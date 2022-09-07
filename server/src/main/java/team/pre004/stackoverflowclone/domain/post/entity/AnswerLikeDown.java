package team.pre004.stackoverflowclone.domain.post.entity;

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
public class AnswerLikeDown {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerLikeDownId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ownerId")
    private Users owner;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "answerId")
    private Answer answer;

    public void mappingUsers(Users owner){
        this.owner = owner;
        owner.mappingAnswerLikeDown(this);
    }

    public void mappingQuestion(Answer answer){
        this.answer = answer;
        answer.mappingAnswerLikeDown(this);
    }

    @Builder
    public AnswerLikeDown(Users owner, Answer answer) {
        this.owner = owner;
        this.answer = answer;
    }
}
