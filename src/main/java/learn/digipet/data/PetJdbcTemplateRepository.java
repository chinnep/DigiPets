package learn.digipet.data;

import learn.digipet.data.mappers.MoveMapper;
import learn.digipet.data.mappers.PetMapper;
import learn.digipet.data.mappers.PetTypeMapper;
import learn.digipet.models.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
                "time_to_zero, is_dead, trophies, user_id from pet;";
        return jdbcTemplate.query(sql, new PetMapper());
    }

    @Override
    @Transactional
    public Pet findById(int petId) {
        final String sql = "select pet_id, pet_name, hunger_lvl, care_lvl, thirst_lvl, health_lvl, " +
                "time_to_zero, is_dead, trophies, user_id "
                + "from pet;"
                + "where pet_id = ?;";

        Pet result = jdbcTemplate.query(sql, new PetMapper(), petId).stream()
                .findAny().orElse(null);

        if (result != null) {
            addPetType(result);
            addMoves(result);
        }

        return result;
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

        var petType = jdbcTemplate.query(sql, new PetTypeMapper(), pet.getPetId())
                .stream()
                .findFirst()
                .orElse(null);
        pet.setPetType(petType);
    }

    private void addMoves(Pet pet) {

        final String sql = "select move_id, move_name, damage "
                + "from move m "
                + "inner join pet_move pm on m.move_id = pm.move_id "
                + "inner join pet p on p.pet_id = pm.pet_id "
                + "where p.pet_id = ?";

        var moves = jdbcTemplate.query(sql, new MoveMapper(), pet.getPetId());
        pet.setMoves(moves);
    }
}
