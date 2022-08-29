package team.pre004.stackoverflowclone.domain.post.entity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import team.pre004.stackoverflowclone.domain.tag.entity.TagList;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import javax.persistence.*;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Question{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    private Integer views = 0;
    private Integer likes = 0;

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modDate;

    private String link;



    @ManyToOne
    private Users owner;

    @OneToMany(mappedBy ="question", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Answer> answers;

    @OneToMany(mappedBy ="question", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<QuestionComment> questionComment;

    @OneToMany(mappedBy ="question", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<QuestionLikeUp> questionLikeUp;

    @OneToMany(mappedBy ="question", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<QuestionLikeDown> questionLikeDown;

    @OneToMany
    private List<TagList> tags;

    @Builder
    public Question(String title, String body, String link, List<TagList> tags, Users owner) {
        this.title = title;
        this.body = body;
        this.link = link;
        this.tags = tags;
        this.owner = owner;
    }

    public void mappingQuestionLikeUp(QuestionLikeUp questionLikeUp) {
        this.questionLikeUp.add(questionLikeUp);
    }

    public void mappingQuestionLikeDown(QuestionLikeDown questionLikeDown) {
        this.questionLikeDown.add(questionLikeDown);
    }

    public void updateLikeCount() {
        this.likes = this.questionLikeUp.size() + this.questionLikeDown.size();
    }

    public void undoQuestionLikeUp(QuestionLikeUp questionLikeUp) {
        this.questionLikeUp.remove(questionLikeUp);
    }

    public void undoQuestionLikeDown(QuestionLikeDown questionLikeDown) {
        this.questionLikeDown.remove(questionLikeDown);
    }



}