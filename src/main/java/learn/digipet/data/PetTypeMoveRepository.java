package learn.digipet.data;

import learn.digipet.models.Move;

import java.util.List;

public interface PetTypeMoveRepository {

    List<Move> findByPetTypeId(int petTypeId);
}
