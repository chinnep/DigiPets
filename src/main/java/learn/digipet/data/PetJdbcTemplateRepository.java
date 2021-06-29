package learn.digipet.data;

import learn.digipet.data.mappers.MoveMapper;
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
                "time_at_last_login, is_dead, trophies, user_id from pet;";
        return jdbcTemplate.query(sql, new PetMapper());
    }

    @Override
    @Transactional
    public Pet findById(int petId) {
        final String sql = "select pet_id, pet_name, hunger_lvl, care_lvl, thirst_lvl, health_lvl, " +
                "time_at_last_login, is_dead, trophies, user_id "
                + "from pet "
<<<<<<< HEAD
                + "where pet_id = ?";
=======
                + "where pet_id = ?; ";
>>>>>>> 76007d2074d06c0fc85839e6e306a7e72ff80314

        Pet result = jdbcTemplate.query(sql, new PetMapper(), petId).stream()
                .findAny().orElse(null);

        if (result != null) {
            addPetType(result);
            addMoves(result);
        }

        return result;
    }

    @Override
    public Pet add(Pet pet) {
        final String sql = "insert into pet(pet_name, hunger_lvl, care_lvl, thirst_lvl, health_lvl, "
                        +  " time_at_last_login, is_dead, trophies, pet_type_id, user_id) "
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
            ps.setBoolean(7, pet.isDead());
            ps.setInt(8, pet.getTrophies());
            ps.setInt(9, pet.getPetType().getPetTypeId());
            ps.setInt(10, pet.getUserId());
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
                + "is_dead = ?, "
                + "trophies = ?, "
                + "pet_type_id = ?, "
                + "user_id = ? "
                + "where pet_id = ?";

        return jdbcTemplate.update(sql,
                pet.getName(),
                pet.getHungerLevel(),
                pet.getCareLevel(),
                pet.getThirstLevel(),
                pet.getHealthLevel(),
                pet.getTimeAtLastLogin().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                pet.isDead(),
                pet.getTrophies(),
                pet.getPetType().getPetTypeId(),
                pet.getUserId(),
                pet.getPetId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int petId) {
        jdbcTemplate.update("delete from pet_move where pet_id = ?", petId);
        return jdbcTemplate.update("delete from pet where pet_id = ?", petId) > 0;
    }

    private void addPetType(Pet pet) {

        final String sql = "select pet_type_id, pet_type_name, appetite, care, thirst, health, next_pet_type_id "
                + "from pet_type "
                + "where pet_type_id = ?";

        var petType = jdbcTemplate.query(sql, new PetTypeMapper(), pet.getPetId())
                .stream()
                .findFirst()
                .orElse(null);
        pet.setPetType(petType);
    }

    private void addMoves(Pet pet) {

        final String sql = "select m.move_id, m.move_name, m.damage "
                + "from move m "
                + "inner join pet_move pm on m.move_id = pm.move_id "
                + "inner join pet p on p.pet_id = pm.pet_id "
                + "where p.pet_id = ?";

        var moves = jdbcTemplate.query(sql, new MoveMapper(), pet.getPetId());
        pet.setMoves(moves);
    }
}
