package learn.digipet.data.mappers;

import learn.digipet.models.Move;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MoveMapper implements RowMapper<Move> {

    @Override
    public Move mapRow(ResultSet resultSet, int i) throws SQLException {
        Move move = new Move();

        move.setMoveId(resultSet.getInt("move_id"));
        move.setName(resultSet.getString("move_name"));
        move.setDamage(resultSet.getInt("damage"));

        return move;
    }
}