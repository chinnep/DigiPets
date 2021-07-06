package learn.digipet.controllers;

import learn.digipet.domain.ItemService;
import learn.digipet.domain.UserItemService;
import learn.digipet.domain.UserService;
import learn.digipet.models.Item;
import learn.digipet.models.User;
import learn.digipet.models.UserItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final ItemService itemService;
    private final UserItemService userItemService;

    public UserController(UserService service, ItemService itemService, UserItemService userItemService) {
        this.service = service;
        this.itemService = itemService;
        this.userItemService = userItemService;
    }

    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username) {
        return service.findByUsername(username);
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<Void> add(@RequestBody @Valid User user) {
        boolean success = service.add(user);
        String username = user.getUsername();

        if (success) {
            List<Item> items = itemService.findAll();
            for (Item i : items) {
                userItemService.add(new UserItem(username,i.getItemId(), 0));
            }
        }

        return new ResponseEntity<>(success ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }
}