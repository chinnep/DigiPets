package learn.digipet.data.mappers;

import learn.digipet.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();

        user.setUsername(resultSet.getString("username"));
        user.setPasswordHash(resultSet.getString("password_hash"));
        user.setGold(resultSet.getInt("gold"));

        return user;
    }
}