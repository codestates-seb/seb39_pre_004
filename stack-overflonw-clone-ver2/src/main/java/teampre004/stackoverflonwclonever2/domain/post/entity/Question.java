package teampre004.stackoverflonwclonever2.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import teampre004.stackoverflonwclonever2.domain.Auditable;
import teampre004.stackoverflonwclonever2.domain.account.entity.Account;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "Question")
@AllArgsConstructor
@NoArgsConstructor
public class Question extends Auditable {

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

    @Column(nullable = false)
    private boolean isBookmarked = false;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "owner_id")
    private Account owner;

    @OneToMany(mappedBy ="question", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Answer> answers = new HashSet<>();

    @OneToMany(mappedBy ="question", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy ="question", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Bookmark> bookmarks = new HashSet<>();

    @OneToMany(mappedBy ="question", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy ="question", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Vote> votes = new HashSet<>();

    public void accepted(){
        for (Answer answer: answers ) {
            this.isAccepted |= answer.isAccepted();
        }
    }


}
