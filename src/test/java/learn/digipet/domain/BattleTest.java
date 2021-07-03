package learn.digipet.domain;

import learn.digipet.models.Move;
import learn.digipet.models.Pet;
import learn.digipet.models.PetType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    @Test
    void shouldResetPetBHealth() {
        Battle battle = new Battle(makePet(), makePet(), null, null);
        boolean result = battle.round(new Move(1, "Cool Move", 100), new Move(1, "Cool Move", 100));
        assertTrue(result);
        assertTrue(battle.getPetB().getHealthLevel() <= 0);
        assertTrue(battle.getPetB().getTrophies() < 1000);
    }

    @Test
    void shouldResetPetAHealth() {
        Battle battle = new Battle(makePet(), makePet(), null, null);
        boolean result = battle.round(new Move(1, "Cool Move", 90), new Move(1, "Cool Move", 100));
        assertTrue(result);
        assertTrue(battle.getPetA().getHealthLevel() <= 0);
        assertTrue(battle.getPetA().getTrophies() < 1000);
    }

    private Pet makePet() {
        Pet pet = new Pet();
        PetType petType = new PetType();
        petType.setPetTypeId(1);
        petType.setName("Type test");
        petType.setAppetite(10);
        petType.setCare(10);
        petType.setThirst(10);
        petType.setHealth(100);
        petType.setNextPetTypeId(2);
        Move move = new Move();
        move.setName("test move");
        move.setMoveId(1);
        move.setDamage(10);
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(move);
        pet.setMoves(moves);
        pet.setName("test pet");
        pet.setHungerLevel(100);
        pet.setCareLevel(100);
        pet.setThirstLevel(100);
        pet.setHealthLevel(100);
        pet.setDead(false);
        pet.setTrophies(1000);
        pet.setPetType(petType);
        pet.setUsername("abc123");

        return pet;
    }

}