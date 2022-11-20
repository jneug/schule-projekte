package roborally;

import roborally.instructions.*;
import roborally.tiles.Tile;
import schule.ngb.zm.Constants;

public class Player extends Constants {

    private String name;

    private Robot robot;

    private Instruction[] hand;

    /**
     * Anzahl Karten, die der Spieler in der aktuellen Runde bisher gezogen
     * hat.
     */
    private int cardsSelected = 0;

    private List<Integer> selectedCards;

    public Player( String pName, Tile startTile ) {
        name = pName;

        robot = new Robot(startTile.getX(), startTile.getY());
        robot.setTile(startTile);

        hand = new Instruction[RoboRallye.HAND_COUNT];
        selectedCards = new List<>();
    }

    public String getName() {
        return name;
    }

    public void drawHand( Stack<Instruction> deck ) {
        for( int i = 0; i < hand.length; i++ ) {
            if( !deck.isEmpty() ) {
                hand[i] = deck.top();
                deck.pop();
            }
        }

        // Wenn eine neue Hand gezogen wurde, wurden noch keine Karten
        // ausgewählt.
        cardsSelected = 0;
    }

    public int getCardsSelected() {
        return cardsSelected;
    }

    public boolean canSelectCards() {
        return cardsSelected < RoboRallye.SELECT_COUNT;
    }

    /**
     * markiert eine der Karten auf der {@link #hand Hand} als ausgewählt.
     *
     * @param i Index der Karte.
     */
    public void selectCard( int i ) {
        if( !isCardSelected(i) ) {
            cardsSelected += 1;
            selectedCards.append(i);
        }
    }

    public void unselectCard( int i ) {
        if( isCardSelected(i) ) {
            selectedCards.remove();
            cardsSelected -= 1;
        }
    }

    /**
     * Prüft, ob eine Karte auf der Hand schon gewählt wurde.
     *
     * @param i Index der Karte.
     * @return {@code true}, wenn die Karte vorher schon gewählt wurde,
     *     {@code false} sonst.
     */
    public boolean isCardSelected( int i ) {
        selectedCards.toFirst();
        while( selectedCards.hasAccess() ) {
            if( selectedCards.getContent().intValue() == i ) {
                return true;
            }
            selectedCards.next();
        }
        return false;
    }

    public Instruction[] getHand() {
        return hand;
    }

    public Instruction getHand( int i ) {
        return hand[i];
    }

    public List<Integer> getSelectedCards() {
        return selectedCards;
    }

    public void programRobot() {
        selectedCards.toFirst();
        while( selectedCards.hasAccess() ) {
            Instruction instruction = hand[selectedCards.getContent()];
            robot.addInstruction(instruction);
            selectedCards.remove();
        }
    }

    public Robot getRobot() {
        return robot;
    }

}
