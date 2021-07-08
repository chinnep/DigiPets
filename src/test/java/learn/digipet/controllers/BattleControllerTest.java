package learn.digipet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.digipet.domain.Battle;
import learn.digipet.domain.BattleService;
import learn.digipet.domain.Result;
import learn.digipet.domain.ResultType;
import learn.digipet.models.*;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class BattleControllerTest {

    @MockBean
    BattleService service;

    @Autowired
    MockMvc mvc;

    @Test
    void shouldFindById() throws Exception {

        Battle expected = new Battle(makePet(), makePet(), makeItem(), makeItem());

        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(expected);

        when(service.findById(0)).thenReturn(expected);

        mvc.perform(get("/battle/0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldNotFindMissingId() throws Exception {
        mvc.perform(get("/battle/360"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldAdd() throws Exception {
        Battle toAdd = new Battle(makePet(), makePet(), makeItem(), makeItem());
        Battle expected = new Battle(makePet(), makePet(), makeItem(), makeItem());

        Result<Battle> result = new Result<>();
        result.setPayload(expected);

        when(service.add(any())).thenReturn(result);

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonIn = jsonMapper.writeValueAsString(toAdd);
        String expectedJson = jsonMapper.writeValueAsString(expected);

        RequestBuilder requestBuilder = post("/battle")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldNotAddNullPetA() throws Exception {
        Battle toAdd = new Battle(null, makePet(), makeItem(), makeItem());
        Result<Battle> result = new Result<>();
        result.addMessage("Test", ResultType.INVALID);

        when(service.add(any())).thenReturn(result);

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonIn = jsonMapper.writeValueAsString(toAdd);

        RequestBuilder requestBuilder = post("/battle")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldNotAddNullPetB() throws Exception {
        Battle toAdd = new Battle(makePet(), null ,makeItem(), makeItem());
        Result<Battle> result = new Result<>();
        result.addMessage("Test", ResultType.INVALID);

        when(service.add(any())).thenReturn(result);

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonIn = jsonMapper.writeValueAsString(toAdd);

        RequestBuilder requestBuilder = post("/battle")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldNotAddNullPets() throws Exception {
        Battle toAdd = new Battle(null, null ,makeItem(), makeItem());
        Result<Battle> result = new Result<>();
        result.addMessage("Test", ResultType.INVALID);

        when(service.add(any())).thenReturn(result);

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonIn = jsonMapper.writeValueAsString(toAdd);

        RequestBuilder requestBuilder = post("/battle")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());
    }

    @Test
    //currently doesn't work but I need a break from battles atm
    void shouldHaveARound() throws Exception {
        Battle battle = new Battle(makePet(),makePet(),makeItem(),makeItem());
        battle.setBattleId(0);

        Result<Battle> result = new Result<>();

        RoundRequest req = new RoundRequest(0,new Move(1,"test",100),
                true);

        when(service.round(req.getBattleId(),req.getMoveA(),req.getMoveA()))
                .thenReturn(result);

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonIn = jsonMapper.writeValueAsString(req);

        RequestBuilder requestBuilder = put("/battle/0/round")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(requestBuilder)
                .andExpect(status().isNoContent());
    }

    Item makeItem() {
        return new Item(1, "wrench","A description of a wrench.",
                true, false, false, false, false, 200, "");
    }

    private Pet makePet() {
        Pet pet = new Pet();
        PetType petType = new PetType();
        petType.setPetTypeId(1);
        petType.setName("Type test");
        petType.setAppetite(10);
        petType.setCare(10);
        petType.setThirst(10);
        petType.setHealth(1000);
        petType.setNextPetTypeId(2);
        Move move = new Move();
        move.setName("test move");
        move.setMoveId(1);
        move.setDamage(10);
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(move);
        petType.setMoves(moves);
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