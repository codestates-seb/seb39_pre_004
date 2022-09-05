package team.pre004.stackoverflowclone.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import team.pre004.stackoverflowclone.domain.LocalDateEntity;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "QuestionComment")
@EntityListeners(AuditingEntityListener.class)
public class QuestionComment extends LocalDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionCommentId;

    @Lob
    @Column(nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name ="ownerId")
    @JsonIgnore
    private Users owner;
    @ManyToOne
    @JoinColumn(name = "questionId")
    @JsonIgnore
    private Question question;

    @Builder
    public QuestionComment(String body, Question question, Users owner) {
        this.body = body;
        this.question = question;
        this.owner = owner;
    }

    public void update(String body){
        this.body = body;
    }

}
