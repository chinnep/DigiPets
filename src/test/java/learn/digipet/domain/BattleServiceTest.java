package learn.digipet.domain;

import learn.digipet.models.Item;
import learn.digipet.models.Move;
import learn.digipet.models.Pet;
import learn.digipet.models.PetType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BattleServiceTest {

    BattleService service = new BattleService();

    @Test
    void shouldAddBattle() {
        Battle arg = new Battle(makePet(), makePet(), makeItem(), makeItem());
        Result<Battle> result = service.addBattle(arg);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertTrue(result.getPayload().getBattleId() >= 0);
    }

    @Test
    void shouldNotCreateBattle() {
        Battle arg = new Battle(null, makePet(), makeItem(), makeItem());
        Result<Battle> result = service.addBattle(arg);
        assertEquals(ResultType.INVALID, result.getType());

        arg = new Battle(makePet(), null, makeItem(), makeItem());
        result = service.addBattle(arg);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldFindByBattleId() {
        //add and confirm a battle
        Battle arg = new Battle(makePet(), makePet(), makeItem(), makeItem());
        Result<Battle> result = service.addBattle(arg);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertTrue(result.getPayload().getBattleId() >= 0);
        arg.setBattleId(0);

        Battle expected = service.findByBattleId(0);
        assertEquals(arg, expected);
    }

    @Test
    void shouldNotFindByInvalidBattleId() {
        //add and confirm a battle
        Battle arg = new Battle(makePet(), makePet(), makeItem(), makeItem());
        Result<Battle> result = service.addBattle(arg);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertTrue(result.getPayload().getBattleId() >= 0);

        Battle expected = service.findByBattleId(-1);
        assertNull(expected);
    }

    @Test
    void PetAShouldWinWithSameDamage() {
        //add and confirm a battle
        Battle arg = new Battle(makePet(), makePet(), makeItem(), makeItem());
        Result<Battle> result = service.addBattle(arg);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertTrue(result.getPayload().getBattleId() >= 0);

        int battleId = result.getPayload().getBattleId();
        do {
            result = service.round(battleId,new Move(1, "Cool Move", 100),new Move(1, "Cool Move", 100));
        }while(result.isSuccess());

        assertTrue(service.findByBattleId(battleId).getPetA().getHealthLevel() > 0);
    }

    @Test
    void PetBShouldWinHigherDamageMoves() {
        //add and confirm a battle
        Battle arg = new Battle(makePet(), makePet(), makeItem(), makeItem());
        Result<Battle> result = service.addBattle(arg);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertTrue(result.getPayload().getBattleId() >= 0);

        int battleId = result.getPayload().getBattleId();
        do {
            result = service.round(battleId,new Move(1, "Cool Move", 10),new Move(1, "Cool Move", 90));
        }while(result.isSuccess());

        assertTrue(service.findByBattleId(battleId).getPetA().getHealthLevel() > 0);
    }

    @Test
    void shouldHaveNoRoundsWithNullMoves() {
        //add and confirm a battle
        Battle arg = new Battle(makePet(), makePet(), makeItem(), makeItem());
        Result<Battle> result = service.addBattle(arg);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertTrue(result.getPayload().getBattleId() >= 0);

        int battleId = result.getPayload().getBattleId();
        do {
            result = service.round(battleId,null, new Move(1, "Cool Move", 90));
        }while(result.isSuccess());

        assertEquals(result.getType(), ResultType.INVALID);

        do {
            result = service.round(battleId,new Move(1, "Cool Move", 90), null);
        }while(result.isSuccess());

        assertEquals(result.getType(), ResultType.INVALID);
    }

    Item makeItem() {
        return new Item(1, "wrench","A description of a wrench.", true, 200);
    }

    private Pet makePet() {
        Pet pet = new Pet();
        PetType petType = new PetType();
        petType.setPetTypeId(1);
        petType.setName("Type test");
        petType.setAppetite(10);
        petType.setCare(10);
        petType.setThirst(10);
        petType.setHealth(1000);
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
        pet.setHealthLevel(1000);
        pet.setDead(false);
        pet.setTrophies(0);
        pet.setPetType(petType);
        pet.setUsername("abc123");

        return pet;
    }
}