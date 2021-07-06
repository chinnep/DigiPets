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
        item.setForBattle(resultSet.getBoolean("for_battle"));
        item.setForFood(resultSet.getBoolean("for_food"));
        item.setForWater(resultSet.getBoolean("for_water"));
        item.setForCare(resultSet.getBoolean("for_care"));
        item.setForHealth(resultSet.getBoolean("for_health"));
        item.setPrice(resultSet.getInt("price"));
        item.setImgUrl(resultSet.getString("img_url"));
        //item.setQuantity(resultSet.getInt("quantity"));

        return item;
    }
}
