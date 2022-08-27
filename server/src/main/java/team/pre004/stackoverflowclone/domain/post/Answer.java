package team.pre004.stackoverflowclone.domain.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import team.pre004.stackoverflowclone.domain.user.Users;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private boolean isAccepted;

    @Column(nullable = false)
    private String link;

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modDate;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Question question;
}
