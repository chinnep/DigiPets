package learn.digipet.data;

import learn.digipet.models.Move;

import java.util.List;

/**
 * Moves will be a set list inserted into sql directly -- no need for adds, deletes, updates
 */
public interface MoveRepository {

    List<Move> findAll();

    Move findById(int moveId);
}
