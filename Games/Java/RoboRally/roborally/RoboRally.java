package roborally;

import roborally.instructions.*;
import roborally.tiles.Tile;
import schule.ngb.zm.Zeichenmaschine;
import schule.ngb.zm.util.io.FontLoader;

import java.awt.Font;

/**
 * Hauptklasse des Robo Rally Spiels.
 */
public class RoboRally extends Zeichenmaschine {

    /**
     * Verzögerung nach jeder Ausführung von Instruktionen.
     */
    public static final int INSTRUCTION_DELAY = 800;

    /**
     * Liste der Spieler, die Teilnehmen.
     */
    private List<Player> players;

    /**
     * Schlange der Spieler, die in der aktuellen Runde noch Karten auswählen
     * müssen.
     */
    private Queue<Player> playerOrder;

    /**
     * Kartenstapel, von dem die Handkarten gezogen werden.
     */
    private Stack<Instruction> deck;

    /**
     * Die Karte des Spiels.
     */
    private Factory factory;

    /**
     * Zustand des Spiels.
     */
    private String gameState;

    /**
     * Nummer der aktuellen Runde.
     */
    private int round;

    /**
     * Konstruktor der Spiel-Klasse.
     */
    public RoboRally() {
        super(Factory.MAP_WIDTH * Tile.TILE_SIZE, Factory.MAP_HEIGHT * Tile.TILE_SIZE + Instruction.CARD_HEIGHT + 20, "Robo Rally");
    }

    @Override
    public void setup() {
        // Spielzustand "startend"
        gameState = "starting";

        // Vorbereiten der Schriftart
        FontLoader.loadFonts("roborally", "Comic Sans MS", "Arial");
        drawing.setFont("roborally", 16, Font.BOLD);

        // Fabrik erstellen
        factory = new Factory();
        factory.createMap();

        // Spieler erstellen
        players = new List<>();
        for( int i = 0; i < 2; i++ ) {
            players.append(new Player("Player " + (i + 1), factory.getTile(2, i * 2 + 3)));
        }

        // Datenstrukturen anlegen
        playerOrder = new Queue<>();
        deck = new Stack<>();

        // Erste Runde starten
        round = 0;
        startNextRound();
    }

    /**
     * Mischt den Kartenstapel.
     * <p>
     * Nach dem Ablauf der Methode enthält der Kartenstapel alle
     * {@link Instruction Anweisungs-Karten}, die sich im Spiel befinden, in
     * zufälliger Reihenfolge.
     *
     * @see #shuffle(Object[])
     */
    public void shuffleDeck() {
        Instruction[] cards = new Instruction[84];

        int i = 0;
        for( ; i < 20; i++ ) {
            cards[i] = new ForwardInstruction();
        }
        for( ; i < 32; i++ ) {
            cards[i] = new ForwardInstruction(2);
        }
        for( ; i < 40; i++ ) {
            cards[i] = new ForwardInstruction(3);
        }
        for( ; i < 52; i++ ) {
            cards[i] = new BackwardsInstruction();
        }
        for( ; i < 63; i++ ) {
            cards[i] = new TurnLeftInstruction();
        }
        for( ; i < 74; i++ ) {
            cards[i] = new TurnRightInstruction();
        }
        for( ; i < 84; i++ ) {
            cards[i] = new TurnAroundInstruction();
        }

        cards = shuffle(cards);

        deck = new Stack<>();
        for( i = 0; i < cards.length; i++ ) {
            deck.push(cards[i]);
        }
    }

    /**
     * Startet die nächste Spielrunde.
     * <p>
     * Zu Beginn einer SPielrunde wird die {@link #round Rundenzahl} erhöht, der
     * Kartenstapel {@link #shuffleDeck() gemischt} und jeder Spieler
     * {@link Player#drawHand(Stack)} zieht aus dem Kartenstapel seine
     * Handkarten. Außerdem wird die Zugreihenfolge in der {@code Queue}
     * {@link #playerOrder} abgelegt.
     */
    public void startNextRound() {
        round += 1;

        shuffleDeck();

        players.toFirst();
        while( players.hasAccess() ) {
            players.getContent().drawHand(deck);
            playerOrder.enqueue(players.getContent());
            players.next();
        }

        gameState = "next turn";
    }

