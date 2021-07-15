package learn.digipet.controllers;

import learn.digipet.domain.*;
import learn.digipet.models.BattleRequest;
import learn.digipet.models.RoundRequest;
import learn.digipet.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/battle")
public class BattleController {

    private final BattleService battleService;
    private final UserService userService;
    private final BattleMoveHandler moveHandler;

    public BattleController(BattleService service, UserService userService, BattleMoveHandler moveHandler) {
        this.battleService = service;
        this.userService = userService;
        this.moveHandler = moveHandler;
    }

    @GetMapping("/{battleId}")
    public ResponseEntity<Battle> findById(@PathVariable int battleId) {
        Battle battle = battleService.findById(battleId);
        if(battle == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(battle);
    }

    @GetMapping
    public List<Battle> findAll() {
        return battleService.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Battle battle) {
        Result<Battle> result = battleService.add(battle);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PostMapping("/request")
    public ResponseEntity<Object> requestBattle(@RequestBody BattleRequest request) {
        Result<Battle> result = battleService.requestBattle(request);
        if (result.isSuccess()) {
            if(result.getPayload() != null) {
                return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return ErrorResponse.build(result);
    }

    @PostMapping("/{battleId}")
    public ResponseEntity<Object> round(@PathVariable int battleId, @RequestBody RoundRequest req) {

        Result<Battle> result = moveHandler.enterMove(battleId, req.getMoveA(), req.getIsPlayerA());
        boolean goldWorked;
        int matchStatusA, matchStatusB;

        if(!result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (result.isSuccess()) {
            User winner = userService.findByUsername(result.getPayload().getWinner().getUsername());
            User a = userService.findByUsername(result.getPayload().getPetA().getUsername());
            User b = userService.findByUsername(result.getPayload().getPetB().getUsername());

            if(a == winner) {
                matchStatusA = 1;
                matchStatusB = 0;
            } else {
                matchStatusA = 0;
                matchStatusB = 1;
            }

            int[] goldExchange = battleService.calculateGold(matchStatusA, matchStatusB, result.getPayload());

            a.setGold(a.getGold() + goldExchange[0]);
            b.setGold(b.getGold() + goldExchange[1]);

            goldWorked = userService.updateGold(a);
            if(!goldWorked) return new ResponseEntity<>(HttpStatus.CONFLICT);

            goldWorked = userService.updateGold(b);
            if(!goldWorked) return new ResponseEntity<>(HttpStatus.CONFLICT);

            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{battleId}")
    public ResponseEntity<Void> deleteById(@PathVariable int battleId) {
        if (battleService.deleteById(battleId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
