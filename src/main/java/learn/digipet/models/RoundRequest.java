package learn.digipet.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class RoundRequest {

    @PositiveOrZero(message = "battle id must be an integer greater than or equal to 0.")
    private final int battleId;
    @NotBlank(message = "PetA must send in a valid move.")
    private final Move moveA;
    @NotBlank(message = "PetB must send in a valid move.")
    private final boolean isPlayerA;

    public RoundRequest(int battleId, Move moveA, boolean isPlayerA) {
        this.battleId = battleId;
        this.moveA = moveA;
        this.isPlayerA = isPlayerA;
    }

    public int getBattleId() {
        return battleId;
    }

    public Move getMoveA() {
        return moveA;
    }

    public boolean getIsPlayerA() {
        return isPlayerA;
    }
}
