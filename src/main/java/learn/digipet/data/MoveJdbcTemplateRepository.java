package learn.digipet.data;

import learn.digipet.data.mappers.MoveMapper;
import learn.digipet.models.Move;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Moves will be a set list inserted into sql directly -- no need for adds, deletes, updates
 * findById to check if special item move has been unlocked
 * findAll ?
 */
@Repository
public class MoveJdbcTemplateRepository implements MoveRepository {

    private final JdbcTemplate jdbcTemplate;

    public MoveJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Move> findAll() {
        final String sql = "select move_id, move_name, damage from move;";
        return jdbcTemplate.query(sql, new MoveMapper());
    }

    @Override
    public Move findById(int moveId) {

        final String sql = "select move_id, move_name, damage from move "
                + "where move_id = ?;";

        Move move = jdbcTemplate.query(sql, new MoveMapper(), moveId).stream()
                .findFirst().orElse(null);

        return move;
    }
}
