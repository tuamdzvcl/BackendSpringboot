package edu.backendservlet.Controller;

import edu.backendservlet.DTO.Request.UserCreateRequest;
import edu.backendservlet.DTO.Request.UserUpdateRequest;
import edu.backendservlet.DTO.Response.ApiResponse;
import edu.backendservlet.DTO.Response.UserResponse;
import edu.backendservlet.Model.User;
import edu.backendservlet.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping()
    public ApiResponse<List<User>> getAllUsers(){
        List<User> userList = userService.getUser();
        ApiResponse<List<User>> response = new ApiResponse<>();

        response.setCode(HttpStatus.OK.value());
        response.setResult(userList);
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable Long id){
        ApiResponse<UserResponse> response = new ApiResponse<>();
        UserResponse UseResponse =userService.getbyidUser(id);
        response.setCode(HttpStatus.OK.value());
        response.setResult(UseResponse);
        return response;
    }
    @PostMapping()
    public ApiResponse<User> CreateUser(@RequestBody UserCreateRequest request){
        ApiResponse<User> response = new ApiResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setResult(userService.CreateUser(request));
        return response;
    }
    @PutMapping("/{id}")
    public ApiResponse<UserResponse> UpdateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request){
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setResult(userService.updateUser(id, request));
        return response;
    }
    @DeleteMapping("/{id}")
    public ApiResponse<UserResponse> DeleteUser(@PathVariable Long id){
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(userService.deleteUser(id));
        return response;
    }
}
