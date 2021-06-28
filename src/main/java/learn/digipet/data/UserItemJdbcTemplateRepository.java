package learn.digipet.data;

import learn.digipet.models.UserItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserItemJdbcTemplateRepository implements UserItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserItemJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(UserItem userItem) {

        final String sql = "insert into user_item (user_id, item_id, quantity) values (?,?,?);";

        return jdbcTemplate.update(sql,
                userItem.getUserId(),
                userItem.getItemId(),
                userItem.getQuantity()) > 0;
    }

}
