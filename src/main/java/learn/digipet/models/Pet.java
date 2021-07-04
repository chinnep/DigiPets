package learn.digipet.models;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

public class Pet {

    private int petId;
    @NotBlank
    private String name;
    @PositiveOrZero
    private int hungerLevel;
    @PositiveOrZero
    private int careLevel;
    @PositiveOrZero
    private int thirstLevel;
    @PositiveOrZero
    private int healthLevel;
    private LocalDateTime timeAtLastLogin;
    private boolean isDead;
    @PositiveOrZero
    private int trophies;
    @NotNull
    private PetType petType;
    @NotBlank
    private String username;

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

    public LocalDateTime getTimeAtLastLogin() {
        return timeAtLastLogin;
    }

    public void setTimeAtLastLogin(LocalDateTime timeAtLastLogin) {
        this.timeAtLastLogin = timeAtLastLogin;
    }

    public int getTrophies() {
        return trophies;
    }

    public void setTrophies(int trophies) {
        this.trophies = trophies;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", hungerLevel=" + hungerLevel +
                ", careLevel=" + careLevel +
                ", thirstLevel=" + thirstLevel +
                ", healthLevel=" + healthLevel +
                ", timeAtLastLogin=" + timeAtLastLogin +
                ", isDead=" + isDead +
                ", trophies=" + trophies +
                ", petType=" + petType +
                ", userId=" + username +
                '}';
    }
}
