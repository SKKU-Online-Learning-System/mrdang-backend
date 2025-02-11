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
public class Content extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ContentType type;

    private String title;

    @Lob
    private String description;
    private String author;

    private String link;
    private String thumbnailUrl;
    private String youtubeVideoId;

    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Tag> tags = new HashSet<>();
}
