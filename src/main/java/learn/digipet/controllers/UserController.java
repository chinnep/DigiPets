package learn.digipet.controllers;

import learn.digipet.domain.UserService;
import learn.digipet.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username) {
        return service.findByUsername(username);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> add(@RequestBody @Valid User user) {
        boolean success = service.add(user);
        return new ResponseEntity<>(success ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }
}