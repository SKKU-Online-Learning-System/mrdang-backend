package cs.skku.edu.mrdang.domain.content.repository;

import cs.skku.edu.mrdang.domain.content.entity.Content;
import cs.skku.edu.mrdang.domain.content.entity.ContentLike;
import cs.skku.edu.mrdang.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentLikeRepository extends JpaRepository<ContentLike, Long> {
    Optional<ContentLike> findByUserAndContent(User user, Content content);
}
