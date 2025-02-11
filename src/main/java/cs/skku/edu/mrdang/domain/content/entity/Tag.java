package cs.skku.edu.mrdang.domain.content.entity;

import cs.skku.edu.mrdang.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Tag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Builder.Default
    @ManyToMany
    private Set<Content> contents = new HashSet<>();

    public void addContent(Content content) {
        contents.add(content);
        content.getTags().add(this);
    }
}
