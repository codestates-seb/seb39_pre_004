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
@EntityListeners(AuditingEntityListener.class)
public class AnswerLikeUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Users users;

    @ManyToOne
    @JsonIgnore
    private Answer answer;


    public void mappingUsers(Users users){
        this.users = users;
        users.mappingAnswerLikeUp(this);
    }

    public void mappingQuestion(Answer answer){
        this.answer = answer;
        answer.mappingAnswerLikeUp(this);
    }
}
