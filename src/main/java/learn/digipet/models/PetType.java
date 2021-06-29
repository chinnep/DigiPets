package learn.digipet.models;

import java.util.Objects;

public class PetType {

    private int petTypeId;
    private String name;
    private int appetite;
    private int care;
    private int health;
    private int thirst;
    private int nextPetTypeId;

    public PetType(int petTypeId, String name, int appetite, int care, int health, int thirst, int nextPetTypeId) {
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

    public int getAppetite() {
        return appetite;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }

    public int getCare() {
        return care;
    }

    public void setCare(int care) {
        this.care = care;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getThirst() {
        return thirst;
    }

    public void setThirst(int thirst) {
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
