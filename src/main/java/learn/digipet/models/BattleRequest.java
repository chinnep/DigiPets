package learn.digipet.models;

import lombok.Builder;
import lombok.Getter;

@Builder
public class BattleRequest {

    @Getter
    Pet pet;
    @Getter
    Item item;
}
