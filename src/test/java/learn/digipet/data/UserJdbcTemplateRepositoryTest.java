package learn.digipet.data;

import learn.digipet.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
class UserJdbcTemplateRepositoryTest {

    @Autowired
    UserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void shouldFindThreeOrMore() {
        System.out.println(repository.findAll());
        assertTrue(repository.findAll().size() >= 3);
    }

    @Test
    void shouldFindByUsername() {
        User abc123 = repository.findByUsername("abc123");
        assertEquals(1, abc123.getPets().size());
        assertEquals(1000, abc123.getGold());
    }

    @Test
    void shouldAdd() {
        User user = makeUser();
        assertTrue(repository.add(user));
        assertEquals(4, repository.findAll().size());
    }

    @Test
    void shouldUpdate() {
        User user = new User("dev10peeps");
        user.setGold(500);
        assertTrue(repository.updateGold(user));
        assertEquals(500, repository.findByUsername("dev10peeps").getGold());

        user.setUsername("bad");
        assertFalse(repository.updateGold(user));
    }

    private User makeUser() {
        User user = new User();
        user.setUsername("abc12345");
        user.setPassword("ketchup3000");
        user.setPasswordHash("$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQd");
        user.setGold(5000);
        return user;
    }

}
