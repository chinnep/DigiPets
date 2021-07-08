package learn.digipet.controllers;

import learn.digipet.domain.Battle;
import learn.digipet.domain.BattleMoveHandler;
import learn.digipet.domain.BattleService;
import learn.digipet.domain.Result;
import learn.digipet.models.BattleRequest;
import learn.digipet.models.RoundRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/battle")
public class BattleController {

    private final BattleService service;
    private final BattleMoveHandler moveHandler;

    public BattleController(BattleService service, BattleMoveHandler moveHandler) {
        this.service = service;
        this.moveHandler = moveHandler;
    }

    @GetMapping("/{battleId}")
    public ResponseEntity<Battle> findById(@PathVariable int battleId) {
        Battle battle = service.findById(battleId);
        if(battle == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(battle);
    }

    @GetMapping
    public List<Battle> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Battle battle) {
        Result<Battle> result = service.add(battle);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PostMapping("/request")
    public ResponseEntity<Object> requestBattle(@RequestBody BattleRequest request) {
        Result<Battle> result = service.requestBattle(request);
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


//        Result<Battle> result = service.round(battleId, req.getMoveA(), req.getMoveB());

        if(!result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }

        return ErrorResponse.build(result);
    }
}
