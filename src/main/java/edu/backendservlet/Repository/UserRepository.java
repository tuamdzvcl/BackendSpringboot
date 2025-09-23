package edu.backendservlet.Repository;

import edu.backendservlet.DTO.Response.UserResponse;
import edu.backendservlet.Model.User;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);

}
