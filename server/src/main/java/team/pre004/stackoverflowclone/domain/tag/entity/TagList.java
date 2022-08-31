package team.pre004.stackoverflowclone.domain.tag.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.Question;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "TagList")
public class TagList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagListId;

    @ManyToOne
    @JoinColumn(name = "tagId")
    @JsonIgnore
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "questionId")
    @JsonIgnore
    private Question question;


}
