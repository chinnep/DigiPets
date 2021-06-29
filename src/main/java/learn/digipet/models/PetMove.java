package learn.digipet.models;

import javax.validation.constraints.PositiveOrZero;

public class PetMove {

    @PositiveOrZero
    private int moveId;
    @PositiveOrZero
    private int petId;

    public PetMove(int moveId, int petId) {
        this.moveId = moveId;
        this.petId = petId;
    }

    public PetMove(){}

    public int getMoveId() {
        return moveId;
    }

    public void setMoveId(int moveId) {
        this.moveId = moveId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }


}
