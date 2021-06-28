package learn.digipet.models;

import java.time.LocalTime;
import java.util.List;

public class Pet {

    private int petId;
    private String name;
    private int hungerLevel;
    private int careLevel;
    private int thirstLevel;
    private int healthLevel;
    private LocalTime timeToZero;
    private List<String> trophies;
    private int petTypeId;
    private int userId;

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public void setHungerLevel(int hungerLevel) {
        this.hungerLevel = hungerLevel;
    }

    public int getCareLevel() {
        return careLevel;
    }

    public void setCareLevel(int careLevel) {
        this.careLevel = careLevel;
    }

    public int getThirstLevel() {
        return thirstLevel;
    }

    public void setThirstLevel(int thirstLevel) {
        this.thirstLevel = thirstLevel;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    public LocalTime getTimeToZero() {
        return timeToZero;
    }

    public void setTimeToZero(LocalTime timeToZero) {
        this.timeToZero = timeToZero;
    }

    public List<String> getTrophies() {
        return trophies;
    }

    public void setTrophies(List<String> trophies) {
        this.trophies = trophies;
    }

    public int getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(int petTypeId) {
        this.petTypeId = petTypeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
