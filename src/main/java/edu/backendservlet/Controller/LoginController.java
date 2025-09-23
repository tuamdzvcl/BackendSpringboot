package edu.backendservlet.Controller;

import com.nimbusds.jose.JOSEException;
import edu.backendservlet.DTO.Request.IntrospectRequest;
import edu.backendservlet.DTO.Request.LoginRequest;
import edu.backendservlet.DTO.Response.ApiResponse;
import edu.backendservlet.DTO.Response.IntrospectResponse;
import edu.backendservlet.DTO.Response.UserLoginResponse;
import edu.backendservlet.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.internal.metadata.aggregated.FieldCascadable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Locale;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "*")
public class LoginController {
    UserService userService;

    @PostMapping("/login")
    public ApiResponse<UserLoginResponse> login(@RequestBody LoginRequest loginRequest) {
           var result = userService.login(loginRequest);
           return ApiResponse.<UserLoginResponse>builder()
                   .result(result)
                   .build();
    }
    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> Introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = userService.introspectoken(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}
