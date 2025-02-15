package cs.skku.edu.mrdang.domain.content.entity;


import cs.skku.edu.mrdang.domain.content.dto.ContentDTO;
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

    private String link;
    private String thumbnailUrl;
    private String youtubeVideoId;

    private Long viewCount;

    @Builder.Default
    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContentTag> contentTags = new HashSet<>();

    public static Content from(ContentDTO.CreateRequest request) {
        return Content.builder()
                .type(request.getType())
                .title(request.getTitle())
                .description(request.getDescription())
                .author(request.getAuthor())
                .link(request.getLink())
                .thumbnailUrl(request.getThumbnailUrl())
                .youtubeVideoId(request.getType() == ContentType.YOUTUBE ? parseYoutubeVideoId(request.getLink()) : null)
                .viewCount(0L)
                .contentTags(new HashSet<>())
                .build();
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    private static String parseYoutubeVideoId(String link) {
        String[] split = link.split("v=");
        return split[1];
    }
}
