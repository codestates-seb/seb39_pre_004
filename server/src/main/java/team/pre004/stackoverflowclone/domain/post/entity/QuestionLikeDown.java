package team.pre004.stackoverflowclone.domain.post.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class QuestionLikeDown {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionLikeDownId;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Question question;

    public void mappingUsers(Users users){
        this.users = users;
        users.mappingQuestionLikeDown(this);
    }

    public void mappingQuestion(Question question){
        this.question = question;
        question.mappingQuestionLikeDown(this);
    }

    @Builder
    public QuestionLikeDown(Users users, Question question) {
        this.users = users;
        this.question = question;
    }
}
