package team.pre004.stackoverflowclone.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import team.pre004.stackoverflowclone.domain.tag.entity.TagList;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "Answer")
@EntityListeners(AuditingEntityListener.class)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(nullable = false)
    private String body;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int likes = 0;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean isAccepted;

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ownerId")
    private Users owner;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "questionId")
    private Question question;

    @OneToMany(mappedBy ="answer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<AnswerComment> answerComments = new HashSet<>();

    @OneToMany(mappedBy ="answer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<AnswerLikeUp> answerLikeUp = new HashSet<>();

    @OneToMany(mappedBy ="answer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<AnswerLikeDown> answerLikeDown = new HashSet<>();

    @OneToMany(mappedBy ="answer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<AnswerAccept> answerAccept = new HashSet<>();


    @Builder
    public Answer(Users owner, Question question, String body) {
        this.owner = owner;
        this.question = question;
        this.body = body;
    }

    public void update(String body) {
        this.body = body;
    }


    public void mappingAnswerAccept(AnswerAccept answerAccept) {
        this.answerAccept.add(answerAccept);
    }

    public void undoAnswerAccept(AnswerAccept answerAccept) {
        this.answerAccept.remove(answerAccept);
    }




    public void mappingAnswerLikeUp(AnswerLikeUp answerLikeUp) {
        this.answerLikeUp.add(answerLikeUp);
    }

    public void mappingAnswerLikeDown(AnswerLikeDown answerLikeDown) {
        this.answerLikeDown.add(answerLikeDown);
    }

    public void updateLikeCount() {
        this.likes = this.answerLikeUp.size() + this.answerLikeDown.size();
    }

    public void undoAnswerLikeUp(AnswerLikeUp answerLikeUp) {
        this.answerLikeUp.remove(answerLikeUp);
    }

    public void undoAnswerLikeDown(AnswerLikeDown answerLikeDown) {
        this.answerLikeDown.remove(answerLikeDown);
    }

}
