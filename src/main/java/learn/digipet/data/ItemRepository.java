package learn.digipet.data;

import learn.digipet.models.Item;

import java.util.List;

public interface ItemRepository {

    List<Item> findAll();

    Item findById(int itemId);
}
