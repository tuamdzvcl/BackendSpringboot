package edu.backendservlet.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

    String name;
    String description;
    String imageName;
}
