package learn.digipet.domain;

import learn.digipet.models.Item;
import learn.digipet.models.Move;
import learn.digipet.models.Pet;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

public class Battle {

    @PositiveOrZero
    int GameId;
    @NotBlank(message= "Need 2 pets to battle.")
    Pet petA;
    @NotBlank(message= "Need 2 pets to battle.")
    Pet petB;
    //currently not requiring items to battle
    Item itemA;
    Item itemB;

    public Battle() {
    }

    public Battle(Pet petA, Pet petB, Item itemA, Item itemB) {
        this.petA = petA;
        this.petB = petB;
        this.itemA = itemA;
        this.itemB = itemB;
    }

    public Battle round(Battle battle, Move moveA, Move moveB) {

        battle.setPetAHealth(moveB);
        if(battle.getPetA().getHealthLevel() <= 0) return battle;

        battle.setPetBHealth(moveA);
        //if petB is at 0 we return anyways so....
        return battle;
    }

    private void setPetAHealth(Move move) {
        petA.setHealthLevel(petA.getHealthLevel() - move.getDamage());
    }

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int gameId) {
        GameId = gameId;
    }

    private void setPetBHealth(Move move) {
        petB.setHealthLevel(petA.getHealthLevel() - move.getDamage());
    }

    public Pet getPetA() {
        return petA;
    }

    public void setPetA(Pet petA) {
        this.petA = petA;
    }

    public Pet getPetB() {
        return petB;
    }

    public void setPetB(Pet petB) {
        this.petB = petB;
    }

    public Item getItemA() {
        return itemA;
    }

    public void setItemA(Item itemA) {
        this.itemA = itemA;
    }

    public Item getItemB() {
        return itemB;
    }

    public void setItemB(Item itemB) {
        this.itemB = itemB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battle battle = (Battle) o;
        return Objects.equals(petA, battle.petA) && Objects.equals(petB, battle.petB) && Objects.equals(itemA, battle.itemA) && Objects.equals(itemB, battle.itemB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petA, petB, itemA, itemB);
    }
}
