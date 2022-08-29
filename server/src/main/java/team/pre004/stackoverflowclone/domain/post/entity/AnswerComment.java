package team.pre004.stackoverflowclone.domain.post.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class AnswerComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerCommentId;

    @Column(nullable = false)
    private String body;

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modDate;

    @ManyToOne
    @JoinColumn(name = "answerId")
    private Answer answer;


    @Builder
    public AnswerComment(String body) {
        this.body = body;
    }
}