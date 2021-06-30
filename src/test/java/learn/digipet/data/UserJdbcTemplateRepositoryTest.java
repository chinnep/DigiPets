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
    void shouldFindByUsername() {
        User expected = new User("username...");
        User actual = repository.findByUsername("username...");
        assertEquals(expected, actual);
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
        user.setUsername("abc123");
        user.setPassword("abc123");
        user.setPasswordHash("hash...");
        user.setGold(800);
        return user;
    }

}
