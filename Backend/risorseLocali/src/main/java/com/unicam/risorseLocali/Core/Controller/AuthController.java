package com.unicam.risorseLocali.Core.Controller;

import com.unicam.risorseLocali.Core.Model.Entity.User;
import com.unicam.risorseLocali.Core.Service.UserService;
import com.unicam.risorseLocali.Security.Jwt.JwtTokenCreator;
import com.unicam.risorseLocali.Security.User.UserCredential;
import com.unicam.risorseLocali.Util.DTO.UserDTO;
import com.unicam.risorseLocali.Util.Enum.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:8080")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenCreator jwtTokenCreator;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                                    PasswordEncoder passwordEncoder,
                                    JwtTokenCreator jwtTokenCreator,
                                    UserService userService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenCreator = jwtTokenCreator;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserCredential credentials) {
        try {
            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
            UserDTO userDTO = this.userService.convertToDTO(this.userService.getUser(credentials.getUsername()));
            String token = this.jwtTokenCreator.generate(authentication.getName(), userDTO.getRuolo());
            return ResponseEntity.ok().headers(authorizationHeaders(token)).body(userDTO);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials: " + e.getLocalizedMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRuolo(Role.AUTH_USER);
        this.userService.addUser(user);
        return ResponseEntity.ok("User added");
    }

    private HttpHeaders authorizationHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return headers;
    }

}