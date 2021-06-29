package learn.digipet.data;

import learn.digipet.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
class ItemJdbcTemplateRepositoryTest {

    @Autowired
    ItemJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set();}

    @Test
    void shouldFindTwo() {
        List<Item> items = repository.findAll();
        assertNotNull(items);
        assertEquals(2, items.size());
    }

    @Test
    void shouldFindSledgehammer() {
        Item expected = new Item();
        expected.setItemId(1);
        expected.setName("Sledgehammer");
        expected.setDescription("A sledgehammer is a tool with a large, flat, often metal head, attached to a long handle. \n" +
                "        The long handle combined with a heavy head allows the sledgehammer to gather momentum during a swing and apply\n" +
                "        a large force compared to hammers designed to drive nails.");
        expected.setForBattle(true);
        expected.setPrice(200);

        Item actual = repository.findById(1);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}