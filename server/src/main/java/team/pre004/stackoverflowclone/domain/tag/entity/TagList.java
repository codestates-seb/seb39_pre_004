package team.pre004.stackoverflowclone.domain.tag.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.domain.post.entity.Question;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class TagList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagListId;

    @ManyToOne
    @JsonIgnore
    private Tag tag;

    @ManyToOne
    @JsonIgnore
    private Question question;
}
