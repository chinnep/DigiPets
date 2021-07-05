package learn.digipet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.digipet.data.ItemRepository;
import learn.digipet.models.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ItemControllerTest {

    @MockBean
    ItemRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    void shouldFindById() throws Exception {

        Item expected = new Item(1, "wrench","A description of a wrench.",
                true, false, false, false, false, 200, "");

        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(expected);

        when(repository.findById(2)).thenReturn(expected);

        mvc.perform(get("/items/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldNotFindMissingId() throws Exception {
        mvc.perform(get("/items/0"))
                .andExpect(status().isNotFound());
    }

}