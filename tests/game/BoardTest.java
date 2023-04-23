package game;

import game.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;
    private Player player;
    private Computer computer;

    @BeforeEach
    void init() {
        board = new Board();
        player = new Player(5, 5, Alliance.PLAYER);
        computer = new Computer(5, 5);
    }

    @Test
    void Board_EmptyBoard_EmptySlotReturnsNull() {
        assertNull(board.getCardAtLocation(3));
    }

    @Test
    void Board_PlacingCards() {
        Bear bear = new Bear(player.getAlliance());
        Wolf wolf = new Wolf(computer.getAlliance());
        board.setCardAtLocation(bear, 3);
        board.setCardAtLocation(wolf, 7);
        assertEquals(wolf, board.getCardAtLocation(7));
        assertEquals(bear, board.getCardAtLocation(3));
    }

    @Test
    void Board_GetEnemyCard() {
        for (int i = 0; i < 4; i++) {
            Bear bear = new Bear(player.getAlliance());
            Wolf wolf = new Wolf(computer.getAlliance());
            board.setCardAtLocation(bear, i);
            int middleRow = i + 4;
            board.setCardAtLocation(wolf, middleRow);
            assertEquals(wolf, board.getEnemyCard(bear));
            assertEquals(bear, board.getEnemyCard(wolf));
        }
    }

    @Test
    void Board_CardOutOfRange_ThrowsIllegalArgumentException() {
        Shark shark = new Shark(player.getAlliance());
        //TODO handle this in a better way maybe.
        assertThrows(IllegalArgumentException.class, () -> board.setCardAtLocation(shark, -1));
    }

    @Test
    void Board_RemoveCardAndReplaceCard() {
        Shark shark = new Shark(player.getAlliance());
        board.setCardAtLocation(shark, 2);
        assertEquals(shark, board.getCardAtLocation(2));

        Cat cat = new Cat(computer.getAlliance());
        board.setCardAtLocation(cat, 6);
        assertEquals(cat, board.getCardAtLocation(6));
        assertEquals(shark, board.getEnemyCard(cat));

        board.removeCardAtLocation(6);
        assertNull(board.getCardAtLocation(6));
        //Successfully removes the card

        //Successfully replaces a new card at the location
        Bear bear = new Bear(computer.getAlliance());
        board.setCardAtLocation(bear, 6);
        assertEquals(bear, board.getCardAtLocation(6));
        assertEquals(shark, board.getEnemyCard(bear));
        assertEquals(bear, board.getEnemyCard(shark));
    }

    @Test
    void board_AttackCard_BearKillsWolf() {
        Bear bear = new Bear(player.getAlliance());
        board.setCardAtLocation(bear, 2);
        assertEquals(bear, board.getCardAtLocation(2));


        Wolf wolf = new Wolf(computer.getAlliance());
        board.setCardAtLocation(wolf, 6);
        assertEquals(wolf, board.getEnemyCard(bear));

        assertEquals(4, wolf.getPower());
        assertEquals(5, bear.getHealth());
        board.attackEnemy(bear);

        //Wolf should die to bear
        assertNull(board.getEnemyCard(bear));
    }

    @Test
    void board_AttackCard_CardLosesHealth() {
        Bear bear = new Bear(player.getAlliance());
        board.setCardAtLocation(bear, 2);
        assertEquals(bear, board.getCardAtLocation(2));


        Cat cat = new Cat(computer.getAlliance());
        board.setCardAtLocation(cat, 6);
        assertEquals(cat, board.getEnemyCard(bear));

        assertEquals(2, cat.getPower());
        assertEquals(5, bear.getHealth());
        board.attackEnemy(cat);

        //Bears health should now be 3
        assertEquals(3, bear.getHealth());
    }
}