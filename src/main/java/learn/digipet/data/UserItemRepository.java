package learn.digipet.data;

import learn.digipet.models.UserItem;

import java.util.List;

/**
 * User Item needs:
 * - findByUserId: to find any items the user has
 * - add: to show that a user has an item (user creation: initialization all to 0?)
 * - update: to change the quantity
 */
public interface UserItemRepository {

    List<UserItem> findByUsername(String username);

    UserItem findByIds(UserItem userItem);

    boolean add (UserItem userItem);

    boolean update(UserItem userItem);
}
