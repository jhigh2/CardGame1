package game.card;

import game.Alliance;
import game.Player;

/**
 * A bear card
 */
public class Shark extends Card {

    /**
     * Constructs a new Shark
     */
    public Shark(Alliance alliance) {
        super(3, 2, 2, Ability.WATER, alliance);
    }
}