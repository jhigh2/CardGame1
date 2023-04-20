package game.card;

import game.Alliance;
import game.Player;

public class Cat extends Card {

        /**
        * Constructs a new Cat
        */
        public Cat(Alliance alliance) {
            super(2, 2, 2, Ability.LAND, alliance);
        }
}
