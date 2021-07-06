package learn.digipet.controllers;

import learn.digipet.domain.UserItemService;
import learn.digipet.models.UserItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/userItems")
public class UserItemController {

    private final UserItemService service;

    public UserItemController(UserItemService service) {
        this.service = service;
    }

}