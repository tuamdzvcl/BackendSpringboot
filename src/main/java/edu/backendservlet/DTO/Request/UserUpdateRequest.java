package edu.backendservlet.DTO.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequest {
    String password;
    String fullname;
    String email;
    String phone;
    Boolean gender;
    String address;
}
