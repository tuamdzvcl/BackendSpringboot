package edu.backendservlet.DTO.Request;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {
    String name;
    String description;
    String imageName;
}
