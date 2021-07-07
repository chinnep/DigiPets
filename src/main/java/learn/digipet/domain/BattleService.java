package learn.digipet.domain;

import learn.digipet.models.BattleRequest;
import learn.digipet.models.Item;
import learn.digipet.models.Move;
import learn.digipet.models.Pet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BattleService {

    private final Map<Integer, Battle> battles = new HashMap<>();
    private final Map<Integer, BattleRequest> queue = new HashMap<>();
    private int lastId = 0;
    private int lastBattleQueue = 0;
    public BattleService() {
    }

    public Battle findById(int battleId) {
        return battles.get(battleId);
    }

    public List<Battle> findAll() {
        List<Battle> battleList = new ArrayList<>();

        for(int i = lastId; i >= 0; i--) {
            if (battles.get(i) != null) {
                battleList.add(battles.get(i));
            }
        }
        return battleList;
    }

    public Result<Battle> add(Battle battle) {
        Result<Battle> result = validatePets(battle.getPetA(), battle.getPetB());
        if(!result.isSuccess()) return result;

        battle.setBattleId(lastId);
        battles.put(lastId, battle);
        lastId++;
        result.setPayload(battle);
        return result;
    }

    public Result<Battle> round(int battleId, Move moveA, Move moveB) {
        Result<Battle> result = validateMoves(moveA, moveB);

        if(!result.isSuccess()) return result;

        Battle battle = findById(battleId);
        if(battle == null) {
            result.addMessage("Battle is null.", ResultType.INVALID);
            return result;
        }

        boolean isOver = battle.round(moveA, moveB);
        if(isOver) {
            result.setPayload(findById(battleId));
            battles.remove(battleId);
        }

        return result;
    }

    public Result<Battle> requestBattle(BattleRequest req) {
        Result<Battle> result = new Result<>();
        if(req == null) {
            result.addMessage("request cannot be null.", ResultType.INVALID);
            return result;
        }
        if(req.getPet() == null) {
            result.addMessage("Pet cannot be null.", ResultType.INVALID);
            return result;
        }
        if(queue.size() > 0) {
            for(BattleRequest b : queue.values()) {
                if(b.getPet().getUsername() == req.getPet().getUsername()) {
                    result.addMessage("User has already made a request", ResultType.INVALID);
                    return result;
                }
            }
            //I'm just going to give the first one waiting that PetA advantage...
            int firstInLine = queue.keySet().stream().sorted().findFirst().orElse(-1);
            Battle battle = new Battle(queue.get(firstInLine).getPet(), req.getPet(),
                    queue.get(firstInLine).getItem(), req.getItem());
            queue.remove(firstInLine);
            result.setPayload(battle);
        } else {
            queue.put(lastBattleQueue, req);
            lastBattleQueue++;
        }
        return result;
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
        if(!moveA.getClass().getName().equals("learn.digipet.models.Move") ||
                !moveB.getClass().getName().equals("learn.digipet.models.Move")) {
            result.addMessage("Move is not valid.", ResultType.INVALID);
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
