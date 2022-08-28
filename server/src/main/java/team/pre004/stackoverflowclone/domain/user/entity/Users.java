package team.pre004.stackoverflowclone.domain.user.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.AnswerLikes;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersId;

    @Column(unique = true, nullable = false)
    private String name;

    @Email
    @Column(unique = true, nullable = false)
    private String email;


    private String password;
    private String bio;

    private String link;

    private String image;

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modDate;

    @OneToMany
    private List<Answer> answers = new ArrayList<>();

    @OneToMany
    private List<AnswerLikes> likes = new ArrayList<>();

    @Builder
    public Users(String name, String email, String password, String bio, String link, String image) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.link = link;
        this.image = image;
    }
}
