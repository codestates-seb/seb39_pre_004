package team.pre004.stackoverflowclone.domain.tag;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
