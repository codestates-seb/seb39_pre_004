package team.pre004.stackoverflowclone.domain.tag.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "Tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy ="tag", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<TagList> tagLists = new ArrayList<>();
}
