package learn.digipet.controllers;

import learn.digipet.domain.Result;
import learn.digipet.domain.Shop;
import learn.digipet.models.Pet;
import learn.digipet.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/shop")
public class ShopController {

    private final Shop service;

    public ShopController(Shop service) {
        this.service = service;
    }

    @PostMapping("/egg/{username}/{petName}")
    public User purchaseEgg(@PathVariable("username") String username, @PathVariable("petName") String petName) {
        return service.purchaseEgg(username, petName);
    }

    @PostMapping("/item/{username}/{itemId}")
    public ResponseEntity<Object> purchaseItem(@PathVariable("username") String username, @PathVariable("itemId") int itemId) {

        User user = service.purchaseItem(username, itemId);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}