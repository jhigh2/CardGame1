package game.card;

import game.Alliance;

/**
 * A bear card
 */
public class Owl extends Card {

    /**
     * Constructs a new Owl
     */
    public Owl(Alliance alliance) {
        super(2, 2, 2, Ability.AIR, alliance);
    }
}