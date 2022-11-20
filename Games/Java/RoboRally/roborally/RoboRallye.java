package roborally;

import roborally.instructions.*;
import roborally.tiles.Tile;
import schule.ngb.zm.Zeichenmaschine;

import java.awt.Font;

/**
 * Hauptklasse des Robo Rally Spiels.
 */
public class RoboRallye extends Zeichenmaschine {

    /**
     * Konfiguration: Breite der Fabrikkarte (Anzahl Kacheln).
     */
    public static final int MAP_WIDTH = 20;

    /**
     * Konfiguration: Höhe der Fabrikkarte (Anzahl Kacheln).
     */
    public static final int MAP_HEIGHT = 15;

    /**
     * Konfiguration: Kantenlänge einer Kachel.
     */
    public static final int TILE_SIZE = 40;

    /**
     * Konfiguration:Größe eines Roboters.
     */
    public static final int ROBO_SIZE = TILE_SIZE - 10;

    /**
     * Konfiguration: Anzahl Karten auf der Hand eines Spielers.
     */
    public static final int HAND_COUNT = 8;

    /**
     * Konfiguration: Anzahl an Karten, die von der Hand ausgewählt werden.
     */
    public static final int SELECT_COUNT = 5;

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
    private Map map;

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
    public RoboRallye() {
        super(MAP_WIDTH * TILE_SIZE, MAP_HEIGHT * TILE_SIZE, "RoboRallye");
    }

    @Override
    public void setup() {
        gameState = "starting";

        map = new Map();
        map.createMap();

        players = new List<>();
        for( int i = 0; i < 2; i++ ) {
            players.append(new Player("Player " + (i + 1), map.getTile(2, i * 2 + 3)));
        }

        playerOrder = new Queue<>();
        deck = new Stack<>();

        round = 0;
        startNextRound();

        drawing.setFont("Comic Sans MS", 16, Font.BOLD);
    }

    public void shuffleDeck() {
        Instruction[] cards = new Instruction[84];
        for( int i = 0; i < 84; i += 7 ) {
            cards[i] = new ForwardInstruction();
            cards[i + 1] = new ForwardInstruction(2);
            cards[i + 2] = new ForwardInstruction(3);
            cards[i + 3] = new BackwardsInstruction(2);
            cards[i + 4] = new TurnLeftInstruction();
            cards[i + 5] = new TurnRightInstruction();
            cards[i + 6] = new TurnAroundInstruction();
        }
        cards = shuffle(cards);

        deck = new Stack<>();
        for( int i = 0; i < cards.length; i++ ) {
            deck.push(cards[i]);
        }
    }

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

    public void executeInstructions() {
        gameState = "executing";
        Tile winTile = map.getWinTile();

        for( int i = 0; i < SELECT_COUNT; i++ ) {
            players.toFirst();
            while( players.hasAccess() ) {
                Player currentPlayer = players.getContent();
                Robot robot = currentPlayer.getRobot();
                robot.executeNextInstruction();

                if( winTile.hasRobot() && winTile.getRobot().equals(robot) ) {
                    gameState = "game ended";

                    redraw();
                    drawWinScreen(currentPlayer);
                    render();
                    return;
                }

                players.next();
            }

            map.executeTiles();
            redraw();
            delay(500);
        }

        startNextRound();
        redraw();
    }

    @Override
    public void draw() {
        drawing.clear();

        map.draw(drawing);

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

    public void drawNextButton() {
        drawing.noStroke();
        drawing.setFillColor(33, 200);
        drawing.rect(0, 0, canvasWidth, canvasHeight, NORTHWEST);
        drawing.setStrokeColor(33);
        drawing.setFillColor(playerOrder.front().getRobot().getColor());
        drawing.roundedRect(canvasWidth / 2, canvasHeight / 2, TILE_SIZE * 6, TILE_SIZE * 2, 8);
        drawing.setFontSize(32);
        drawing.text(playerOrder.front().getName(), canvasWidth / 2, canvasHeight / 2);
    }

    private void drawWinScreen( Player player ) {
        drawing.noStroke();
        drawing.setFillColor(33, 200);
        drawing.rect(0, 0, canvasWidth, canvasHeight, NORTHWEST);
        drawing.setStrokeColor(33);
        drawing.setFillColor(player.getRobot().getColor());
        drawing.roundedRect(canvasWidth / 2, canvasHeight / 2, TILE_SIZE * 10, TILE_SIZE * 2, 8);
        drawing.setFontSize(32);
        drawing.text(player.getName() + " gewinnt", canvasWidth / 2, canvasHeight / 2);
    }

    public void drawCards() {
        Player currentPlayer = playerOrder.front();

        for( int i = 0; i < currentPlayer.getHand().length; i++ ) {
            currentPlayer.getHand()[i].draw(
                50 + i * (Instruction.CARD_WIDTH + 20),
                canvasHeight - (Instruction.CARD_HEIGHT + 20),
                drawing
            );
        }

        int prio = 1;
        currentPlayer.getSelectedCards().toFirst();
        while( currentPlayer.getSelectedCards().hasAccess() ) {
            int i = currentPlayer.getSelectedCards().getContent();
            currentPlayer.getSelectedCards().next();

            int cardX1 = 50 + i * (Instruction.CARD_WIDTH + 20);
            int cardY1 = canvasHeight - (Instruction.CARD_HEIGHT + 20);

            drawing.setStrokeColor(BLACK);
            drawing.setStrokeWeight(2);
            drawing.setFillColor(RED);
            drawing.circle(
                cardX1 + Instruction.CARD_WIDTH / 2,
                cardY1 + Instruction.CARD_HEIGHT / 2,
                20
            );
            drawing.setFontSize(16);
            drawing.text(
                "" + prio,
                cardX1 + Instruction.CARD_WIDTH / 2,
                cardY1 + Instruction.CARD_HEIGHT / 2
            );

            prio += 1;
        }
    }

    @Override
    public void mouseClicked() {
        if( gameState == "next turn" ) {
            int buttonX1 = canvasWidth / 2 - TILE_SIZE * 3;
            int buttonX2 = canvasWidth / 2 + TILE_SIZE * 3;
            int buttonY1 = canvasHeight / 2 - TILE_SIZE;
            int buttonY2 = canvasHeight / 2 + TILE_SIZE;

            if( cmouseX > buttonX1 && cmouseX < buttonX2
                && cmouseY > buttonY1 && cmouseY < buttonY2 ) {
                gameState = "card select";
                redraw();
            }
        } else if( gameState == "card select" ) {
            Player currentPlayer = playerOrder.front();

            // Prüfen, ob eine Karte angeklickt wurde.
            for( int i = 0; i < HAND_COUNT; i++ ) {
                int cardX1 = 50 + i * (Instruction.CARD_WIDTH + 20);
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
        new RoboRallye();
    }

}
