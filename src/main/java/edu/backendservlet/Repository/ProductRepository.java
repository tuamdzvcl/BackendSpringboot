package edu.backendservlet.Repository;

import edu.backendservlet.DTO.Response.Interface.ProductResponseipm;
import edu.backendservlet.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long > {
    @Query(value ="SELECT p.id as id, p.author as author, p.description as description ," +
            "p.image_name as imageName, p.name as name," +
            " p.pages as pages,p.publisher as publisher, p.totalbuy as totalbuy," +
            "c.name as categoryName " +
            "FROM product p " +
            "INNER JOIN product_category pc ON p.id = pc.product_id" +
            " INNER JOIN category c ON c.id = pc.category_id",
    countQuery = "SELECT count(*) FROM product p " +
            "INNER JOIN product_category pc ON p.id = pc.product_id " +
            "INNER JOIN category c ON c.id = pc.category_id"
            ,nativeQuery = true)
    Page<ProductResponseipm> findProductsflowCategory(Pageable pageable);

    @Query(value ="SELECT p.id as id, p.author as author, p.description as description ," +
            "p.image_name as imageName, p.name as name," +
            " p.pages as pages,p.publisher as publisher, p.totalbuy as totalbuy," +
            "c.name as categoryName " +
            "FROM product p " +
            "INNER JOIN product_category pc ON p.id = pc.product_id" +
            " INNER JOIN category c ON c.id = pc.category_id " +
            "where p.id = :id",nativeQuery = true)
    Optional<ProductResponseipm> findProductflowCategoryByid(@Param("id")  Long id);
}
