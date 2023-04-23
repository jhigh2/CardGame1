package game;

import game.card.Card;

import java.util.Random;

import static game.Alliance.COMPUTER;
import static game.Alliance.PLAYER;

/**
 * This class represents the game board.
 * The locations start at the bottom left and go from left to right like so:
 * <li>4  5  6   7</li>
 * <li>0  1  2   3</li>
 * @author Joel High
 * @author John Freeman
 */
public class Board {
    private final Card[] cardLocations = new Card[8];
    private final Player player = new Player(10, 5, PLAYER);
    private final Computer computer = new Computer(10, 5);
    private final Random rand = new Random();

    /**
     * Constructs a new board that has a player and computer object.
     */
    public Board() {
    }

    /**
     * Returns the card opposite the card passed as argument.
     *
     * @param card the card being checked.
     * @return The card at the specified index.
     */
    public Card getEnemyCard(Card card) {
        int location = card.getCardLocation();
        if (card.getAlliance() == COMPUTER && location >= 4) {
            return getCardAtLocation(location - 4);
        }
        return getCardAtLocation(location + 4);
    }

    /**
     * Returns the card at the specified index
     *
     * @param location the location of the card
     * @return Card at location
     */
    public Card getCardAtLocation(int location) {
        return cardLocations[location];
    }

    /**
     * Returns a boolean stating whether the given location is valid for the given card.
     *
     * @param card         A card
     * @param cardLocation The location being checked
     * @return Boolean stating whether the given location is valid for the given card.
     */
    public boolean isValidLocation(Card card, int cardLocation) {
        if (cardLocation < 0 || cardLocation > 7) throw new IllegalArgumentException("Location out of bounds");
        return (card.getAlliance() == COMPUTER && cardLocation >= 4) || (card.getAlliance() == PLAYER && cardLocation <= 3);
    }

    /**
     * Returns true if the location is occupied, false otherwise.
     *
     * @param cardLocation The location being checked
     * @return Boolean stating whether the given location is occupied.
     */
    public boolean isLocationOccupied(int cardLocation) {
        return cardLocations[cardLocation] != null;
    }

    /**
     * Places the provided card at the given location and lowers the player's mana accordingly.
     *
     * @param card     The card to be placed
     * @param location The location for the card to be placed
     */
    public void setCardAtLocation(Card card, int location) {
        if (isValidLocation(card, location) && !isLocationOccupied(location)) {
            cardLocations[location] = card;
            card.setCardLocation(location);
            //Lower the players mana
            if (card.getAlliance() == PLAYER) player.setMana(player.getMana() - card.getMana());
            else computer.setMana(computer.getMana() - card.getMana());
        }
    }

    /**
     * Removes the card at the given location by setting it to null.
     *
     * @param location The location of the card to be removed
     */
    public void removeCardAtLocation(int location) {
        cardLocations[location] = null;
    }

    /**
     * Attacks the enemy card at the location opposite the card provided as argument.
     *
     * @param attackingCard The attacking card.
     */
    public void attackEnemy(Card attackingCard) {
        //Skip null cards
        if (attackingCard == null) return;
        Card enemyCard = getEnemyCard(attackingCard);
        if (enemyCard == null) {
            //If the enemy card is null, attack the enemy player.
            if (attackingCard.getAlliance() == COMPUTER)
                player.setHealth(player.getHealth() - attackingCard.getPower());
            else computer.setHealth(computer.getHealth() - attackingCard.getPower());
        } else {
            //If the enemy card is not null, attack the enemy card.
            enemyCard.setHealth(enemyCard.getHealth() - attackingCard.getPower());
            if (enemyCard.getHealth() <= 0) removeCardAtLocation(enemyCard.getCardLocation());
        }
    }

    /**
     * Returns the player
     *
     * @return The player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the computer
     *
     * @return The computer
     */
    public Computer getComputer() {
        return computer;
    }

    public void doComputerMove() {
        //Increase number of turns and mana
        computer.setNumberOfTurns(computer.getNumberOfTurns() + 1);
        computer.setMana(computer.getMana() + computer.getNumberOfTurns());
        //First check if all slots full and return if that's the case
        boolean isRowFull = true;
        for (int i = 4; i <= 7; i++) {
            if (!isLocationOccupied(i)) isRowFull = false;
        }
        if (isRowFull) return;
        //Draw a random card.
        Card card = computer.drawCard();
        //Try to place the card at a random non-occupied location if has enough mana to play it, otherwise return.
        int computerMoveLocation = rand.nextInt(4, 8);
        while (isLocationOccupied(computerMoveLocation)) {
            computerMoveLocation = rand.nextInt(4, 8);
        }
        if (computer.getMana() > card.getMana()) {
            setCardAtLocation(card, computerMoveLocation);
        } else {
            return;
        }
        attackAllEnemies(computer);
    }

    /**
     * Attacks all enemy cards of the given player
     *
     * @param player The attacker
     */
    public void attackAllEnemies(Player player) {
        if (player instanceof Computer) {
            for (int i = 4; i <= 7; i++) {
                attackEnemy(getCardAtLocation(i));
            }
        } else {
            for (int i = 0; i <= 3; i++) {
                attackEnemy(getCardAtLocation(i));
            }
        }
    }
}
