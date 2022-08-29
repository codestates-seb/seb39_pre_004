package team.pre004.stackoverflowclone.domain.post.entity;

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
    private Long id;

    @ManyToOne
    @JoinColumn(name ="usersId")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "answerId")
    private Answer answer;
}
