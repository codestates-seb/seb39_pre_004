package teampre004.stackoverflonwclonever2.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teampre004.stackoverflonwclonever2.domain.Auditable;
import teampre004.stackoverflonwclonever2.domain.account.entity.Account;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Table(name = "Tag")
@AllArgsConstructor
@NoArgsConstructor
public class Tag extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(max = 15, message = "태그 이름은 15자 이상을 초과할 수 없습니다.")
    @NotBlank(message = "태그 이름을 지정해 주세요.")
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "owner_id")
    private Account owner;
}
