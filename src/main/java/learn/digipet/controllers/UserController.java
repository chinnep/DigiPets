package learn.digipet.controllers;

import learn.digipet.domain.UserService;
import learn.digipet.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> add(@RequestBody User user) {
        boolean success = service.add(user);
        return new ResponseEntity<>(success ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }
}