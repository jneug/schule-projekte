package roborally;

import roborally.instructions.*;
import roborally.tiles.Tile;
import schule.ngb.zm.Constants;

/**
 * Ein Spieler im Robo Rally Spiel.
 * <p>
 * Ein Spieler besitzt genau einen {@link Robot Roboter} und hält eine Anzahl
 * von {@link Instruction Anweisungen} auf der Hand, aus denen er eine Anzahl
 * auswählt, um sie dem Roboter einzuprogrammieren.
 * <p>
 * Der Spieler enthält vor allem Methoden, um das Ziehen und die Auswahl der
 * Anweisungen zu verwalten.
 */
public class Player extends Constants {

    /**
     * Konfiguration: Anzahl Karten auf der Hand eines Spielers.
     */
    public static final int HAND_COUNT = 9;

    /**
     * Konfiguration: Anzahl an Karten, die von der Hand ausgewählt werden.
     */
    public static final int SELECT_COUNT = 5;

    private String name;

    private Robot robot;

    private Instruction[] hand;

    /**
     * Anzahl Karten, die der Spieler in der aktuellen Runde bisher gezogen hat.
     * Wird zu Beginn einer Runde auf 0 gesetzt.
     */
    private int cardsSelectedCount = 0;

    /**
     * Liste an Integern, die den Index und die Reihenfolge der bisher
     * ausgewählten Karten festlegen. Ausgewählte Karten werden an die Liste
     * angehängt. Die Reihenfolge der Nummern in der Liste ist die Reihenfolge,
     * in der die Anweisungen in den Roboter einprogrammiert werden. Die Nummern
     * in der Liste sind jeweils der Index der Karte im Hand-Array.
     */
    private List<Integer> selectedCards;

    public Player( String pName, Tile startTile ) {
        name = pName;

        robot = new Robot(startTile);

        hand = new Instruction[HAND_COUNT];
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
        cardsSelectedCount = 0;
    }

    //ml*
    @SuppressWarnings( "unused" )
    //*ml
    public int getCardsSelectedCount() {
        return cardsSelectedCount;
    }

    public boolean canSelectCards() {
        return cardsSelectedCount < SELECT_COUNT;
    }

    /**
     * markiert eine der Karten auf der {@link #hand Hand} als ausgewählt.
     *
     * @param i Index der Karte.
     */
    public void selectCard( int i ) {
        if( !isCardSelected(i) ) {
            cardsSelectedCount += 1;
            selectedCards.append(i);
        }
    }

    public void unselectCard( int i ) {
        if( isCardSelected(i) ) {
            selectedCards.remove();
            cardsSelectedCount -= 1;
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
            if( selectedCards.getContent() == i ) {
                return true;
            }
            selectedCards.next();
        }
        return false;
    }

    public Instruction[] getHand() {
        return hand;
    }

    public Instruction getHandCard( int i ) {
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
