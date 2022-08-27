package team.pre004.stackoverflowclone.domain.tag;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.domain.post.Question;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class TagList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tag tag;

    @ManyToOne
    private Question question;
}
