package cs.skku.edu.mrdang.domain.content.entity;

import cs.skku.edu.mrdang.domain.user.entity.User;
import cs.skku.edu.mrdang.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ContentLike extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static ContentLike of(Content content, User user) {
        return ContentLike.builder()
                .content(content)
                .user(user)
                .build();
    }
}
