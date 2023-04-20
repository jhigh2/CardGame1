package game;

import game.card.Card;
import game.card.Bear;
import game.card.Wolf;

import java.util.List;
import java.util.Random;

/**
 * A Computer opponent that the player will play against.
 */
public class Computer extends Player {
    private final Random rand = new Random();

    /**
     * Constructs a new Computer opponent
     * @param health   The Computer's health
     * @param mana     The Computer's mana
     */
    public Computer(int health, int mana) {
        super(health, mana, Alliance.COMPUTER);
    }
}
