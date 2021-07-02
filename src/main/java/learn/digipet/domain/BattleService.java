package learn.digipet.domain;

import learn.digipet.models.Move;
import learn.digipet.models.Pet;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BattleService {

    private final Map<Integer, Battle> battles = new HashMap<>();
    public BattleService() {
    }

    public Battle findByBattleId(int battleId) {
        return battles.get(battleId);
    }

    public Result<Battle> addBattle(Battle battle) {
        Result<Battle> result = validatePets(battle.getPetA(), battle.getPetB());
        if(result.getMessages().size() > 0) return result;

        battles.put(battles.size(), battle);
        result.setPayload(battle);
        return result;
    }

    public Result<Battle> round(int BattleId, Move moveA, Move moveB) {
        Result<Battle> result = validateMoves(moveA, moveB);

        //Battle battle = findByBattleId(battle);
        return null;
    }

    //yeah so I'm just going to check for the correct datatypes, not each individual parameter
    //hopefully this doesn't backfire... but if it does here's the friendly warning
    public Result<Battle> validatePets(Pet petA, Pet petB) {
        Result<Battle> result = new Result<>();
        if(petA == null || petB == null) {
            result.addMessage("Pets cannot be null", ResultType.INVALID);
            return result;
        }
        if(!petA.getClass().getName().equals("learn.digipet.models.Pet") ||
                !petB.getClass().getName().equals("learn.digipet.models.Pet")) {
            result.addMessage("Pet is not valid.", ResultType.INVALID);
        }
        return result;
    }

    public Result<Battle> validateMoves(Move moveA, Move moveB) {
        Result<Battle> result = new Result<>();
        if(moveA == null || moveB == null) {
            result.addMessage("Moves cannot be null", ResultType.INVALID);
            return result;
        }
        if(!moveA.getClass().getName().equals("learn.digipet.models.Pet") ||
                !moveB.getClass().getName().equals("learn.digipet.models.Pet")) {
            result.addMessage("Pet is not valid.", ResultType.INVALID);
            return result;
        }
        if(moveA.getDamage() < 0 || moveB.getDamage() < 0) {
            result.addMessage("Moves must have a positive number", ResultType.INVALID);
        }
        return result;
    }

    //TODO: perhaps add a method here to use an item to teach the third move ?
    // although the move could probably be there for the next battle so if it ripples
    // then this is not a battle class issue;
}
