package learn.digipet.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

public class PetType {

    private int petTypeId;
    @NotBlank
    private String name;
    @PositiveOrZero
    private double appetite;
    @PositiveOrZero
    private double care;
    @PositiveOrZero
    private double health;
    @PositiveOrZero
    private double thirst;
    private int nextPetTypeId;

    public PetType(int petTypeId, String name, double appetite, double care, double health, double thirst, int nextPetTypeId) {
        this.petTypeId = petTypeId;
        this.name = name;
        this.appetite = appetite;
        this.care = care;
        this.health = health;
        this.thirst = thirst;
        this.nextPetTypeId = nextPetTypeId;
    }

    public PetType() {}

    public int getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(int petTypeId) {
        this.petTypeId = petTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAppetite() {
        return appetite;
    }

    public void setAppetite(double appetite) {
        this.appetite = appetite;
    }

    public double getCare() {
        return care;
    }

    public void setCare(double care) {
        this.care = care;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getThirst() {
        return thirst;
    }

    public void setThirst(double thirst) {
        this.thirst = thirst;
    }

    public int getNextPetTypeId() {
        return nextPetTypeId;
    }

    public void setNextPetTypeId(int nextPetTypeId) {
        this.nextPetTypeId = nextPetTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetType petType = (PetType) o;
        return petTypeId == petType.petTypeId && appetite == petType.appetite && care == petType.care && health == petType.health && thirst == petType.thirst && nextPetTypeId == petType.nextPetTypeId && Objects.equals(name, petType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petTypeId, name, appetite, care, health, thirst, nextPetTypeId);
    }
}
