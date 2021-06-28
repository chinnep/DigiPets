package learn.digipet.models;

public class PetType {

    private int petTypeId;
    private String name;
    private int appetite;
    private int care;
    private int health;
    private int thirst;
    private int nextPetTypeId;

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

}
