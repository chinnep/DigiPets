package learn.digipet.controllers;

import learn.digipet.domain.UserService;
import learn.digipet.jwt.JwtConverter;
import learn.digipet.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthController {

    private UserService service;
    private JwtConverter jwtConverter;

    public AuthController(UserService service, JwtConverter jwtConverter) {
        this.service = service;
        this.jwtConverter = jwtConverter;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody User user) {
        if (service.authenticate(user)) {
            String token = jwtConverter.getTokenFromUser(user);
            System.out.println(user);

            HashMap<String, String> values = new HashMap<>();
            values.put("jwt_token", token);

            return new ResponseEntity<>(values, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<Object> refresh(HttpServletRequest request) {
        User user = jwtConverter.getUserFromAuthHeader(request.getHeader("Authorization"));
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        String token = jwtConverter.getTokenFromUser(user);

        HashMap<String, String> values = new HashMap<>();
        values.put("jwt_token", token);

        return new ResponseEntity<>(values, HttpStatus.OK);
    }

}
