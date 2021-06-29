package learn.digipet.data;

import learn.digipet.models.PetMove;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * Pet Move Jdbc repository is only used to add moves to pets
 * No editing of moves, deleting moves, and no need to read
 *  (reading attached moves will be handled by PetRepo)
 */
@Repository
public class PetMoveJdbcTemplateRepository implements PetMoveRepository {

    private final JdbcTemplate jdbcTemplate;

    public PetMoveJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add (PetMove petMove) {

        final String sql = "insert into pet_move (move_id, pet_id) values "
                + "(?,?);";

        return jdbcTemplate.update(sql,
                petMove.getMoveId(),
                petMove.getPetId()) > 0;
    }
}