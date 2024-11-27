package cs.skku.edu.mrdang.domain.user.repository;

import cs.skku.edu.mrdang.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByGlsId(String glsId);
    boolean existsByGlsId(String glsId);
}
