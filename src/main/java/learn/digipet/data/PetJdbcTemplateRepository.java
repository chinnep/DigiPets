package learn.digipet.data;

import learn.digipet.data.mappers.PetMapper;
import learn.digipet.data.mappers.PetTypeMapper;
import learn.digipet.models.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class PetJdbcTemplateRepository implements PetRepository {

    private final JdbcTemplate jdbcTemplate;

    public PetJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pet> findAll() {
        final String sql = "select pet_id, pet_name, hunger_lvl, care_lvl, thirst_lvl, health_lvl, " +
                "time_at_last_login, birthday, is_dead, trophies, username, pet_type_id from pet;";
        return jdbcTemplate.query(sql, new PetMapper());
    }

    @Override
    @Transactional
    public Pet findById(int petId) {
        final String sql = "select pet_id, pet_name, hunger_lvl, care_lvl, thirst_lvl, health_lvl, " +
                "time_at_last_login, birthday, is_dead, trophies, username, pet_type_id "
                + "from pet "
                + "where pet_id = ?";

        Pet result = jdbcTemplate.query(sql, new PetMapper(), petId).stream()
                .findAny().orElse(null);

        if (result != null) {
            addPetType(result);
        }

        return result;
    }

    @Override
    public Pet add(Pet pet) {
        final String sql = "insert into pet(pet_name, hunger_lvl, care_lvl, thirst_lvl, health_lvl, "
                        +  " time_at_last_login, birthday, is_dead, trophies, pet_type_id, username) "
                        +  " values (?,?,?,?,?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pet.getName());
            ps.setInt(2, pet.getHungerLevel());
            ps.setInt(3, pet.getCareLevel());
            ps.setInt(4, pet.getThirstLevel());
            ps.setInt(5, pet.getHealthLevel());
            ps.setString(6, pet.getTimeAtLastLogin().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            ps.setString(7, pet.getBirthday().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            ps.setBoolean(8, pet.isDead());
            ps.setInt(9, pet.getTrophies());
            ps.setInt(10, pet.getPetType().getPetTypeId());
            ps.setString(11, pet.getUsername());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        pet.setPetId(keyHolder.getKey().intValue());
        return pet;
    }

    @Override
    public boolean update(Pet pet) {
        final String sql = "update pet set "
                + "pet_name = ?, "
                + "hunger_lvl = ?, "
                + "care_lvl = ?, "
                + "thirst_lvl = ?, "
                + "health_lvl = ?, "
                + "time_at_last_login = ?, "
                + "birthday = ?, "
                + "is_dead = ?, "
                + "trophies = ?, "
                + "pet_type_id = ?, "
                + "username = ? "
                + "where pet_id = ?";

        //calculating things to determine evolution.
        // going to be determined by health stats average with care being worth 2x as much
        // need to determine line of evolution....

        return jdbcTemplate.update(sql,
                pet.getName(),
                pet.getHungerLevel(),
                pet.getCareLevel(),
                pet.getThirstLevel(),
                pet.getHealthLevel(),
                pet.getTimeAtLastLogin().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                pet.getBirthday().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                pet.isDead(),
                pet.getTrophies(),
                pet.getPetType().getPetTypeId(),
                pet.getUsername(),
                pet.getPetId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int petId) {
        return jdbcTemplate.update("delete from pet where pet_id = ?", petId) > 0;
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

}
