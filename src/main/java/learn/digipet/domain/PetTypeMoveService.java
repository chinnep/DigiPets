package learn.digipet.domain;

import learn.digipet.data.PetTypeMoveRepository;
import learn.digipet.models.Move;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetTypeMoveService {

    private final PetTypeMoveRepository repository;

    public PetTypeMoveService(PetTypeMoveRepository repository) {
        this.repository = repository;
    }

    public List<Move> findByPetTypeId(int petTypeId) {
        return repository.findByPetTypeId(petTypeId);
    }
}
