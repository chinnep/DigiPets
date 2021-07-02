package learn.digipet.controllers;

import learn.digipet.domain.ItemService;
import learn.digipet.models.Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) { this.service = service; }

    @GetMapping
    public List<Item> findAll() { return service.findAll(); }

    @GetMapping("/{itemId}")
    public Item findById(@PathVariable int itemId) { return service.findById(itemId); }

}
