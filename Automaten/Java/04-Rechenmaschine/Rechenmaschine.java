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
        Scanner input = new Scanner(System.in);

        while( true ) {
            tokenlist = new List<>();
            /*aufg* 1
            System.out.println("Gib einen Plusterm ein und bestätige mit ENTER (quit zum Beenden): ");
            *aufg*/
            /*aufg* 2
            System.out.println("Gib einen Minusterm ein und bestätige mit ENTER (quit zum Beenden): ");
            *aufg*/
            //ml*
            System.out.println("Gib einen Term ein und bestätige mit ENTER (quit zum Beenden): ");
            //*ml
            String eingabe = input.nextLine();

            if( eingabe.equalsIgnoreCase("quit") ) {
                break;
            }

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
        input.close();
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

                        /*aufg* 1
                        case '+':
                            fehler = "Fehler im Wort " +pEingabe+ ":\nDas Wort darf nicht mit + beginnen!";
                        *aufg*/
                        /*aufg* 2
                        case '-':
                            fehler = "Fehler im Wort " +pEingabe+ ":\nDas Wort darf nicht mit - beginnen!";
                        *aufg*/
                        //ml*
                        case '+':
                        case '-':
                            fehler = "Fehler im Wort " +pEingabe+ ":\nDas Wort darf nicht mit + oder - beginnen!";
                        //*ml
                            return false;

                        default:
                            fehler = "Fehler im Wort " +pEingabe+ ":\nDer Buchstabe " + buchstabe + " gehört nicht zur Sprache!";
                            return false;
                    }
                    break;


                case 1:
                    switch( buchstabe ) {
                        /*aufg* 1
                        case '+':
                        *aufg*/
                        /*aufg* 2
                        case '-':
                        *aufg*/
                        //ml*
                        case '+':
                        case '-':
                        //*ml
                            tokenlist.append(new Token("OPERAND", currentToken));
                            currentToken = "";
                            /*aufg*
                            tokenlist.append(new Token("OPERATOR"));
                            *aufg*/
                            //ml*
                            tokenlist.append(new Token("OPERATOR", Character.toString(buchstabe)));
                            //*ml
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
                        //ml* 1
                        case '+':
                        //*ml
                        //ml* 2
                        case '-':
                        //*ml
                            /*aufg*
                            tokenlist.append(new Token("OPERATOR"));
                            *aufg*/
                            //ml*
                            tokenlist.append(new Token("OPERATOR", Character.toString(buchstabe)));
                            //*ml
                            state = 0;
                            break;

                        default:
                            /*aufg* 1
                            fehler = "Fehler im Wort " +pEingabe+ ":\nNach 0 am Anfang muss ein + folgen!";
                            *aufg*/
                            /*aufg* 2
                            fehler = "Fehler im Wort " +pEingabe+ ":\nNach 0 am Anfang muss ein - folgen!";
                            *aufg*/
                            //ml*
                            fehler = "Fehler im Wort " +pEingabe+ ":\nNach 0 am Anfang muss ein + oder - folgen!";
                            //*ml
                            return false;
                    }
                    break;
            }
        }

        if( state == 0 ) {
            /*aufg* 1
            fehler = "Fehler im Wort " +pEingabe+ ":\nDas Wort darf nicht auf + enden!";
            *aufg*/
            /*aufg* 2
            fehler = "Fehler im Wort " +pEingabe+ ":\nDas Wort darf nicht auf - enden!";
            *aufg*/
            //ml*
            fehler = "Fehler im Wort " +pEingabe+ ":\nDas Wort darf nicht auf + oder - enden!";
            //*ml
            return false;
        } else {
            // Fall noch eine Zahl im "Puffer" ist, wird diese
            // noch als neues Token eingefügt
            if( currentToken.length() > 0 ) {
                tokenlist.append(new Token("OPERAND", currentToken));
            }
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

        //ml*
        String currentOp = "+";
        //*ml
        tokenlist.toFirst();
        /*aufg* 2
        result = Integer.parseInt(tokenlist.getContent().getToken());
        tokenlist.next();
        *aufg*/
        while( tokenlist.hasAccess() ) {
            Token currentToken = tokenlist.getContent();

            if( currentToken.getType().equals("OPERAND") ) {
                /*aufg* 1
                result += Integer.parseInt(currentToken.getToken());
                *aufg*/
                /*aufg* 2
                result -= Integer.parseInt(currentToken.getToken());
                *aufg*/
                //ml*
                if( currentOp.equals("+") ) {
                    result += Integer.parseInt(currentToken.getToken());
                } else if( currentOp.equals("-") )  {
                    result -= Integer.parseInt(currentToken.getToken());
                }
                //*ml
            } else if ( currentToken.getType().equals("OPERATOR") ) {
                /*aufg*
                // Nichts, da es nur einen Operator gibt ...
                *aufg*/
                //ml*
                currentOp = currentToken.getToken();
                //*ml
            }
            tokenlist.next();
        }
    }

}
