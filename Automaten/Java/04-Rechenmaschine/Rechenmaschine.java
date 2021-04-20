import java.util.Scanner;

/**
 * Hauptklasse des Projekts
 */
public class Rechenmaschine {

    public static void main(String[] args) {
        new Rechenmaschine();
    }

    private List<Token> tokenlist;

    private String fehler;

    private int result;

    public Rechenmaschine() {
        tokenlist = new List<>();

        Scanner input = new Scanner(System.in);
        System.out.println("Gib einen Plusterm ein und bestätige mit ENTER: ");
        String eingabe = input.nextLine();
        input.close();

        if( !scanne(eingabe) ) {
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
        System.out.println("Ergebnis der Ausführung: " + result);
    }

    public boolean scanne( String pEingabe ) {
        char[] eingabe = pEingabe.toCharArray();

        int state = 0;
        String currentToken = "";

        for( char buchstabe: eingabe ) {
            switch( state ) {
                case 0:
                    switch( buchstabe ) {
                        case '0':
                            tokenlist.append(new Token("OPERAND", "0"));
                            state = 2;
                            break;

                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            currentToken += buchstabe;
                            state = 1;
                            break;

                        case '+':
                            fehler = "Fehler im Wort " +pEingabe+ ":\nDas Wort darf nicht mit + beginnen!";
                            return false;

                        default:
                            fehler = "Fehler im Wort " +pEingabe+ ":\nDer Buchstabe " + buchstabe + " gehört nicht zur Sprache!";
                            return false;
                    }
                    break;


                case 1:
                    switch( buchstabe ) {
                        case '+':
                            tokenlist.append(new Token("OPERAND", currentToken));
                            currentToken = "";
                            tokenlist.append(new Token("OPERATOR"));
                            state = 0;
                            break;

                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            currentToken += buchstabe;
                            state = 1;
                            break;

                        default:
                            fehler = "Fehler im Wort " +pEingabe+ ":\nDer Buchstabe " + buchstabe + " gehört nicht zur Sprache!";
                            return false;
                    }
                    break;


                case 2:
                    switch( buchstabe ) {
                        case '+':
                            tokenlist.append(new Token("OPERATOR"));
                            state = 0;
                            break;

                        default:
                            fehler = "Fehler im Wort " +pEingabe+ ":\nNach 0 am Anfang muss ein + folgen!";
                            return false;
                    }
                    break;
            }
        }

        if( state == 0 ) {
            fehler = "Fehler im Wort " +pEingabe+ ":\nDas Wort darf nicht auf + enden!";
            return false;
        } else {
            tokenlist.append(new Token("OPERAND", currentToken));
            return true;
        }
    }

    public boolean parse() {

        int state = 0;

        tokenlist.toFirst();
        while( tokenlist.hasAccess() ) {
            Token currentToken = tokenlist.getContent();

            switch( state ) {
                case 0:
                    if( currentToken.getType().equals("OPERATOR") ) {
                        fehler = "Rechenterm darf nicht mit einem Operator starten!";
                        return false;
                    } else if( currentToken.getType().equals("OPERAND") ) {
                        state = 1;
                    } else {
                        fehler = "Unbekanntes Token: "+currentToken.getType();
                        return false;
                    }
                    break;


                case 1:
                    if( currentToken.getType().equals("OPERATOR") ) {
                        state = 0;
                    } else if( currentToken.getType().equals("OPERAND") ) {
                        fehler = "Auf einen Operanden muss ein Operator folgen!";
                        return false;
                    } else {
                        fehler = "Unbekanntes Token: "+currentToken.getType();
                        return false;
                    }
                    break;
            }

            tokenlist.next();
        }

        if( state == 0 ) {
            fehler = "Ein Rechenterm darf nicht auf einen Operator enden!";
            return false;
        } else {
            return true;
        }
    }

    public void run() {
        result = 0;

        tokenlist.toFirst();
        while( tokenlist.hasAccess() ) {
            Token currentToken = tokenlist.getContent();

            if( currentToken.getType().equals("OPERAND") ) {
                result += Integer.parseInt(currentToken.getToken());
            }

            tokenlist.next();
        }
    }

}
