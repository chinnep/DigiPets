package learn.digipet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.digipet.data.PetRepository;
import learn.digipet.models.Move;
import learn.digipet.models.Pet;
import learn.digipet.models.PetType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class PetControllerTest {
    
    @MockBean
    PetRepository repository;
    
    @Autowired
    MockMvc mvc;

    @Test
    void addShouldReturn201() throws Exception {
        Pet toAdd = makePet();
        Pet expected = makePet();

        when(repository.add(any())).thenReturn(expected);

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonIn = jsonMapper.writeValueAsString(toAdd);
        String expectedJson = jsonMapper.writeValueAsString(expected);

        RequestBuilder requestBuilder = post("/pet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void addShouldReturn400WithNullName() throws Exception {
        Pet toAdd = makePet();
        toAdd.setName(null);

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonIn = jsonMapper.writeValueAsString(toAdd);

        RequestBuilder requestBuilder = post("/pet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void addShouldReturn400WhenEmpty() throws Exception {

        var request = post("/pet")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn400WhenInvalid() throws Exception {

        ObjectMapper jsonMapper = new ObjectMapper();

        Pet alias = new Pet();
        String agencyJson = jsonMapper.writeValueAsString(alias);

        var request = post("/pet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(agencyJson);

        mvc.perform(request)
                .andExpect(status().isBadRequest());

    }

    @Test
    void shouldNotAddBlankName() throws Exception {
        Pet toAdd = makePet();
        toAdd.setName("");

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonIn = jsonMapper.writeValueAsString(toAdd);

        RequestBuilder requestBuilder = post("/pet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldUpdate() throws Exception {
        Pet toUpdate = makePet();
        toUpdate.setPetId(1);

        when(repository.update(any())).thenReturn(true);

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonIn = jsonMapper.writeValueAsString(toUpdate);

        RequestBuilder requestBuilder = put("/pet/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(requestBuilder)
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldNotUpdateInvalid() throws Exception {

        RequestBuilder requestBuilder = put("/pet/1")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldDelete() throws Exception {
        int toDelete = 1;

        when(repository.deleteById(toDelete)).thenReturn(true);

        RequestBuilder requestBuilder = delete("/pet/1")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(requestBuilder)
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldNotDeleteInvalidId() throws Exception {
        int toDelete = 0;

        when(repository.deleteById(toDelete)).thenReturn(false);

        RequestBuilder requestBuilder = delete("/pet/0")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

    private Pet makePet() {
        Pet pet = new Pet();
        PetType petType = new PetType();
        petType.setPetTypeId(1);
        petType.setName("Type test");
        petType.setAppetite(10);
        petType.setCare(10);
        petType.setThirst(10);
        petType.setHealth(10);
        petType.setNextPetTypeId(2);
        Move move = new Move();
        move.setName("test move");
        move.setMoveId(1);
        move.setDamage(10);
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(move);
        pet.setMoves(moves);
        pet.setName("test pet");
        pet.setHungerLevel(100);
        pet.setCareLevel(100);
        pet.setThirstLevel(100);
        pet.setHealthLevel(1000);
        pet.setDead(false);
        pet.setTrophies(0);
        pet.setPetType(petType);
        pet.setUsername("abc123");

        return pet;
    }
}