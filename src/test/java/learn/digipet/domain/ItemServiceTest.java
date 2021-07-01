package learn.digipet.domain;

import learn.digipet.data.ItemRepository;
import learn.digipet.models.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ItemServiceTest {

    @Autowired
    ItemService service;

    @MockBean
    ItemRepository repository;

    @Test
    void findById() {
        Item expected = new Item(1, "wrench","A description of a wrench.", true, 200);
        when(repository.findById(1)).thenReturn(expected);
        Item actual = service.findById(1);
        assertEquals(expected, actual);
    }
}