package team.pre004.stackoverflowclone.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class QuestionLikeUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionLikeUpId;

    @ManyToOne
    @JsonIgnore
    private Users users;

    @ManyToOne
    @JsonIgnore
    private Question question;

    public void mappingUsers(Users users){
        this.users = users;
        users.mappingQuestionLikeUp(this);
    }

    public void mappingQuestion(Question question){
        this.question = question;
        question.mappingQuestionLikeUp(this);
    }

    @Builder
    public QuestionLikeUp(Users users, Question question) {
        this.users = users;
        this.question = question;
    }
}
