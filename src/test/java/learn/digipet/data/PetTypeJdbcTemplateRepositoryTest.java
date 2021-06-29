package learn.digipet.data;

import learn.digipet.models.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
class PetTypeJdbcTemplateRepositoryTest {

    @Autowired
    PetTypeJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set();}

    @Test
    void shouldFindFour() {
        List<PetType> petTypes = repository.findAll();
        assertNotNull(petTypes);
        assertEquals(4, petTypes.size());

        for (PetType p : petTypes) {
            System.out.println(p.getNextPetTypeId());
        }
    }

    @Test
    void shouldFind() {
        PetType expected = new PetType(1, "adult",100,100,100,100,0);
        PetType actual = repository.findById(1);
        assertEquals(expected, actual);
    }
}