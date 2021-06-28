package learn.digipet.data;

/**
 * Pet Move repository is only used to add moves to pets when creating pets
 * No editing of moves, deleting moves, and no need to read
 *  (reading attached moves will be handled by PetRepo)
 */
public interface PetMoveRepository {
    boolean add (int petId, int moveId);
}
