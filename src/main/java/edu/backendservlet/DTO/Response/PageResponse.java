package edu.backendservlet.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T> {
     List<T> content;      
     int totalPages;       
     long totalElements;  
     int page;             
     int size;

}
