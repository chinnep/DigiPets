package learn.digipet.data;

import learn.digipet.models.PetMove;

/**
 * Pet Move repository is only used to add moves to pets
 * No editing of moves, deleting moves, and no need to read
 *  (reading attached moves will be handled by PetRepo)
 */
public interface PetMoveRepository {
    boolean add (PetMove petMove);
}
