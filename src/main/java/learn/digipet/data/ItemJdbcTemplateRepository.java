package learn.digipet.data;

import learn.digipet.data.mappers.ItemMapper;
import learn.digipet.models.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemJdbcTemplateRepository implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ItemJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Item> findAll(){
        final String sql = "select item_id, item_name, description, price from items limit 1000;";
        return jdbcTemplate.query(sql, new ItemMapper());
    }

    @Override
    public Item findById(int itemId) {

        final String sql = "select item_id, item_name, description, price from items "
                + "where item_id = ?;";

        Item item = jdbcTemplate.query(sql, new ItemMapper(), itemId).stream()
                .findFirst().orElse(null);

        return item;
    }
}
