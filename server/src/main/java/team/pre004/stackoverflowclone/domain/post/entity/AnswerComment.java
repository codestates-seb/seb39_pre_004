package team.pre004.stackoverflowclone.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class AnswerComment extends LocalDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerCommentId;

    @Lob
    @Column(nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name ="ownerId")
    @JsonIgnore
    private Users owner;

    @ManyToOne
    @JsonIgnore
    private Answer answer;

    public void update(String body) {
        this.body = body;
    }


    @Builder
    public AnswerComment(Users owner, Answer answer, String body) {
        this.owner = owner;
        this.answer = answer;
        this.body = body;
    }
}
