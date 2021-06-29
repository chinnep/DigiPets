package learn.digipet.data;

import learn.digipet.data.mappers.ItemMapper;
import learn.digipet.data.mappers.PetMapper;
import learn.digipet.data.mappers.UserMapper;
import learn.digipet.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {

        final String sql = "select user_id, gold from user limit 1000;";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User findById(int userId) {

        final String sql = "select user_id, gold from user "
                + "where user_id = ?;";

        User user = jdbcTemplate.query(sql, new UserMapper(), userId).stream()
                .findFirst().orElse(null);

        return user;
    }

    @Override
    public boolean add(User user) {

        final String sql = "insert into user (user_id, gold) values (?,?);";

        return jdbcTemplate.update(sql,
                user.getUserId(),
                user.getGold()) > 0;
    }

    @Override
    public boolean update(User user) {

        // not allowing user_id updates...
        final String sql = "update user set "
                + "gold = ? "
                + "where user_id = ?;";

        return jdbcTemplate.update(sql,
                user.getGold(),
                user.getUserId()) > 0;
    }

    private void addPets(User user) {

        final String sql = "select pet_id, pet_name, hunger_lvl, care_lvl, "
                + "thirst_lvl, health_lvl, time_to_zero, is_dead, trophies "
                + "from pet "
                + "where user_id = ?";

        var pets = jdbcTemplate.query(sql, new PetMapper(), user.getUserId());
        user.setPets(pets);
    }

    private void addItems(User user) {

        final String sql = "select ui.user_id, ui.item_id, ui.quantity, "
                + "i.item_name, i.description, i.for_battle, i.price "
                + "from user_item ui "
                + "inner join item i on ui.item_id = i.item_id "
                + "where ui.user_id = ?";

        var userItems = jdbcTemplate.query(sql, new ItemMapper(), user.getUserId());
        user.setItems(userItems);
    }

}
