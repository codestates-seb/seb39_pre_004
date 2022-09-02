package teampre004.stackoverflonwclonever2.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teampre004.stackoverflonwclonever2.domain.Auditable;
import teampre004.stackoverflonwclonever2.domain.account.entity.Account;
import teampre004.stackoverflonwclonever2.domain.post.PostType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Table(name = "Comment")
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    @NotEmpty(message = "댓글을 1글자 이상 입력해 주세요.")
    private String body;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "owner_id")
    private Account owner;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "answer_id")
    private Answer answer;

}
