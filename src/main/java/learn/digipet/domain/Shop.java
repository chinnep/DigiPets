package learn.digipet.domain;

import learn.digipet.models.PetType;
import learn.digipet.models.User;
import learn.digipet.models.Item;
import learn.digipet.models.UserItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The shop offers the ability to spend gold (UserService) won in battles on:
 * Weapons and items (ItemService and UserItemService)
 * New Eggs for pets --> will randomize and load pet creation
 */
@Service
public class Shop {

    private final ItemService itemService;
    private final UserService userService;
    private final UserItemService userItemService;
    private static final int EGG_PRICE = 100;

    public Shop (UserService userService, ItemService itemService, UserItemService userItemService) {
        this.itemService = itemService;
        this.userService = userService;
        this.userItemService = userItemService;
    }

    public List<Item> generateWeaponsList() {
        List<Item> all = itemService.findAll();

        List<Item> result = new ArrayList<Item>();

        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).isForBattle() == true) {
                result.add(all.get(i));
            }
        }

        return result;
    }

    public List<Item> generateItemsList() {
        return itemService.findAll().stream()
                .filter(i -> i.isForBattle() == false)
                .collect(Collectors.toList());
    }

    public Result<User> purchasePet(User user) {
        user.setGold(user.getGold() - EGG_PRICE);

        Result<User> result = userService.update(user);

        return result;
    }

    // Todo
    @Transactional
    public boolean purchaseItem(User user, Item item) {

        return false;
    }

}
