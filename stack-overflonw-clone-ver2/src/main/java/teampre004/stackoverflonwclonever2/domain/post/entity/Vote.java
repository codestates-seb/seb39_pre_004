package teampre004.stackoverflonwclonever2.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teampre004.stackoverflonwclonever2.domain.Auditable;
import teampre004.stackoverflonwclonever2.domain.account.entity.Account;
import teampre004.stackoverflonwclonever2.domain.post.VoteType;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "Vote")
@AllArgsConstructor
@NoArgsConstructor
public class Vote extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private VoteType voteType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "owner_id")
    private Account owner;

}
