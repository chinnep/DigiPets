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
    private final PetService petService;
    private final UserService userService;
    private final UserItemService userItemService;
    private static final int EGG_PRICE = 100;

    public Shop (UserService userService, PetService petService,
                 ItemService itemService, UserItemService userItemService) {
        this.petService = petService;
        this.userService = userService;
        this.userItemService = userItemService;
        this.itemService = itemService;
    }

    public Result<User> purchaseEgg(User user) {
        //user.setGold(user.getGold() - EGG_PRICE);

        //Result<User> result = userService.update(user); // No update currently

        return null;
    }

    @Transactional
    public boolean purchaseItem(User user, Item item) {

        item = itemService.findById(item.getItemId());
//        user.setGold(user.getGold() - );
        return false;
    }

}
