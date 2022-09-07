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
@Table(name = "QuestionLikeUp")
@EntityListeners(AuditingEntityListener.class)
public class QuestionLikeUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionLikeUpId;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    @JsonIgnore
    private Users owner;

    @ManyToOne
    @JoinColumn(name = "questionId")
    @JsonIgnore
    private Question question;

    public void mappingUsers(Users owner){
        this.owner = owner;
        owner.mappingQuestionLikeUp(this);
    }

    public void mappingQuestion(Question question){
        this.question = question;
        question.mappingQuestionLikeUp(this);
    }

    @Builder
    public QuestionLikeUp(Users owner, Question question) {
        this.owner = owner;
        this.question = question;
    }
}
