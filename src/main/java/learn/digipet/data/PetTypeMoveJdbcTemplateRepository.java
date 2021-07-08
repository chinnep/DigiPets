package learn.digipet.data;

import learn.digipet.data.mappers.MoveMapper;
import learn.digipet.models.Move;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetTypeMoveJdbcTemplateRepository implements PetTypeMoveRepository {

    private final JdbcTemplate jdbcTemplate;

    public PetTypeMoveJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Move> findByPetTypeId(int petTypeId) {
        final String sql = "select pet_type_move.move_id, move_name, damage from pet_type_move "
                + "inner join move on move.move_id = pet_type_move.move_id "
                + "where pet_type_id = ?;";

        return jdbcTemplate.query(sql, new MoveMapper(), petTypeId);
    }
}
