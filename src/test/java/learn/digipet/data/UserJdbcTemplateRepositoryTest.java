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
    void shouldFind1ByUsername() {
        User abc123 = repository.findByUsername("abc123");
        assertEquals("abc123", abc123.getUsername());
        assertEquals(1000, abc123.getGold());
    }

    @Test
    void shouldAdd() {
        User user = makeUser();
        assertTrue(repository.add(user));
        assertEquals(4, repository.findAll().size());
    }

//    @Test
//    void shouldUpdate() {
//        User user = makeUser();
//        user.setUsername("existing user...");
//        assertTrue(repository.update(user));
//
//        user.setUsername("non-existing user...");
//        assertFalse(repository.update(user));
//
//    }

    private User makeUser() {
        User user = new User();
        user.setUsername("abc12345");
        user.setPassword("ketchup3000");
        user.setPasswordHash("$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQd");
        user.setGold(5000);
        return user;
    }

}
