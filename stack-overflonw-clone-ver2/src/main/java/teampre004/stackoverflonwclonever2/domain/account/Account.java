package teampre004.stackoverflonwclonever2.domain.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.type.TextType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Provider provider;

    @Column(unique = true, nullable = false, length = 64)
    @NotBlank(message = "이름은 반드시 작성해야 합니다.")
    @Size(message = "이름은 15자 까지만 가능합니다.", max = 15)
    private String name;

    @Column(unique = true, nullable = false, length = 128)
    @Email(message = "이메일 양식으로 작성해야 합니다.")
    private String email;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "비밀번호는 반드시 입력해야 합니다.")
    private String password;

    @Lob
    private String bio;

    private String image;
    




}
