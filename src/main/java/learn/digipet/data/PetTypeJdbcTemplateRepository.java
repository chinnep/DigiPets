package learn.digipet.data;

import learn.digipet.data.mappers.MoveMapper;
import learn.digipet.data.mappers.PetTypeMapper;
import learn.digipet.models.Pet;
import learn.digipet.models.PetType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetTypeJdbcTemplateRepository implements PetTypeRepository {

    private final JdbcTemplate jdbcTemplate;

    public PetTypeJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PetType> findAll() {
        final String sql = "select pet_type_id, pet_type_name, appetite, care, thirst, health, next_pet_type_id" +
                " from pet_type;";
        return jdbcTemplate.query(sql, new PetTypeMapper());
    }

    @Override
    public PetType findById(int petTypeId) {

        final String sql = "select pet_type_id, pet_type_name, appetite, care, thirst, health, next_pet_type_id" +
                " from pet_type" +
                " where pet_type_id = ?;";

        PetType result = jdbcTemplate.query(sql, new PetTypeMapper(), petTypeId).stream()
                .findFirst().orElse(null);

        if (result != null) {
            addMoves(result);
        }

        return result;
    }

    private void addMoves(PetType petType) {

        final String sql = "select m.move_id, m.move_name, m.damage "
                + "from move m "
                + "inner join pet_type_move ptm on m.move_id = ptm.move_id "
                + "inner join pet_type pt on pt.pet_type_id = ptm.pet_type_id "
                + "where pt.pet_type_id = ?";

        var moves = jdbcTemplate.query(sql, new MoveMapper(), petType.getPetTypeId());
        petType.setMoves(moves);
    }
}
