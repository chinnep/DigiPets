package learn.digipet.data;

import learn.digipet.models.Pet;
import learn.digipet.models.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
class PetJdbcTemplateRepositoryTest {

    @Autowired
    PetJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Pet> pets = repository.findAll();
        assertNotNull(pets);
        assertTrue(pets.size() > 0);
    }

    @Test
    void shouldFindRick() {
        Pet pet = repository.findById(1);
        assertNotNull(pet);
        assertEquals(1, pet.getPetId());
        assertEquals("Rick Sanchez", pet.getName());
        assertEquals(pet.getPetType().getClass().getName(), "learn.digipet.models.PetType");
    }

    @Test
    void shouldDeleteById() {
        assertTrue(repository.deleteById(3));
    }

    @Test
    void shouldAddPet() {
        Pet pet = makePet();
        Pet actual = repository.add(pet);
        assertNotNull(actual);
        assertEquals(4, actual.getPetId());
    }

    @Test
    void shouldUpdatePet() {

        Pet pet = makePet();
        pet.setPetId(1);

        assertTrue(repository.update(pet));
    }

    @Test
    void shouldDeleteAgency() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
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
        pet.setUsername("abc123");

        return pet;
    }
}