import view.Schachbrett;

import java.util.Scanner;

/**
 * Hauptklasse des Projekts
 */
public class Schachprogramm {

    public static void main( String[] args ) {
        new Schachprogramm();
    }

    private List<Token> tokenliste;

    private String fehler;

    private Schachbrett brett;

    public Schachprogramm() {
        tokenliste = new List<>();

        brett = new Schachbrett();

        try( Scanner input = new Scanner(System.in) ) {
            System.out.println("Gib eine Schachaufstellung für schwarz ein und bestätige mit ENTER: ");
            //String eingabe = input.nextLine();
            String eingabe = "Ta8,Sb8,Lc8,Kd8,De8,Lf8,Sg8,Th8,Ba7,Bb7,Bc7,Bd7,Be7,Bf7,Bg7,Bh7;Ta1,Sb1,Lc1,Kd1,De1,Lf1,Sg1,Th1,Ba2,Bb2,Bc2,Bd2,Be2,Bf2,Bg2,Bh2";

            compile(eingabe);

            brett.bewegeFigur(4, 2, 4, 3);
            brett.bewegeFigur(5, 7, 5, 6);
            brett.bewegeFigur(4, 3, 4, 4);
            brett.bewegeFigur(4, 7, 4, 6);
            brett.bewegeFigur(5, 2, 5, 3);
            brett.bewegeFigur(4, 6, 4, 5);
            brett.bewegeFigur(6, 1, 2, 5);
            brett.bewegeFigur(3, 7, 3, 6);
            brett.bewegeFigur(2, 5, 1, 4);
        }
    }

    public void compile( String pAufstellung ) {
        if( !scanne(pAufstellung) ) {
            System.out.println("Fehler bei der lexikalischen Analyse:");
            System.out.println(fehler);
            return;
        }

        if( !parse() ) {
            System.out.println("Fehler bei der syntaktischen Analyse:");
            System.out.println(fehler);
            return;
        }

        run();
    }

    public int scanneBuchstabe( int pZustand, String pBuchstabe ) {
        switch( pZustand ) {
            // Zustand q0
            case 0:
                switch( pBuchstabe ) {
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                        tokenliste.append(new Token("REIHE", pBuchstabe));
                        return 0;

                    case "a":
                    case "b":
                    case "c":
                    case "d":
                    case "e":
                    case "f":
                    case "g":
                    case "h":
                        tokenliste.append(new Token("LINIE", pBuchstabe));
                        return 0;

                    case "B":
                    case "K":
                    case "D":
                    case "T":
                    case "S":
                    case "L":
                        tokenliste.append(new Token("FIGUR", pBuchstabe));
                        return 0;

                    case ",":
                        tokenliste.append(new Token("TRENNZEICHEN"));
                        return 0;

                    case ";":
                        tokenliste.append(new Token("SWTRENNZEICHEN"));
                        return 0;

                    // Falsche Eingabe, Fehler!
                    default:
                        fehler = "Fehler im Wort:\nDer Buchstabe " + pBuchstabe + " gehört nicht zur Sprache!";
                        return -1;
                }
        }
        return -1;
    }

    public boolean scanne( String pEingabe ) {
        // Zustand des Automaten
        int state = 0;

        for( int i = 0; i < pEingabe.length(); i += 1 ) {
            state = scanneBuchstabe(state, Character.toString(pEingabe.charAt(i)));
        }

        // Zustand 0 ist Endzustand
        if( state == 0 ) {
            return true;
        } else {
            return false;
        }
    }

    public int parseToken( int pZustand, Token pToken ) {
        switch( pZustand ) {
            // Zustand q0
            case 0:
                switch( pToken.getType() ) {
                    case "FIGUR":
                        return 1;

                    case "SWTRENNZEICHEN":
                        return 4;

                    default:
                        fehler = "Definition muss mit einer Figure beginnen: " + pToken.getValue();
                        return -1;
                }


            // Zustand q1
            case 1:
                switch( pToken.getType() ) {
                    case "LINIE":
                        return 2;

                    default:
                        fehler = "Auf Figur muss die Linie folgen: " + pToken.getValue();
                        return -1;
                }


            // Zustand q2
            case 2:
                switch( pToken.getType() ) {
                    case "REIHE":
                        return 3;

                    default:
                        fehler = "Auf Linie muss die Reihe folgen: " + pToken.getValue();
                        return -1;
                }

            // Zustand q3
            case 3:
                switch( pToken.getType() ) {
                    case "TRENNZEICHEN":
                        return 0;

                    case "SWTRENNZEICHEN":
                        return 4;

                    default:
                        fehler = "Figurenpositionen müssen mit \",\" getrennt werden.";
                        return -1;
                }

            // Zustand q4
            case 4:
                switch( pToken.getType() ) {
                    case "FIGUR":
                        return 5;

                    default:
                        fehler = "Definition muss mit einer Figure beginnen: " + pToken.getValue();
                        return -1;
                }


            // Zustand q5
            case 5:
                switch( pToken.getType() ) {
                    case "LINIE":
                        return 6;

                    default:
                        fehler = "Auf Figur muss die Linie folgen: " + pToken.getValue();
                        return -1;
                }


            // Zustand q6
            case 6:
                switch( pToken.getType() ) {
                    case "REIHE":
                        return 7;

                    default:
                        fehler = "Auf Linie muss die Reihe folgen: " + pToken.getValue();
                        return -1;
                }

            // Zustand q3
            case 7:
                switch( pToken.getType() ) {
                    case "TRENNZEICHEN":
                        return 4;

                    default:
                        fehler = "Figurenpositionen müssen mit \",\" getrennt werden.";
                        return -1;
                }
        }
        return -1;
    }

    public boolean parse() {
        // Aktueller Zustand des Automaten
        int state = 0;

        // Tokens durchgehen
        tokenliste.toFirst();
        while( tokenliste.hasAccess() ) {
            Token currentToken = tokenliste.getContent();

            state = parseToken(state, currentToken);

            tokenliste.next();
        }

        if( state == 3 || state == 7 ) {
            return true;
        } else {
            // Fehler, da nicht im Endzustand
             fehler = "Fehlende oder falsche Zeichen in Definition.";
            return false;
        }
    }

    public void run() {
        String figur = "";
        int linie = 0, reihe = 0;
        boolean isBlack = true;

        tokenliste.toFirst();
        while( tokenliste.hasAccess() ) {
            Token currentToken = tokenliste.getContent();
            switch( currentToken.getType() ) {
                case "FIGUR":
                    figur = switch( currentToken.getValue() ) {
                        case "K" -> "Koenig";
                        case "D" -> "Dame";
                        case "S" -> "Springer";
                        case "T" -> "Turm";
                        case "L" -> "Laeufer";
                        case "B" -> "Bauer";
                        default -> figur;
                    };
                    break;

                case "LINIE":
                    linie = currentToken.getValue().charAt(0) - 96;
                    break;


                case "REIHE":
                    reihe = Integer.parseInt(currentToken.getValue());

                    if( isBlack ) {
                        brett.neueFigurSchwarz(linie, reihe, figur);
                    } else {
                        brett.neueFigurWeiss(linie, reihe, figur);
                    }
                    break;

                case "SWTRENNZEICHEN":
                    isBlack = false;
                case "TRENNZEICHEN":
                    figur = "";
                    linie = 0;
                    reihe = 0;
                    break;
            }

            tokenliste.next();
        }
    }

}
