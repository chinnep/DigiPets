package learn.digipet.data;

import learn.digipet.models.UserItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserItemJdbcTemplateRepositoryTest {

    @Autowired
    UserItemJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set();}

    @Test
    void shouldFindTwo() {
        List<UserItem> result = repository.findByUsername("abc123");
        assertEquals(2, result.size());
    }

    @Test
    void shouldFindFiveBiscuits () {
        UserItem result = repository.findByIds(new UserItem(1,2,0));
        assertEquals(5, result.getQuantity());
    }

    @Test
    void shouldAdd() {
        UserItem toAdd = new UserItem("dev10peeps",2,1);
        assertTrue(repository.add(toAdd));
    }

    @Test
    void shouldUpdateTo11() {
        UserItem toUpdate = new UserItem("tamagotchi-gang",2,11);
        assertTrue(repository.update(toUpdate));
    }
}