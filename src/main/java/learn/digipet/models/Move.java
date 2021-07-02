package learn.digipet.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class Move {

    @PositiveOrZero
    private int moveId;
    @NotBlank
    private String name;
    @PositiveOrZero
    private int damage;

    public Move (int moveId, String name, int damage) {
        this.moveId = moveId;
        this.name = name;
        this.damage = damage;
    }

    public Move() {}

    public int getMoveId() {
        return moveId;
    }

    public void setMoveId(int moveId) {
        this.moveId = moveId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

