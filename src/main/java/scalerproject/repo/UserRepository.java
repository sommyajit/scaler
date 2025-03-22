package scalerproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scalerproject.entity.User.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
