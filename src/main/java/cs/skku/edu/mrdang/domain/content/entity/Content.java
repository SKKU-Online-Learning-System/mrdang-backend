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

    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private String author;
    private Long duration; // stored in second(1365 = 22:45)

    private String link;
    private String thumbnailUrl;
    private String youtubeVideoId;

    private Long viewCount;

    @Builder.Default
    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContentTag> contentTags = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContentLike> contentLikes = new HashSet<>();

    public void increaseViewCount() {
        this.viewCount++;
    }
}
