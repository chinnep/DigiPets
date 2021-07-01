package learn.digipet.data;

import learn.digipet.data.mappers.UserItemMapper;
import learn.digipet.models.UserItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User Item needs:
 * - findByUserId: to find a list of items the user has
 * - add: to show that a user has an item (user initialization to 0?)
 * - update: to change the quantity
 */
@Repository
public class UserItemJdbcTemplateRepository implements UserItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserItemJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserItem> findByUserId(int id) {
        final String sql = "select quantity, user_id, item_id "
                + "from user_item "
                + "where user_id = ?;";

        return jdbcTemplate.query(sql, new UserItemMapper(), id);
    }

    @Override
    public UserItem findByIds(UserItem userItem) {
        final String sql = "select quantity, user_id, item_id "
                + "from user_item "
                + "where user_id = ? "
                + "and item_id = ?;";

        return jdbcTemplate.query(sql, new UserItemMapper(), userItem.getUserId(), userItem.getItemId())
                .stream().findAny().orElse(null);
    }

    @Override
    public boolean add(UserItem userItem) {

        final String sql = "insert into user_item (user_id, item_id, quantity) values (?,?,?);";

        return jdbcTemplate.update(sql,
                userItem.getUserId(),
                userItem.getItemId(),
                userItem.getQuantity()) > 0;
    }

    @Override
    public boolean update(UserItem userItem) {
        final String sql = "update user_item set quantity = ? "
                + "where user_id = ? "
                + "and item_id = ?;";

        return jdbcTemplate.update(sql,
                userItem.getQuantity(),
                userItem.getUserId(),
                userItem.getItemId()) > 0;
    }
}
