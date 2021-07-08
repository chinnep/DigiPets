package learn.digipet.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class RoundRequest {

    @PositiveOrZero(message = "battle id must be an integer greater than or equal to 0.")
    private final int battleId;
    @NotBlank(message = "PetA must send in a valid move.")
    private final Move move;
    @NotBlank(message = "PetB must send in a valid move.")
    private final boolean isPlayerA;

    public RoundRequest(int battleId, Move move, boolean isPlayerA) {
        this.battleId = battleId;
        this.move = move;
        this.isPlayerA = isPlayerA;
    }

    public int getBattleId() {
        return battleId;
    }

    public Move getMoveA() {
        return move;
    }

    public boolean getIsPlayerA() {
        return isPlayerA;
    }

    @Override
    public String toString() {
        return "RoundRequest{" +
                "battleId=" + battleId +
                ", move=" + move +
                ", isPlayerA=" + isPlayerA +
                '}';
    }
}
