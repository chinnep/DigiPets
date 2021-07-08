package learn.digipet.domain;

import learn.digipet.models.Move;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BattleMoveHandler {

    private final BattleService service;
    private final HashMap<Integer, Move[]> moveStorage = new HashMap<>();

    public BattleMoveHandler(BattleService service) {
        this.service = service;
    }

    public Result<Battle> enterMove(int battleId, Move move, boolean isPetA) {

        Move[] moves = new Move[2];
        Result<Battle> result = new Result<>();

        if (moveStorage.containsKey(battleId)) {
            moves = moveStorage.get(battleId);

            moves[isPetA ? 0 : 1] = move;


            if (moves[0] != null && moves[1] != null) {
                moveStorage.remove(battleId);
                return service.round(battleId, moves[0], moves[1]);
            }

        } else {
            for (int i = 0; i < moves.length; i++) {
                moves[i] = null;
            }

            moves[isPetA ? 0 : 1] = move;

            moveStorage.put(battleId, moves);
        }

        result.setPayload(service.findById(battleId));
        return result;
    }
}
