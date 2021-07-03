package learn.digipet.domain;

import learn.digipet.models.Item;
import learn.digipet.models.Move;
import learn.digipet.models.Pet;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

public class Battle {

    @PositiveOrZero
    int battleId;
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

    public boolean round(Move moveA, Move moveB) {

        setPetBHealth(moveA);
        if(this.petB.getHealthLevel() <= 0) {
            calculateTrophies(1, 0);
            return true;
        }

        setPetAHealth(moveB);
        if(this.petA.getHealthLevel() <= 0) {
            calculateTrophies(0, 1);
            return true;
        }

        //if neither are at or below zero no one has won the battle yet
        return false;
    }

    //1 is win and 0 is loss
    private void calculateTrophies(int matchStatusA, int matchStatusB) {
        double startRankingPetA = (double)this.petA.getTrophies();
        double startRankingPetB = (double)this.petB.getTrophies();

        this.petA.setTrophies((int)(startRankingPetA + 32 * (matchStatusA - (startRankingPetA/(startRankingPetA + startRankingPetB)))));
        this.petB.setTrophies((int)(startRankingPetB + 32 * (matchStatusB - (startRankingPetB/(startRankingPetA + startRankingPetB)))));
    }

    private void setPetAHealth(Move move) {
        this.petA.setHealthLevel(this.petA.getHealthLevel() - move.getDamage());
    }

    public int getBattleId() {
        return battleId;
    }

    public void setBattleId(int battleId) {
        this.battleId = battleId;
    }

    private void setPetBHealth(Move move) {
        this.petB.setHealthLevel(this.petB.getHealthLevel() - move.getDamage());
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
