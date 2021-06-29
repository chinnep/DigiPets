package learn.digipet.data;

import learn.digipet.models.PetMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
class PetMoveJdbcTemplateRepositoryTest {

    @Autowired
    PetMoveJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set();}

    @Test
    void shouldAdd() {
        PetMove pm = new PetMove(2,3);
        assertTrue(repository.add(pm));
    }
}