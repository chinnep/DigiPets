package learn.digipet.domain;

import learn.digipet.data.MoveRepository;
import learn.digipet.models.Move;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoveService {
    private final MoveRepository repository;

    public MoveService (MoveRepository repository) {
        this.repository = repository;
    }

    public List<Move> findAll() {
        return repository.findAll();
    }

    public Move findById(int moveId) {
        return repository.findById(moveId);
    }
}
