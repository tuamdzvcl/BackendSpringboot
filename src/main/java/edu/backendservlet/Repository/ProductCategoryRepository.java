package edu.backendservlet.Repository;

import edu.backendservlet.Model.Product;
import edu.backendservlet.Model.Product_Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<Product_Category, Long> {
    List<Product_Category> findByProductId(Long id);
}
