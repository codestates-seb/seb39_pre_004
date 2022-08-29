package team.pre004.stackoverflowclone.domain.post.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import team.pre004.stackoverflowclone.domain.tag.entity.TagList;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private boolean isAccepted;

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modDate;

    @ManyToOne
    private Users owner;

    @ManyToOne
    private Question question;

    @OneToMany
    private Set<AnswerLikeUp> answerLikeUp;

    @OneToMany
    private Set<AnswerLikeUp> answerLikeDown;

    @OneToMany
    private List<TagList> tags;

    @Builder
    public Answer(String body) {
        this.body = body;
    }
}
