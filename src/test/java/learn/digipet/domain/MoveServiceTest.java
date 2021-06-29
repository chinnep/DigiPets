package learn.digipet.domain;

import learn.digipet.data.MoveRepository;
import learn.digipet.models.Move;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MoveServiceTest {

    @Autowired
    MoveService service;

    @MockBean
    MoveRepository repository;

    @Test
    void findById() {
        Move expected = new Move(1, "Cool Move", 100);
        when(repository.findById(1)).thenReturn(expected);
        Move actual = service.findById(1);
        assertEquals(expected, actual);
    }
}