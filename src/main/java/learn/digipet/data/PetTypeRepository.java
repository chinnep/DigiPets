package learn.digipet.data;

import learn.digipet.models.PetType;

import java.util.List;

public interface PetTypeRepository {

    List<PetType> findAll();

    PetType findById(int petTypeId);
}
