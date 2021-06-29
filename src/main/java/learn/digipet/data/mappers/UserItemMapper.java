package learn.digipet.data.mappers;

import learn.digipet.models.UserItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserItemMapper implements RowMapper<UserItem> {

    @Override
    public UserItem mapRow(ResultSet resultSet, int i) throws SQLException {
        UserItem userItem = new UserItem();

        userItem.setItemId(resultSet.getInt("item_id"));
        userItem.setUserId(resultSet.getInt("user_id"));
        userItem.setQuantity(resultSet.getInt("quantity"));

        return userItem;
    }
}