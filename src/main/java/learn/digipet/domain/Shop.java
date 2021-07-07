package learn.digipet.domain;

import learn.digipet.models.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    public Shop(UserService userService, PetService petService,
                ItemService itemService, UserItemService userItemService) {
        this.petService = petService;
        this.userService = userService;
        this.userItemService = userItemService;
        this.itemService = itemService;
    }

    @Transactional
    public User purchaseEgg(String username, String petName) {

        Result<Pet> adding = petService.add(new Pet(petName,100, 100, 100,
                100, LocalDateTime.now(), false, 1500, new PetType(3), username));

        if (!adding.isSuccess()) {
            return null;
        }

        User result = userService.findByUsername(username);
        result.setGold(result.getGold() - EGG_PRICE);

        if (!userService.updateGold(result)) {
            return null;
        }

        return result;
    }

    @Transactional
    public User purchaseItem(String username, int itemId) {

        Item purchase = itemService.findById(itemId);

        UserItem userItem = new UserItem(username, itemId);
        userItem = userItemService.findByIds(userItem);
        userItem.setQuantity(userItem.getQuantity() + 1);

        Result<Void> userItemResult = userItemService.update(userItem);

        if (!userItemResult.isSuccess()) return null;

        User result = userService.findByUsername(username);
        result.setGold(result.getGold() - purchase.getPrice());

        if (!userService.updateGold(result)) {
            return null;
        }

        return result;
    }

}
