package game.card;

import game.Alliance;

/**
 * A bear card
 */
public class Bobcat extends Card {

    /**
     * Constructs a new Bobcat
     */
    public Bobcat(Alliance alliance) {
        super(3, 3, 3, Ability.LAND, alliance);
    }
}