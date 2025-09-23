package edu.backendservlet.DTO.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequest {
    String username;
    String password ;
    String fullname;
    String email;
    String phone;
    boolean gender;
    String address;
    String role = "USER";
}
