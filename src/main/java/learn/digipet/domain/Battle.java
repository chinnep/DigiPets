package learn.digipet.domain;

import learn.digipet.data.PetJdbcTemplateRepository;
import learn.digipet.data.PetRepository;
import learn.digipet.models.Item;
import learn.digipet.models.Move;
import learn.digipet.models.Pet;
import learn.digipet.domain.PetTypeMoveService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Battle {

    @PositiveOrZero
    int battleId;
    Pet petA;
    Pet petB;
    Pet winner;
    //currently not requiring items to battle
    Item itemA;
    Item itemB;
    int music;
    List<String> battleLog = new ArrayList<>();

    public Battle() {
    }

    public Battle(Pet petA, Pet petB, Item itemA, Item itemB) {
        this.petA = petA;
        this.petB = petB;
        this.itemA = itemA;
        this.itemB = itemB;
    }

    public boolean round(Move moveA, Move moveB) {

        battleLog.add(this.petA.getName()+" uses "+moveA.getName()+"!");
        this.petB.setHealthLevel(calculateNewHealth(petB, moveA));
        if(this.petB.getHealthLevel() <= 0) {
            calculateTrophies(1, 0);

            setWinner(this.petA);
            return true;
        }

        battleLog.add(this.petB.getName()+" uses "+moveB.getName()+"!");
        this.petA.setHealthLevel(calculateNewHealth(petA, moveB));
        if(this.petA.getHealthLevel() <= 0) {
            calculateTrophies(0, 1);
            setWinner(this.petB);
            return true;
        }

        //if neither are at or below zero no one has won the battle yet
        return false;
    }

    //1 is win and 0 is loss
    private void calculateTrophies(int matchStatusA, int matchStatusB) {
        double startRankingPetA = (double)this.petA.getTrophies();
        double startRankingPetB = (double)this.petB.getTrophies();

        double aGain = 32 * (matchStatusA - (startRankingPetA/(startRankingPetA + startRankingPetB)));
        double bGain = 32 * (matchStatusB - (startRankingPetB/(startRankingPetA + startRankingPetB)));

        this.battleLog.add(this.petA.getName() + " gains " + aGain + " trophies!");
        this.battleLog.add(this.petB.getName() + " gains " + bGain + " trophies.");

        this.petA.setTrophies((int)(startRankingPetA + aGain));
        this.petB.setTrophies((int)(startRankingPetB + bGain));
    }

    //If the attacking pet has health < 10% their damage is 2 times as strong
    //if hunger/thirst/care are below 30% their attack will be 80%
    //if care is low their attack will be at 20%
    private int calculateNewHealth(Pet pet, Move move) {
        double healthPercentage = ((double)pet.getHealthLevel())/((double)pet.getPetType().getHealth());
        double hungerPercentage = ((double)pet.getHungerLevel())/((double)pet.getPetType().getAppetite());
        double thirstPercentage = ((double)pet.getCareLevel())/((double)pet.getPetType().getCare());
        double carePercentage = ((double)pet.getCareLevel())/((double)pet.getPetType().getCare());
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

        damage = damage*(Math.random() + 0.2);
        battleLog.add(pet.getName()+" loses "+(int)damage+" health.");
        return (int)((double)pet.getHealthLevel() - damage);
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

    public Pet getWinner() {
        return winner;
    }

    public void setWinner(Pet winner) {
        this.winner = winner;
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

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public List<String> getBattleLog() {
        return battleLog;
    }

    public void setBattleLog(List<String> battleLog) {
        this.battleLog = battleLog;
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

    @Override
    public String toString() {
        return "Battle{" +
                "battleId=" + battleId +
                ", petA=" + petA +
                ", petB=" + petB +
                ", itemA=" + itemA +
                ", itemB=" + itemB +
                '}';
    }
}
