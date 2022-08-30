package team.pre004.stackoverflowclone.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Table(name ="AnswerAccept")
@EntityListeners(AuditingEntityListener.class)
public class AnswerAccept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerAcceptId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ownerId")
    private Users owner;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "answerId")
    private Answer answer;


    public void mappingOwner(Users owner){
        this.owner = owner;
        owner.mappingAnswerAccept(this);
    }

    public void mappingQuestion(Answer answer){
        this.answer = answer;
        answer.mappingAnswerAccept(this);
    }

}
