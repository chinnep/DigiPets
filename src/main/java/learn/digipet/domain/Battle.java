package learn.digipet.domain;

import learn.digipet.models.Item;
import learn.digipet.models.Move;
import learn.digipet.models.Pet;

import javax.validation.constraints.NotBlank;

public class Battle {

    @NotBlank(message= "Need 2 pets to battle.")
    Pet petA;
    @NotBlank(message= "Need 2 pets to battle.")
    Pet petB;
    Item itemA;
    Item itemB;

    public Battle() {
    }

    public void setPetAHealth(Move move) {
        petA.setHealthLevel(petA.getHealthLevel() - move.getDamage());
    }

    public void setPetBHealth(Move move) {
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
}
