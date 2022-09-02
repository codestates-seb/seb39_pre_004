package teampre004.stackoverflonwclonever2.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teampre004.stackoverflonwclonever2.domain.Auditable;
import teampre004.stackoverflonwclonever2.domain.account.entity.Account;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "Bookmark")
@AllArgsConstructor
@NoArgsConstructor
public class Bookmark extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "owner_id")
    private Account owner;

}
