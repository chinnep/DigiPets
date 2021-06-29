package learn.digipet.data;

import learn.digipet.models.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        pets.stream()
                .forEach(p -> System.out.println(p.getTimeAtLastLogin()));
        assertNotNull(pets);
        assertTrue(pets.size() > 0);
    }

}