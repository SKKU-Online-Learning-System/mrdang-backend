package cs.skku.edu.mrdang.domain.content.repository;

import cs.skku.edu.mrdang.domain.content.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
}
