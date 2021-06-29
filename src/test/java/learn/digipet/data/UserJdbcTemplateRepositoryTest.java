package learn.digipet.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class UserJdbcTemplateRepositoryTest {

    @Autowired
    UserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void shouldFindAll() {
    }

    @Test
    void shouldFindById() {
    }

    @Test
    void shouldAdd() {
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

}
