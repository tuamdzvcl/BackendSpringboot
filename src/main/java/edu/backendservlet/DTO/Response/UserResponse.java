package edu.backendservlet.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    String fullname;
    String username;
    String email;
    String phone;
    boolean gender;
    String address;
    String role;
}
