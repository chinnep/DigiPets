package learn.digipet.data;

import learn.digipet.data.mappers.ItemMapper;
import learn.digipet.data.mappers.PetMapper;
import learn.digipet.data.mappers.UserMapper;
import learn.digipet.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public User findByUsername(String username) {

        final String sql = "select gold from user "
                + "where username = ?;";

        User user = jdbcTemplate.query(sql, new UserMapper(), username).stream()
                .findFirst().orElse(null);

        return user;
    }

    @Override
    public boolean add(User user) {

        final String sql = "insert into user (username, password, passwordHash, gold) values (?,?,?,?);";

        return jdbcTemplate.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getPasswordHash(),
                user.getGold()) > 0;
    }

// Do we need to update?? we won't let users update their gold... username we could change, but it's our identifier,
// and password we could change but I'm not sure how difficult that would be.
//    @Override
//    public boolean update(User user) {
//
//        // not allowing user_id updates...
//        final String sql = "update user set "
//                + "gold = ? "
//                + "where username = ?;";
//
//        return jdbcTemplate.update(sql,
//                user.getGold(),
//                user.getUsername()) > 0;
//    }

    private void addPets(User user) {

        final String sql = "select pet_id, pet_name, hunger_lvl, care_lvl, "
                + "thirst_lvl, health_lvl, time_to_zero, is_dead, trophies "
                + "from pet "
                + "where username = ?";

        var pets = jdbcTemplate.query(sql, new PetMapper(), user.getUsername());
        user.setPets(pets);
    }

    private void addItems(User user) {

        final String sql = "select ui.username, ui.item_id, ui.quantity, "
                + "i.item_name, i.description, i.for_battle, i.price "
                + "from user_item ui "
                + "inner join item i on ui.item_id = i.item_id "
                + "where ui.username = ?";

        var userItems = jdbcTemplate.query(sql, new ItemMapper(), user.getUsername());
        user.setItems(userItems);
    }

}
