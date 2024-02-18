package com.unicam.risorseLocali.Core.Controller;

import com.unicam.risorseLocali.Core.Service.UserService;
import com.unicam.risorseLocali.Core.Model.Entity.User;
import com.unicam.risorseLocali.Security.Jwt.JwtTokenCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userDetails")
    public ResponseEntity<Object> getUserDetails(Principal principal) {
        Optional<User> user = this.userService.getUser(principal.getName());
        return user.<ResponseEntity<Object>>map(value -> ResponseEntity.ok(this.userService.
                convertToDTO(Optional.of(value)))).orElseGet(() -> ResponseEntity.status(404).body("User not found"));
    }

}