package team.pre004.stackoverflowclone.domain.user.entity;
;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.Query;
import team.pre004.stackoverflowclone.domain.LocalDateEntity;
import team.pre004.stackoverflowclone.domain.post.entity.*;
import team.pre004.stackoverflowclone.domain.user.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;


@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "Owner")
public class Users extends LocalDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    @Column(unique = true, nullable = false)
    private String name;

    @Email
    @Column(unique = true, nullable = false)
    private String email;


    @Column(unique = true, nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role roles; //User, MANAGER, ADMIN
    private String bio;

    private String link;

    private String image;

    private String provider;
    private String providerId;


    @JsonIgnore
    @OneToMany(mappedBy ="owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Answer> answers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy ="owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Question> questions = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy ="owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<QuestionComment> questionComments = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy ="owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<AnswerComment> answerComments = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy ="owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<AnswerLikeUp> answerLikeUpList = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy ="owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<AnswerLikeDown> answerLikeDownList = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy ="owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<QuestionLikeUp> questionLikeUpList = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy ="owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<QuestionLikeDown> questionLikeDownList = new HashSet<>();


    @Builder
    public Users(String name, String email, String password, Role roles, String bio, String link, String image) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.bio = bio;
        this.link = link;
        this.image = image;
        this.provider = provider;
        this.providerId = providerId;
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
