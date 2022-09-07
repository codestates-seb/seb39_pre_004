package teampre004.stackoverflonwclonever2.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import teampre004.stackoverflonwclonever2.domain.Auditable;
import teampre004.stackoverflonwclonever2.domain.account.entity.Account;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "Answer")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Answer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    @NotEmpty(message = "답변을 1글자 이상 입력해 주세요.")
    private String body;

    @Column(nullable = false)
    private int likes = 0;

    @Column(nullable = false)
    private boolean isAccepted = false;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "owner_id")
    private Account owner;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy ="answer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Vote> votes = new HashSet<>();

    @OneToMany(mappedBy ="answer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();

}
