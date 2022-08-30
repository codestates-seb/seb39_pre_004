package team.pre004.stackoverflowclone.domain.user.entity;
;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import team.pre004.stackoverflowclone.domain.post.entity.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Email
    @Column(unique = true, nullable = false)
    private String email;


    @Column(unique = true, nullable = false)
    private String password;
    private String bio;

    private String link;

    private String image;

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modDate;

    @OneToMany(mappedBy ="owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Answer> answers;

    @OneToMany(mappedBy ="owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Question> questions;

    @OneToMany(mappedBy ="owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<QuestionComment> questionComments;
    @OneToMany(mappedBy ="users", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<AnswerLikeUp> answerLikeUpList;

    @OneToMany(mappedBy ="users", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<AnswerLikeDown> answerLikeDownList;

    @OneToMany(mappedBy ="users", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<QuestionLikeUp> questionLikeUpList;

    @OneToMany(mappedBy ="users", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<QuestionLikeDown> questionLikeDownList;


    @Builder
    public Users(String name, String email, String password, String bio, String link, String image) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.link = link;
        this.image = image;
    }

    public void mappingQuestionLikeUp(QuestionLikeUp questionLikeUp){
        this.questionLikeUpList.add(questionLikeUp);
    }

    public void mappingQuestionLikeDown(QuestionLikeDown questionLikeDown){
        this.questionLikeDownList.add(questionLikeDown);
    }

    public void mappingAnswerLikeUp(AnswerLikeUp answerLikeUp){
        this.answerLikeUpList.add(answerLikeUp);
    }

    public void mappingAnswerLikeDown(AnswerLikeDown answerLikeDown){
        this.answerLikeDownList.add(answerLikeDown);
    }

}
