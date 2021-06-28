package learn.digipet.data.mappers;

import learn.digipet.models.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet resultSet, int i) throws SQLException {
        Item item = new Item();

        item.setItemId(resultSet.getInt("item_id"));
        item.setName(resultSet.getString("item_name"));
        item.setDescription(resultSet.getString("description"));
        item.setPrice(resultSet.getInt("price"));

        return item;
    }
}
