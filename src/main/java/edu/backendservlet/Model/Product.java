package edu.backendservlet.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String name;
     float price;
     float discount;
     int quantity;
     int totalbuy;
     String  author;
     int pages;
     String publisher;
     int yearPublishing;
     @Column(columnDefinition = "VARCHAR(MAX)")
     String  description;
     String imageName;
     boolean shop;
     LocalDate createdAt;
     LocalDate updatedAt;
     LocalDate startsAt;
     LocalDate endsAt;

     @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
     Set<Product_Category> productCategory;
}
