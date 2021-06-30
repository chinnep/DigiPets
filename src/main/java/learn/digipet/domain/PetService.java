package learn.digipet.domain;

import learn.digipet.data.PetRepository;
import learn.digipet.models.Pet;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class PetService {

    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public List<Pet> findAll() {
        return repository.findAll();
    }

    public Pet findById(int petId) {
        return repository.findById(petId);
    }

    public boolean deleteById(int petId) {
        return repository.deleteById(petId);
    }

    /*
    A few things to note:
    1. petId should be at 0 to be a valid add
    2. Last login should be null (can't be in the future or past
    3. Can't be dead, we don't give out dead 8bit pets in this household
    4. No trophies for new pets
    5. New pets can have null moves but not a list < 1 (because eggs can't have moves)
     */
    public Result<Pet> add(Pet pet) {

        Result<Pet> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Pet>> violations = validator.validate(pet);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Pet> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }

        result = validate(pet);

        if(result.getMessages().size() > 0) return result;

        if (pet.getTimeAtLastLogin() != null) {
            result.addMessage("Last Login must be null for a brand new pet.", ResultType.INVALID);
            return result;
        }

        if (pet.getPetId() != 0) {
            result.addMessage("PetId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        if (pet.isDead()) {
            result.addMessage("New pet cannot be dead lol.", ResultType.INVALID);
            return result;
        }

        if (pet.getTrophies() > 0) {
            result.addMessage("New pet cannot have trophies.", ResultType.INVALID);
            return result;
        }

        result.setPayload(repository.add(pet));
        return result;
    }

    /*
    1. petId has to be above 0
    2. Last login can be null (if there's an update right after receiving the pet
     */
    public Result<Pet> update(Pet pet) {
        Result<Pet> result = new Result<>();

        if (pet.getPetId() <= 0) {
            result.addMessage("PetId cannot be zero or less for `update` operation", ResultType.INVALID);
            return result;
        }

        result = validate(pet);

        if(result.getMessages().size() > 0) return result;

        if(pet.getTimeAtLastLogin().isAfter(LocalDateTime.now())) {
            result.addMessage("Time at last login cannot be in the future", ResultType.INVALID);
            return result;
        }

        if (!repository.update(pet)) {
            result.addMessage(String.format("pet id: %s, not found", pet.getPetId()), ResultType.NOT_FOUND);
        }

        return result;
    }

    private Result<Pet> validate(Pet pet) {
        Result<Pet> result = new Result<>();
        if (pet == null) {
            result.addMessage("Pet cannot be null", ResultType.INVALID);
            return result;
        }

        if(pet.getName() == null) {
            result.addMessage("Name cannot be null.", ResultType.INVALID);
        }

        if(pet.getUserId() < 1) {
            result.addMessage("Invalid userId.", ResultType.INVALID);
        }
        if(pet.getPetType() != null && pet.getPetType().getPetTypeId() < 1) {
            result.addMessage("Invalid pet type id.", ResultType.INVALID);
        }

        return result;
    }
}
