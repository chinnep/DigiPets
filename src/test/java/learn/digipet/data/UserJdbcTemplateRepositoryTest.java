package learn.digipet.data;

import learn.digipet.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
class UserJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 4;

    @Autowired
    UserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void shouldFindAll() {
        List<User> users = repository.findAll();
        assertNotNull(users);
        assertEquals(3, users.size());
    }

    @Test
    void shouldFindById() {
        User expected = new User(1, 1000);
        User actual = repository.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        User user = makeUser();
        User actual = repository.add(user);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getUserId());
    }

    @Test
    void shouldNotAdd() {
    }

    @Test
    void shouldUpdate() {
    }

    @Test
    void shouldNotUpdate() {
    }

    @Test
    void shouldDeleteById() {
    }

    @Test
    void shouldNotDeleteById() {
    }

    private User makeUser() {
        User user = new User();
        user.setGold(500);
        return user;
    }

}
