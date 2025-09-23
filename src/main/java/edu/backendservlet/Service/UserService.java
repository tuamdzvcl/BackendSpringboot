package edu.backendservlet.Service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import edu.backendservlet.DTO.Request.IntrospectRequest;
import edu.backendservlet.DTO.Request.LoginRequest;
import edu.backendservlet.DTO.Request.UserCreateRequest;
import edu.backendservlet.DTO.Request.UserUpdateRequest;
import edu.backendservlet.DTO.Response.ApiResponse;
import edu.backendservlet.DTO.Response.IntrospectResponse;
import edu.backendservlet.DTO.Response.UserLoginResponse;
import edu.backendservlet.DTO.Response.UserResponse;
import edu.backendservlet.Model.User;
import edu.backendservlet.Repository.UserRepository;
import edu.backendservlet.exception.Appexcption;
import edu.backendservlet.exception.ErrorCode;
import edu.backendservlet.exception.GobalExcepyionHander;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;


@Log4j2
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;

    @NonFinal
    @Value("${BackEndServlet.api.key}")
    protected String SIGNER_KEY;

    public UserResponse getbyidUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new Appexcption(ErrorCode.USER_NOT_FOUND));
        return UserResponse.builder()
                .username(user.getUsername())
                .fullname(user.getFullname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .gender(user.isGender())
                .address(user.getAddress())
                .role(user.getRole())
                .build();
    }

    public List<User> getUser(){
        return userRepository.findAll();
    }

    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new Appexcption(ErrorCode.USER_NOT_FOUND));
        user.setFullname(request.getFullname());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setGender(request.getGender());
        user.setAddress(request.getAddress());
        userRepository.save(user);

        return UserResponse.builder()
                .fullname(user.getFullname())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .gender(user.isGender())
                .address(user.getAddress())
                .role(user.getRole()).build();
    }
    public String deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new Appexcption(ErrorCode.USER_NOT_FOUND));
        userRepository.delete(user);
        return "deleted";
    }
    public User CreateUser(UserCreateRequest request) {
        User user = new User();
        if(userRepository.existsByUsername(request.getUsername()))
            throw  new Appexcption(ErrorCode.USER_EXISTED);

        user.setFullname(request.getFullname());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setGender(request.isGender());
        user.setAddress(request.getAddress());
        user.setRole(request.getRole());

        return  userRepository.save(user);
    }

    public IntrospectResponse introspectoken(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY);
        SignedJWT  jwt = SignedJWT.parse(token);

        Date expriryTime = jwt.getJWTClaimsSet().getExpirationTime();

        var verfied = jwt.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verfied && expriryTime.after(new Date()))
                .build();

    }

    public UserLoginResponse login(LoginRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new Appexcption(ErrorCode.USER_NOT_FOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean result = passwordEncoder.matches(request.getPassword(),user.getPassword());
        if(!result) {
            throw new Appexcption(ErrorCode.USER_AUTH);
        }
        var token = generatetoken(request.getUsername());
        return UserLoginResponse.builder()
                .success(true)
                .token(token)
                .build();
    }

    private String generatetoken(String username) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet= new JWTClaimsSet.Builder()
                .subject(username)
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(15, ChronoUnit.MINUTES).toEpochMilli()
                ))
                .claim("username",username)
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jweObject = new JWSObject(header,payload);

        try {
            jweObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return  jweObject.serialize();
        } catch (JOSEException e) {
            log.error(e.getMessage()+" khong tao dc token");
            throw new RuntimeException(e);
        }
    }
}
