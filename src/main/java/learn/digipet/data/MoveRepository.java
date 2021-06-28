package learn.digipet.data;

import learn.digipet.models.Move;

import java.util.List;

public interface MoveRepository {

    List<Move> findAll();

    Move findById(int moveId);
}
