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
    void shouldFindById() {
        User expected = new User(1, 1000);
        User actual = repository.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        User user = makeUser();
        assertTrue(repository.add(user));
        assertEquals(4, repository.findAll().size());
    }

    @Test
    void shouldUpdate() {
        User user = makeUser();
        user.setUserId(2);
        assertTrue(repository.update(user));

        user.setUserId(15);
        assertFalse(repository.update(user));

    }

//    @Test
//    void shouldUpdate() {
//        Agent agent = makeAgent();
//        agent.setAgentId(3);
//        assertTrue(repository.update(agent));
//        agent.setAgentId(13);
//        assertFalse(repository.update(agent));
//    }

    private User makeUser() {
        User user = new User();
        user.setUserId(4);
        user.setGold(800);
        return user;
    }

}
