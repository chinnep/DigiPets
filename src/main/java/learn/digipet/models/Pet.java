package learn.digipet.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
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
    @NotBlank
    private boolean isDead;
    @PositiveOrZero
    private int trophies;
    @NotBlank
    private PetType petType;
    private List<Move> moves;
    @PositiveOrZero
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

    public LocalDateTime getTimeAtLastLogin() {
        return timeAtLastLogin;
    }

    public void setTimeAtLastLogin(LocalDateTime timeAtLastLogin) {
        this.timeAtLastLogin = this.timeAtLastLogin;
    }

    public int getTrophies() {
        return trophies;
    }

    public void setTrophies(int trophies) {
        this.trophies = trophies;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
}