    /**
     * Führt nacheinander die Anweisungen der Roboter und der Fabrik aus.
     */
    public void executeInstructions() {
        gameState = "executing";
        Tile winTile = factory.getWinTile();

        for( int i = 0; i < Player.SELECT_COUNT; i++ ) {
            players.toFirst();
            while( players.hasAccess() ) {
                Player currentPlayer = players.getContent();
                Robot robot = currentPlayer.getRobot();
                robot.executeNextInstruction();

                if( checkWinTile(currentPlayer) ) {
                    gameState = "game ended";
                    drawWinScreen(currentPlayer);
                    return;
                }

                players.next();
            }

            factory.executeTiles();
            redraw();
            delay(INSTRUCTION_DELAY);
        }

        // Effekte anwenden
        players.toFirst();
        while( players.hasAccess() ) {
            Player currentPlayer = players.getContent();
            players.next();

            currentPlayer.getRobot().applyEffects();
        }

        startNextRound();
        redraw();
    }

    public boolean checkWinTile( Player pCurrentPlayer ) {
        Tile winTile = factory.getWinTile();
        return winTile.hasRobot() && winTile.getRobot().equals(pCurrentPlayer.getRobot());
    }

    @Override
    public void draw() {
        if( playerOrder.isEmpty() ) {
            drawing.clear();
        } else {
            drawing.clear(playerOrder.front().getRobot().getColor());
        }

        // Karte anzeigen
        factory.draw(drawing);

        // Nummer der Runde anzeigen
        drawing.setFontSize(30);
        drawing.setFillColor(WHITE);
        drawing.text("Runde " + round, canvasWidth - 100, 50);
        drawing.setFontSize(28);
        drawing.setFillColor(BLACK);
        drawing.text("Runde " + round, canvasWidth - 100, 50);

        players.toFirst();
        while( players.hasAccess() ) {
            players.getContent().getRobot().draw(drawing);
            players.next();
        }

        if( gameState == "next turn" ) {
            drawNextButton();
        } else if( gameState == "card select" ) {
            drawCards();
        } else if( gameState == "execution phase" ) {
            executeInstructions();
        }
    }

    private void drawNextButton() {
        drawing.noStroke();
        drawing.setFillColor(33, 200);
        drawing.rect(0, 0, canvasWidth, canvasHeight, NORTHWEST);
        drawing.setStrokeColor(33);
        drawing.setFillColor(playerOrder.front().getRobot().getColor());
        drawing.roundedRect(canvasWidth / 2, canvasHeight / 2, Tile.TILE_SIZE * 6, Tile.TILE_SIZE * 2, 8);
        drawing.setFontSize(32);
        drawing.text(playerOrder.front().getName(), canvasWidth / 2, canvasHeight / 2);
    }

    private void drawWinScreen( Player player ) {
        // Einmal neu zeichnen, damit die Karte im finalen Zustand angezeigt wird
        // (ggf. wurde die Karte nach der letzten Anweisung noch nicht gezeichnet).
        redraw();

        drawing.noStroke();
        drawing.setFillColor(33, 200);
        drawing.rect(0, 0, canvasWidth, canvasHeight, NORTHWEST);
        drawing.setStrokeColor(33);
        drawing.setFillColor(player.getRobot().getColor());
        drawing.roundedRect(canvasWidth / 2, canvasHeight / 2, Tile.TILE_SIZE * 10, Tile.TILE_SIZE * 2, 8);
        drawing.setFontSize(32);
        drawing.text(player.getName() + " gewinnt", canvasWidth / 2, canvasHeight / 2);

        // Nicht noch einmal draw aufrufen, sondern nur die neuen Inhalte zeichnen
        render();
    }

