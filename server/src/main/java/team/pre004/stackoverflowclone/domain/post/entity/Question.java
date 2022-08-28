package team.pre004.stackoverflowclone.domain.post.entity;

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

    private String link;

    private int views = 0;

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modDate;


    @ManyToOne
    private Users users;

    @OneToMany
    private List<Answer> answers = new ArrayList<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @OneToMany
    private List<Likes> likes = new ArrayList<>();

    @OneToMany
    private List<TagList> tagLists = new ArrayList<>();
}
