package learn.digipet.data;

import learn.digipet.models.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
class MoveJdbcTemplateRepositoryTest {

    @Autowired
    MoveJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set();}

    @Test
    void shouldFindTwo() {
        List<Move> moves = repository.findAll();

        assertNotNull(moves);
        assertEquals(2, moves.size());
    }

    @Test
    void findById() {

        Move move = repository.findById(2);
        assertNotNull(move);
        assertEquals("Wet willy", move.getName());
        assertEquals(20, move.getDamage());
    }
}