package learn.digipet.domain;

import learn.digipet.data.PetRepository;
import learn.digipet.models.Move;
import learn.digipet.models.Pet;
import learn.digipet.models.PetType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PetServiceTest {

    @Autowired
    PetService service;

    @MockBean
    PetRepository repository;

    @Test
    void findById() {
        Pet expected = makePet();
        when(repository.findById(1)).thenReturn(expected);
        Pet actual = service.findById(1);
        assertEquals(expected, actual);
    }
    
    @Test
    void shouldAdd() {
        Pet expected = makePet();
        Pet arg = makePet();

        when(repository.add(arg)).thenReturn(expected);
        Result<Pet> result = service.add(arg);
        assertEquals(ResultType.SUCCESS, result.getType());

        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotAddWithInvalidName() {
        Pet pet = makePet();
        pet.setName("");
        Result<Pet> result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());

        pet.setName(null);
        result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWithIdNotZero() {
        Pet pet = makePet();
        pet.setPetId(5);
        Result<Pet> result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWithLastLoginInFuture() {
        Pet pet = makePet();
        pet.setTimeAtLastLogin(LocalDateTime.now().plusDays(1));
        Result<Pet> result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWithLastLoginNotNull() {
        Pet pet = makePet();
        pet.setTimeAtLastLogin(LocalDateTime.now().minusDays(1));
        Result<Pet> result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWithNegativeTrophies() {
        Pet pet = makePet();
        pet.setTrophies(-1);
        Result<Pet> result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
    }
//
//    @Test
//    void shouldNotAddWithNoMoves() {
//        Pet pet = makePet();
//        pet.setMoves(new ArrayList<>());
//        Result<Pet> result = service.add(pet);
//        assertEquals(ResultType.INVALID, result.getType());
//
//    }
//
//    @Test
//    void shouldNotAddWithNullMoves() {
//        Pet pet = makePet();
//        pet.setMoves(null);
//        Result<Pet> result = service.add(pet);
//        assertEquals(ResultType.SUCCESS, result.getType());
//    }

    @Test
    void shouldNotAddWithInvalidUserId() {
        Pet pet = makePet();
        pet.setUsername("");
        Result<Pet> result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWithInvalidPetType() {
        Pet pet = makePet();
        pet.getPetType().setPetTypeId(0);
        Result<Pet> result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());

        pet.setPetType(null);
        result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWithNegativeHunger() {
        Pet pet = makePet();
        pet.setHungerLevel(-1);
        Result<Pet> result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWithNegativeCare() {
        Pet pet = makePet();
        pet.setCareLevel(-1);
        Result<Pet> result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWithNegativeThirst() {
        Pet pet = makePet();
        pet.setThirstLevel(-1);
        Result<Pet> result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWithNegativeHealth() {
        Pet pet = makePet();
        pet.setHealthLevel(-1);
        Result<Pet> result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
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