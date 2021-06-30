package learn.digipet.domain;

import learn.digipet.data.ItemRepository;
import learn.digipet.models.Item;

import java.util.List;

public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) { this.repository = repository; }

    public List<Item> findAll() { return repository.findAll(); }

    public Item findById(int itemId) { return repository.findById(itemId); }

}
