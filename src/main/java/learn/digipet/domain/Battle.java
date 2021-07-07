package learn.digipet.domain;

import learn.digipet.data.PetJdbcTemplateRepository;
import learn.digipet.data.PetRepository;
import learn.digipet.models.Item;
import learn.digipet.models.Move;
import learn.digipet.models.Pet;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

public class Battle {

    @PositiveOrZero
    int battleId;
    Pet petA;
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

    //If the attacking pet has health < 10% their damage is 2 times as strong
    //if hunger/thirst/care are below 30% their attack will be 80%
    //if care is low their attack will be at 20%
    private void setPetAHealth(Move move) {
        double healthPercentage = ((double)this.petB.getHealthLevel())/((double)this.petB.getPetType().getHealth());
        double hungerPercentage = ((double)this.petB.getHungerLevel())/((double)this.petB.getPetType().getAppetite());
        double thirstPercentage = ((double)this.petB.getCareLevel())/((double)this.petB.getPetType().getCare());
        double carePercentage = ((double)this.petB.getCareLevel())/((double)this.petB.getPetType().getCare());
        double damage = move.getDamage();
        if(healthPercentage <= 0.1) {
            damage *=2;
        }

        //with this order, if care is high but hunger/thirst are low, then the damage will still be cut 20%
        //if hunger/thirst are low we still get only the cut based on care because care > hunger/thirst
        if(carePercentage <= 0.1) {
            damage*=0.1;
        } else if (carePercentage <= 0.3) {
            damage *=0.8;
        } else if(hungerPercentage <= 0.3 || thirstPercentage <= 0.3) {
            damage=0.8;
        } else if (carePercentage > 0.8) {
            damage *= 1.2;
        }
        this.petA.setHealthLevel((int)((double)this.petA.getHealthLevel() - damage));
    }

    private void setPetBHealth(Move move) {
        double healthPercentage = ((double)this.petA.getHealthLevel())/((double)this.petA.getPetType().getHealth());
        double hungerPercentage = ((double)this.petA.getHungerLevel())/((double)this.petA.getPetType().getAppetite());
        double thirstPercentage = ((double)this.petA.getCareLevel())/((double)this.petA.getPetType().getCare());
        double carePercentage = ((double)this.petA.getCareLevel())/((double)this.petA.getPetType().getCare());
        double damage = move.getDamage();
        if(healthPercentage <= 0.1) {
            damage *=2;
        }

        //with this order, if care is high but hunger/thirst are low, then the damage will still be cut 20%
        //if hunger/thirst are low we still get only the cut based on care because care > hunger/thirst
        if(carePercentage <= 0.1) {
            damage*=0.1;
        } else if (carePercentage <= 0.3) {
            damage *=0.8;
        } else if(hungerPercentage <= 0.3 || thirstPercentage <= 0.3) {
            damage=0.8;
        } else if (carePercentage > 0.8) {
            damage *= 1.2;
        }
        this.petB.setHealthLevel((int)((double)this.petB.getHealthLevel() - damage));
    }

    public int getBattleId() {
        return battleId;
    }

    public void setBattleId(int battleId) {
        this.battleId = battleId;
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
