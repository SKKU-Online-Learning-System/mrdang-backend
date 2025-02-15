package cs.skku.edu.mrdang.domain.content.repository;

import cs.skku.edu.mrdang.domain.content.entity.ContentTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentTagRepository extends JpaRepository<ContentTag, Long> {
}
