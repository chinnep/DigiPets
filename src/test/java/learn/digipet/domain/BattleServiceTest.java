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

//    @Test
//    void shouldCreateBattle() {
//        Pet petA = makePet();
//        Pet petB = makePet();
//        Item itemA = makeItem();
//        Item itemB = makeItem();
//        Battle expected = new Battle(petA, petB, itemA, itemB);
//        Result<Battle> result = service.createBattle(petA, petB, itemA, itemB);
//        assertEquals(ResultType.SUCCESS, result.getType());
//        assertTrue(expected.equals(result.getPayload()));
//    }
//
//    @Test
//    void shouldNotCreateBattle() {
//        Result<Battle> result = service.createBattle(makePet(), null, makeItem(), makeItem());
//        assertEquals(ResultType.INVALID, result.getType());
//
//        result = service.createBattle(null, makePet(), makeItem(), makeItem());
//        assertEquals(ResultType.INVALID, result.getType());
//    }
//
//    @Test
//    void shouldHaveOneRound() {
//        Result<Battle> result = service.createBattle(makePet(), makePet(), makeItem(), makeItem());
//        assertEquals(ResultType.SUCCESS, result.getType());
//
//        Battle battle;
//        do {
//            battle = service.round(result.result.getPayload().getPetA().getMoves().get(0), result.getPayload().getPetB().getMoves().get(0));
//        }while(battle.getPetA().getHealthLevel() > 0);
//
//        assertTrue(battle.getPetA().getHealthLevel() > 0);
//    }

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
        petType.setHealth(10);
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