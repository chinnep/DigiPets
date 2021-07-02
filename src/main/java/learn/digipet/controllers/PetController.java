package learn.digipet.controllers;

import learn.digipet.domain.PetService;
import learn.digipet.domain.Result;
import learn.digipet.models.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/pet")
public class PetController {

    private final PetService service;

    public PetController(PetService service) { this.service = service; }

    @GetMapping
    public List<Pet> findAll() {
        return service.findAll();
    }

    @GetMapping("/{petId}")
    public Pet findById(@PathVariable int petId) {
        return service.findById(petId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody @Valid Pet pet, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Result<Pet> result = service.add(pet);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<Object> update(@PathVariable int petId, @RequestBody Pet pet) {
        if (petId != pet.getPetId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Pet> result = service.update(pet);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deleteById(@PathVariable int petId) {
        if (service.deleteById(petId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
