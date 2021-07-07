package learn.digipet.data;

import learn.digipet.data.mappers.*;
import learn.digipet.models.Pet;
import learn.digipet.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public HashSet<String> findAll() {

        final String sql = "select username from user;";

        List<String> result = jdbcTemplate.query(sql,
                (rs, rowNum) ->
                    new String(rs.getString("username")));

        return new HashSet<String>(result);
    }

    @Override
    public User findByUsername(String username) {

        final String sql = "select username, password_hash, gold "
                + "from user "
                + "where username = ?;";


        User user = jdbcTemplate.query(sql, new UserMapper(), username).stream()
                .findFirst().orElse(null);

        if (user != null) {
            addItems(user);
            addPets(user);
        }

        return user;
    }

    @Override
    public boolean add(User user) {

        final String sql = "insert into user (username, password_hash, gold) values (?,?,?);";

        return jdbcTemplate.update(sql,
                user.getUsername(),
                user.getPasswordHash(),
                user.getGold()) > 0;
    }

    @Override
    public boolean updateGold(User user) {

        final String sql = "update user set "
                + "gold = ? "
                + "where username = ?;";

        return jdbcTemplate.update(sql,
                user.getGold(),
                user.getUsername()) > 0;
    }

    private void addPets(User user) {

        final String sql = "select pet_id, pet_name, hunger_lvl, care_lvl, "
                + "thirst_lvl, health_lvl, time_at_last_login, is_dead, trophies, pet_type_id, username "
                + "from pet "
                + "where username = ?";

        var pets = jdbcTemplate.query(sql, new PetMapper(), user.getUsername());

        pets.stream().forEach(p -> addPetType(p));
        user.setPets(pets);
    }

    private void addPetType(Pet pet) {

        final String sql = "select pet_type_id, pet_type_name, appetite, care, thirst, health, next_pet_type_id "
                + "from pet_type "
                + "where pet_type_id = ?";

        var petType = jdbcTemplate.query(sql, new PetTypeMapper(), pet.getPetType().getPetTypeId())
                .stream()
                .findFirst()
                .orElse(null);
        pet.setPetType(petType);
    }

    private void addItems(User user) {

        final String sql = "select ui.username, ui.item_id, ui.quantity, "
                + "i.item_name, i.description, i.price, "
                + "i.for_battle, i.for_food, i.for_water, i.for_care, i.for_health, i.img_url "
                + "from user_item ui "
                + "inner join item i on ui.item_id = i.item_id "
                + "where ui.username = ?";

        var userItems = jdbcTemplate.query(sql, new ItemQuantityMapper(), user.getUsername());
        user.setItems(userItems);
    }

}
