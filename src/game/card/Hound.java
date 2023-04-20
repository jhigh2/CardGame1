package game.card;

import game.Alliance;

public class Hound extends Card{

    public Hound(Alliance alliance){
        super(3, 2, 2, Ability.LAND, alliance);
    }
}
