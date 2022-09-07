package team.pre004.stackoverflowclone.domain.post.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import team.pre004.stackoverflowclone.domain.LocalDateEntity;
import team.pre004.stackoverflowclone.domain.tag.entity.TagList;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import javax.persistence.*;
import java.time.LocalDateTime;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
@Slf4j
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Question")
@EntityListeners(AuditingEntityListener.class)
public class Question extends LocalDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String body;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean isAccepted;
    @Column(columnDefinition = "integer default 0", nullable = false)
    private int likes ;

    private String link;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId")
    @JsonIgnore
    private Users owner;

    @OneToMany(mappedBy ="question", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Answer> answers = new LinkedHashSet<>() ;

    @OneToMany(mappedBy ="question", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<QuestionComment> questionComment = new LinkedHashSet<>();

    @OneToMany(mappedBy ="question", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<QuestionLikeUp> questionLikeUp = new LinkedHashSet<>();

    @OneToMany(mappedBy ="question", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<QuestionLikeDown> questionLikeDown = new LinkedHashSet<>();

    @OneToMany(mappedBy ="question", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<TagList> tags;

    @Builder
    public Question(String title, String body, String link, List<TagList> tags, Users owner) {
        this.title = title;
        this.body = body;
        this.link = link;
        this.tags = tags;
        this.owner = owner;
    }

    public void accept(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }


    public void mappingQuestionLikeUp(QuestionLikeUp questionLikeUp) {
        this.questionLikeUp.add(questionLikeUp);
    }

    public void mappingQuestionLikeDown(QuestionLikeDown questionLikeDown) {
        this.questionLikeDown.add(questionLikeDown);
    }

    public void updateLikeCount() {
        this.likes = this.questionLikeUp.size() - this.questionLikeDown.size();
    }

    public void undoQuestionLikeUp(QuestionLikeUp questionLikeUp) {
        this.questionLikeUp.remove(questionLikeUp);
    }

    public void undoQuestionLikeDown(QuestionLikeDown questionLikeDown) {
        this.questionLikeDown.remove(questionLikeDown);
    }

    public void update(String title, String body, List<TagList> tags){
        this.title = title;
        this.body = body;
        this.tags = tags;
    }



}
