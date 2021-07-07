package learn.digipet.controllers;

import learn.digipet.domain.*;
import learn.digipet.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final ItemService itemService;
    private final UserItemService userItemService;
    private final PetService petService;

    public UserController(UserService service, ItemService itemService, UserItemService userItemService,
                          PetService petService) {
        this.service = service;
        this.itemService = itemService;
        this.userItemService = userItemService;
        this.petService = petService;
    }

    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username) {
        User result = service.findByUsername(username);

        System.out.println(result);
        System.out.println(result.getPets());

        return result;
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<Void> add(@RequestBody @Valid User user) {
        user.setGold(2000);
        boolean success = service.add(user);
        String username = user.getUsername();

        if (success) {
            List<Item> items = itemService.findAll();
            for (Item i : items) {
                userItemService.add(new UserItem(username,i.getItemId(), 0));
            }
        }

        if (success) {
            Result<Pet> adding = petService.add(new Pet(username + " Jr.",100,100,100,
                    100, LocalDateTime.now(),false,1500,
                    new PetType(3), username));
        }

        return new ResponseEntity<>(success ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }
}