    private void drawCards() {
        Player currentPlayer = playerOrder.front();

        // Abstand zum linken Bildrand berechnen, damit die Karten zentriert sind
        int offset = (int) ((canvasWidth - (Player.HAND_COUNT * (Instruction.CARD_WIDTH + 20))) / 2);

        // Die Karten von der Hand einzeichnen
        for( int i = 0; i < Player.HAND_COUNT; i++ ) {
            // Alle Handkarten sollten zu diesem Zeitpunkt ungleich null sein
            currentPlayer.getHandCard(i).draw(
                offset + i * (Instruction.CARD_WIDTH + 20),
                canvasHeight - (Instruction.CARD_HEIGHT + 10),
                drawing
            );
        }

        // Die Reihenfolge für alle ausgewählten Karten einzeichnen
        int prio = 1;
        // Referenz auf die Liste der ausgewählten Kartennummern holen
        List<Integer> selectedCards = currentPlayer.getSelectedCards();
        selectedCards.toFirst();
        // Ausgewählte Kartennummern durchlaufen und einzeichnen
        while( selectedCards.hasAccess() ) {
            int i = selectedCards.getContent();
            selectedCards.next();

            // Koordinaten bestimmen
            int cardX1 = offset + i * (Instruction.CARD_WIDTH + 20);
            int cardY1 = canvasHeight - 10;

            // Zeichnen
            drawing.setStrokeColor(BLACK);
            drawing.setStrokeWeight(2);
            drawing.setFillColor(RED);
            drawing.circle(
                cardX1 + 25,
                cardY1 - 25,
                20
            );
            drawing.setFontSize(16);
            drawing.text(
                "" + prio,
                cardX1 + 25,
                cardY1 - 25
            );

            // Nächste Nummer der Reihenfolge
            prio += 1;
        }
    }

    @Override
    public void mouseClicked() {
        if( gameState == "next turn" ) {
            int buttonX1 = canvasWidth / 2 - Tile.TILE_SIZE * 3;
            int buttonX2 = canvasWidth / 2 + Tile.TILE_SIZE * 3;
            int buttonY1 = canvasHeight / 2 - Tile.TILE_SIZE;
            int buttonY2 = canvasHeight / 2 + Tile.TILE_SIZE;

            if( cmouseX > buttonX1 && cmouseX < buttonX2
                && cmouseY > buttonY1 && cmouseY < buttonY2 ) {
                gameState = "card select";
                redraw();
            }
        } else if( gameState == "card select" ) {
            Player currentPlayer = playerOrder.front();

            // Prüfen, ob eine Karte angeklickt wurde.
            for( int i = 0; i < Player.HAND_COUNT; i++ ) {
                int cardX1 = 240 + i * (Instruction.CARD_WIDTH + 20);
                int cardX2 = cardX1 + Instruction.CARD_WIDTH;
                int cardY1 = canvasHeight - (Instruction.CARD_HEIGHT + 20);
                int cardY2 = cardY1 + Instruction.CARD_HEIGHT;

                if( cmouseX > cardX1 && cmouseX < cardX2
                    && cmouseY > cardY1 && cmouseY < cardY2 ) {
                    if( !currentPlayer.isCardSelected(i) ) {
                        currentPlayer.selectCard(i);

                        if( !playerOrder.front().canSelectCards() ) {
                            currentPlayer.programRobot();

                            playerOrder.dequeue();
                            if( playerOrder.isEmpty() ) {
                                gameState = "execution phase";
                            } else {
                                gameState = "next turn";
                            }
                        }
                    } else {
                        currentPlayer.unselectCard(i);
                    }
                    redraw();
                    break;
                }
            }
        }
    }


    public static void main( String[] args ) {
        new RoboRally();
    }

}
