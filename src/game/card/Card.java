package game.card;

import game.Alliance;
import game.Computer;
import game.Player;
import game.Board;

public abstract class Card {
    private int power;
    private int health;
    private int mana;
    private int cardLocation;
    private final Ability ability;
    private final Alliance alliance;

    /**
     * Constructs a new card
     * @param power The card's power
     * @param health The card's health
     * @param mana The card's mana
     * @param ability The type ability
     * @param alliance The card's alliance
     */
    public Card(int power, int health, int mana, Ability ability, Alliance alliance){
        this.power = power;
        this.health = health;
        this.mana = mana;
        this.ability = ability;
        this.alliance = alliance;
    }

    public int getPower() {
        return power;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public Ability getAbility() {
        return ability;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public int getCardLocation() {
        return cardLocation;
    }

    /**
     * Sets the card at the specified location on the board;
     * @param cardLocation The location on the board. (Must align with the card's alliance or an IllegalArgumentException will be thrown)
     */
    public void setCardLocation(int cardLocation) {
        this.cardLocation = cardLocation;
    }

    /**
     * Returns a String containing the card's health, name, and damage stats.
     * @return the card's health, name, and damage stats.
     */
    @Override
    public String toString() {
        return getPower() + " " + this.getClass().getSimpleName() + " " + getHealth() ;
    }
}
