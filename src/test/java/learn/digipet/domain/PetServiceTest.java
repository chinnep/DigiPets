package learn.digipet.domain;

import learn.digipet.data.PetRepository;
import learn.digipet.models.Pet;
import learn.digipet.models.PetType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
    void shouldNotAddWithNullName() {
        Pet Pet = makePet();
        Result<Pet> result = service.add(Pet);
        assertEquals(ResultType.INVALID, result.getType());

        Pet.setPetId(0);
        Pet.setName(null);
        result = service.add(Pet);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWithIdNotZero() {
        Pet Pet = makePet();
        Result<Pet> result = service.add(Pet);
        assertEquals(ResultType.INVALID, result.getType());

        Pet.setPetId(5);
        result = service.add(Pet);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWhenValid() {
        Pet expected = makePet();
        Pet arg = makePet();
        arg.setPetId(0);

        when(repository.add(arg)).thenReturn(expected);
        Result<Pet> result = service.add(arg);
        assertEquals(ResultType.SUCCESS, result.getType());

        assertEquals(expected, result.getPayload());
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
        pet.setName("test pet");
        pet.setHungerLevel(100);
        pet.setCareLevel(100);
        pet.setThirstLevel(100);
        pet.setHealthLevel(1000);
        pet.setTimeAtLastLogin(LocalDateTime.now());
        pet.setDead(false);
        pet.setTrophies(0);
        pet.setPetType(petType);
        pet.setUserId(1);

        return pet;
    }

}