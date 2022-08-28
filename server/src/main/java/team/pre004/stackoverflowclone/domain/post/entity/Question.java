package team.pre004.stackoverflowclone.domain.post.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import team.pre004.stackoverflowclone.domain.tag.entity.TagList;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Question{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    private int views = 0;

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modDate;

    private String link;

    @ManyToOne
    private Users users;

    @OneToMany
    private List<Answer> answers = new ArrayList<>();

    @OneToMany
    private List<QuestionComment> questionComments = new ArrayList<>();

    @OneToMany
    private List<QuestionLikes> questionLikes = new ArrayList<>();

    @OneToMany
    private List<TagList> tagLists = new ArrayList<>();

    @Builder
    public Question(String title, String body, String link, List<TagList> tagLists) {
        this.title = title;
        this.body = body;
        this.link = link;
        this.tagLists = tagLists;
    }
}
