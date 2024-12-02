import view.Hochregallager;

public class Lagerverwaltung {

    public static void main( String[] args ) {
        new Lagerverwaltung();
    }

    private Hochregallager lager;

    private List<Token> tokenlist;

    private String fehler;

    public Lagerverwaltung() {
        this.lager = new Hochregallager();
        this.tokenlist = new List<>();

        String[] boxen = new String[]{
            "#C7", "#D5"
        };
        for( String boxCode: boxen ) {
            lager.newBox(boxCode);

            if( !scanne(boxCode) ) {
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
    }


    public void run() {
        // TODO: Implementieren
        // Nachdem Scanner und Parser das Wort übersetzt haben,
        // muss der Interpreter nun das richtige Lagerfach ansteuern.
        tokenlist.toFirst();
        while( tokenlist.hasAccess() ) {
            Token currentToken = tokenlist.getContent();
            tokenlist.next();

            switch( currentToken.getType() ) {
                case "STARTSYMBOL":
                    // Box aufladen
                    this.lager.loadBox();
                    break;

                case "EBENE":
                    // Kran zur richtigen Ebene bewegen:
                    // zB "C" bedeutet zweimal hoch
                    /*aufg*
                    lager.moveCraneUp();
                    *aufg*/
                    //ml*
                    switch( currentToken.getToken() ) {
                        case "D":
                            lager.moveCraneUp();
                        case "C":
                            lager.moveCraneUp();
                        case "B":
                            lager.moveCraneUp();
                            break;
                    }
                    //*ml
                    break;

                case "FACH":
                    // Kran zur richtigen Ebene bewegen:
                    // zB "3" bedeutet zweimal rechts

                    // Fach in einen Integer umwandeln
                    int fach = Integer.parseInt(currentToken.getToken()) - 1;

                    /*aufg*
                    lager.moveCraneUp();
                    *aufg*/
                    //ml*
                    for( ;fach > 0; fach -= 1 ) {
                        lager.moveCraneRight();
                    }
                    //*ml

                    // Zum Schluss Box entladen und wieder zum Ausgangspunkt zurück!
                    //ml*
                    lager.unloadBox();
                    lager.moveCraneToStart();
                    //*ml
                    break;
            }
        }
    }


    public boolean scanne( String pEingabe ) {
        char[] eingabe = pEingabe.toCharArray();

        // Zustand des Automaten
        int state = 0;

        //
        for( char buchstabe : eingabe ) {
            switch( state ) {
                // Zustand q0
                case 0:
                    switch( buchstabe ) {
                        // Startsymbol
                        case '#':
                            tokenlist.append(new Token("STARTSYMBOL"));
                            break;

                        // Ebene
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                            // Token erstellen
                            tokenlist.append(new Token("EBENE", "" + buchstabe));
                            // Nächster Zustand
                            state = 0;
                            break;

                        // Fach
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                            // Token erstellen
                            tokenlist.append(new Token("FACH", "" + buchstabe));
                            // Nächster Zustand
                            state = 0;
                            break;


                        // Falsche Eingabe, Fehler!
                        default:
                            fehler = "Fehler im Wort " + pEingabe + ":\nDer Buchstabe " + buchstabe + " gehört nicht zur Sprache!";
                            return false;
                    }
                    break;
            }
        }

        // Zustand 0 ist Endzustand
        if( state == 0 ) {
            return true;
        } else {
            fehler = "Fehler im Wort " + pEingabe + ":\nDas Wort wurde nicht akzeptiert!";
            return false;
        }
    }

    public boolean parse() {
        // Aktueller Zustand des Automaten
        int state = 0;

        // Tokens durchgehen
        tokenlist.toFirst();
        while( tokenlist.hasAccess() ) {
            Token currentToken = tokenlist.getContent();

            switch( state ) {
                // Zustand q0
                case 0:
                    switch( currentToken.getType() ) {
                        case "STARTSYMBOL":
                            state = 1;
                            break;

                        default:
                            fehler = "Definition muss mit dem Startsymbol beginnen.";
                            return false;
                    }
                    break;

                // Zustand q1
                case 1:
                    switch( currentToken.getType() ) {
                        case "EBENE":
                            state = 2;
                            break;

                        default:
                            fehler = "Zweites Symbol muss die Ebene sein.";
                            return false;
                    }
                    break;

                // Zustand q1
                case 2:
                    switch( currentToken.getType() ) {
                        case "FACH":
                            state = 3;
                            break;

                        default:
                            fehler = "Zweites Symbol muss die Ebene sein.";
                            return false;
                    }
                    break;
            }

            tokenlist.next();
        }

        // Zustand q3 ist Endzustand
        if( state == 3 ) {
            // Alles korrekt
            return true;
        } else {
            fehler = "Fehlerhafte Syntax! Das Wort kann nicht übersetzt werden.";
            return false;
        }
    }

}
