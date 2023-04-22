package game;

import game.card.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A class representing a player.
 */
public class Player {
    private final List<Card> deck = new ArrayList<>();
    private final List<Card> hand = new ArrayList<>();
    private final Alliance alliance;
    private final Random rand = new Random();
    private int health;
    private int mana;
    private int numberOfTurns = 0;

    /**
     * Constructs a new Player
     *
     * @param health The player's health
     * @param mana   The player's mana
     */
    public Player(int health, int mana, Alliance alliance) {
        this.health = health;
        this.mana = mana;
        this.alliance = alliance;
        initializeRandomDeck();
        //initializeHand();
    }

    /**
     * Initializes the player's hand by removing 5 cards from the deck.
     */
    private void initializeHand() {
        for (int i = 0; i < 5; i++) {
            hand.add(drawCard());
        }
    }

    /**
     * Initializes a deck of 10 cards.
     */
    public void initializeRandomDeck() {
        deck.clear();
        List<Card> uniqueCards = new ArrayList<>();
        Collections.addAll(uniqueCards, new Bear(alliance), new Wolf(alliance), new Squirrel(alliance), new Cat(alliance), new Crow(alliance), new Shark(alliance),
        new Owl(alliance), new Bobcat(alliance), new Hound(alliance), new Alligator(alliance));
        for (int i = 0; i < 10; i++) {
            int r = rand.nextInt(uniqueCards.size());
            deck.add(uniqueCards.get(r));
            uniqueCards.remove(r);
        }

    }

    /**
     * Draws a random card from the players deck and adds it to the players hand
     */
    public Card drawCard() {
        int n = rand.nextInt(deck.size());
        Card drawnCard = deck.get(n);
        deck.remove(n);
        hand.add(drawnCard);
        return drawnCard;
    }

    /**
     * Returns the player's deck
     *
     * @return the player's deck
     */
    public List<Card> getDeck() {
        return this.deck;
    }

    /**
     * Returns the player's hand
     *
     * @return the player's hand
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * Adds a card to the player's hand
     *
     * @param card A Card
     */
    public void setHand(Card card) {
        hand.add(card);
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

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public Alliance getAlliance() {
        return alliance;
    }
}
