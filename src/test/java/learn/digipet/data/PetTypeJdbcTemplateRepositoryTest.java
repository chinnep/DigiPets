package learn.digipet.data;

import learn.digipet.models.Move;
import learn.digipet.models.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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
    }

    @Test
    void shouldFind() {
        Move move = new Move();
        move.setName("test move");
        move.setMoveId(1);
        move.setDamage(10);
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(move);
        PetType expected = new PetType(1, "adult",100,100,100,100,moves, 0);
        PetType actual = repository.findById(1);
        assertEquals(expected, actual);
    }
}