package edu.backendservlet.DTO.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    String name;
    int totalbuy;
    String  author;
    int pages;
    String publisher;
    int yearPublishing;
    String  description;
    String imageName;
    String category_name;
}
