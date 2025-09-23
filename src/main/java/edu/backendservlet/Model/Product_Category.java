package edu.backendservlet.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product_Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_id")
    Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Category_id")
    Category category;
}
