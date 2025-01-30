package cs.skku.edu.mrdang.domain.content.repository;

import cs.skku.edu.mrdang.domain.content.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String tagName);
}
