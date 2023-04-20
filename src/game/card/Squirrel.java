package game.card;

import game.Alliance;
import game.Player;

/**
 * A bear card
 */
public class Squirrel extends Card {

    /**
     * Constructs a new Squirrel
     */
    public Squirrel(Alliance alliance) {
        super(1, 1, 1, Ability.LAND, alliance);
    }
}