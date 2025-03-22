package scalerproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scalerproject.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
