package learn.digipet.data;

import learn.digipet.models.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
class PetTypeMoveJdbcTemplateRepositoryTest {

    @Autowired
    PetTypeMoveJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set();}

    @Test
    void shouldFindMoves() {
        List<Move> moves = repository.findByPetTypeId(3);
        assertEquals(1, moves.size());

        List<Move> fakeMoves = repository.findByPetTypeId(14);
        assertFalse(fakeMoves.size() > 0);
    }
}