import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.lang.Math;
import java.util.Arrays;
import java.io.*;

/**
 * Die einzigen aktiven Akteure in der Roboterwelt sind die Roboter.
 * Die Welt besteht aus 15 * 12 Feldern.
 */

public class Planet extends World
{
    private static int zellenGroesse = 30;

    /**
     * Erschaffe eine Welt mit 15 * 12 Zellen.
     */
    public Planet() {
        // Initialisierung der Welt
        super(20, 18, zellenGroesse);
        setBackground("images/boden.png");
        setPaintOrder(String.class, Rover.class, Marke.class, Gestein.class, Huegel.class, Referee.ActorDelegate.class);
        setActOrder(Referee.ActorDelegate.class, Rover.class, Marke.class, Gestein.class, Huegel.class);
        Greenfoot.setSpeed(40);

        arenaErstellen();
    }

    public void arenaErstellen() {
        int arena = Utils.zufallsInt(4);
        if( arena == 0 ) {
            zufallskarte();
        } else {
            karteLaden("arena" +arena);
        }

        arenaStarten();
    }

    public void arenaStarten() {
        addObject(Referee.getInstance().getActor(), 0, 0);
    }

    /**
     * L?scht alle Objekte (Rover,H?gel,Gesteine,Markierungen,...) aus der Welt.
     */
    public void weltLeeren() {
        removeObjects(getObjects(Actor.class));
    }

    /**
     * Erstellt eine Karte aus einem String
     *
     * Es werden Rober, H?gel, Gesteine und Markierungen erstellt.
     * Dazu wird der String Zeilenweise verarbeitet. Jedes Zeichen
     * entspricht einer Zelle in der Welt. Je nachdem, welches
     * Zeichen gelesen wird, werden die verschiedenen Objekte erstellt
     * und platziert.
     * <dl>
     * <dt><code>r</code></dt>
     * <dd>Platziert einen Rover mit Blickrichtung rechts. Alternativ kann <code>&gt;</code> benutzt werden.</dd>
     * <dt><code>&lt;</code></dt>
     * <dd>Platziert einen Rover mit Blickrichtung links.</dd>
     * <dt><code>^</code></dt>
     * <dd>Platziert einen Rover mit Blickrichtung oben.</dd>
     * <dt><code>v</code></dt>
     * <dd>Platziert einen Rover mit Blickrichtung unten.</dd>
     * <dt><code>h</code</dt>
     * <dd>Platziert einen H?gel.</dd>
     * <dt><code>g</code</dt>
     * <dd>Platziert ein Gestein.</dd>
     * <dt><code>m</code</dt>
     * <dd>Platziert eine Marke.</dd>
     * <dt><code>z</code</dt>
     * <dd>Platziert ein zuf?lliges Objekt (oder keines).</dd>
     * </dl>
     *
     * Die Welt hat normalerweise 15-mal-12 Zellen. Das bedeutet, der
     * Karten-String soltle maximal 12 Zeilen und jede Zeile maximal
     * 15 Zeichen enthalten. Alle Zeichen dar?ber hinaus werden ignoriert.
     * Gro?- und Kleinschreibung wird nicht beachtet.
     *
     * Beispiel 1: Eine Welt, die von H?geln umgeben ist, einen Rover mit
     * Blickrichtung oben platziert, ein Gestein und einige Marken.
     * <pre>
     * hhhhhhhhhhhhhhh
     * hOOOOOOOOOOOOOh
     * hOOOOOmOOOmOOOh
     * hOOOOOOOOOOOOOh
     * hOOOOO^OOOOOOOh
     * hOOOOOOOOOOOOOh
     * hOOOOOOOOOOOOOh
     * hOOOOOgOOOmOOOh
     * hOOOOOOOOOOOOOh
     * hOOOOOOOOOOOOOh
     * hhhhhhhhhhhhhhh
     * </pre>
     *
     * Beispiel 2: Es wird der Rover und ein Gestein platziert.
     * <pre>
     *
     *
     *     R     G
     * </pre>
     *
     * Beispiel 3: Es werden der Rover und zwei Gesteine platziert.
     * Der Wassergehalt des ersten Gesteins wird auf 12 festgelegt.
     * <pre>
     *
     *
     *     R     G(12)G
     * </pre>
     */
    public void weltErstellen( String map ) {
        String[] lines = map.trim().split("\n");

        int y = 0;
        for( String line: lines ) {
            line = line.trim();
            if( y >= getHeight() || line.startsWith("//") || line.isEmpty() ) {
                continue;
            }

            parseLine(y, line);
            y += 1;
        }
    }

    private void parseLine( int pY, String pLine ) {
        pLine = pLine.trim();
        if( pY >= getHeight() || pLine.startsWith("//") || pLine.isEmpty() ) {
            return;
        }

        char[] chars = pLine.toLowerCase().toCharArray();

        Random r = new Random();
        int x = 0;
        for( int i = 0; i < chars.length; i++ ) {
            // Skip spaces
            if( chars[i] == ' ' ) {
                continue;
            }
            // Stop after exceeding the world bounds
            if( x >= getWidth() ) {
                break;
            }

            // Random cases first
            // replace current char with result
            String choices;
            switch( chars[i] ) {
                case 'z':
                    choices = " hmg";
                    chars[i] = choices.charAt(r.nextInt(choices.length()));
                    break;

                case '[':
                    // choices parsen
                    int j = i;
                    choices = "";
                    while( i+1 < chars.length && chars[i+1] != ']' ) {
                        choices += chars[++i];
                    }
                    if( i+1 < chars.length ) {
                        i += 1;
                    }
                    chars[i] = choices.charAt(r.nextInt(choices.length()));
                    break;
            }

            // Create object(s)
            if( chars[i] == '(' ) {
                while( i+1 < chars.length && chars[i+1] != ')' ) {
                    createObject(x, pY, chars[++i]);
                }
                i += 1;
            } else {
                createObject(x, pY, chars[i]);
            }
            x += 1;
        }
    }

    private void createObject( int pX, int pY, char pType ) {
        if( pX < 0 || pX >= getWidth() || pY < 0 || pY >= getHeight() ) {
            return;
        }

        switch( pType ) {
            case 'r':
            case '>':
                addObject(new Rover(), pX, pY);
                break;

            case '^':
                Rover r1 = new Rover();
                addObject(r1, pX, pY);
                r1.drehe("links");
                break;

            case 'v':
                Rover r2 = new Rover();
                addObject(r2, pX, pY);
                r2.drehe("rechts");
                break;

            case '<':
                Rover r3 = new Rover();
                addObject(r3, pX, pY);
                r3.drehe("links");
                r3.drehe("links");
                break;

            case 'm':
                addObject(new Marke(), pX, pY);
                break;

            case 'h':
            addObject(new Huegel(), pX, pY);
                break;

            case 'g':
            addObject(new Gestein(), pX, pY);
                break;
        }
    }

    /**
     * Lädt eine Karte aus einer Datei im maps-Ordner
     */
    public void karteLaden( String pFile ) {
        if( pFile.indexOf(".") < 0 ) {
            pFile += ".map";
        }

        String map = "";
        try {
            InputStream i = Planet.class.getResourceAsStream("maps/" + pFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(i));
            String st;
            while ((st = br.readLine()) != null) {
                map += st + "\n";
            }
        } catch(Exception e) {
            System.err.println("Karte "+pFile+" konnte nicht geladen werden.");
        }

        weltLeeren();
        weltErstellen(map);
    }

    /**
     * Erstellt eine prozedual generierte Zufallskarte.
     * see #zufallskarte(long)
     */
    public void zufallskarte() {
        Random r = new Random();
        zufallskarte(r.nextLong());
    }

    /**
     * Erstellt eine prozedual generierte  Pseudo-Zufallskarte
     *
     * Die generierte Karte wird abh?nig vom <var>seed</var>
     * generiert. Dabei wird nicht rein zuf?llig entschieden,
     * welche Objekte wo platziert werden, sondern es wird versucht
     * zusammenh?ngende H?gelketten zu erstellen.
     *
     * Bei gleichen Werten f?r <var>seed</var> werden gleiche
     * Karten erstellt. Die Objekte sind zuf?llig verteilt, es
     * kann aber eine Karte reproduziert werden, wenn derselbe
     * <var>seed</var> wiederverwendet wird.
     *
     * Der aktuelle <var>seed</var> wird unten rechts angezeigt.
     */
    public void zufallskarte( long seed ) {
        String map = "";

        // Generate random feature points
        int[][] points = new int[6][2];
        int maxX = getWidth(), maxY = getHeight();

        Random r = new Random(seed);
        for( int i = 0; i < points.length; i++ ) {
            points[i] = new int[2];
            points[i][0] = r.nextInt(maxX);
            points[i][1] = r.nextInt(maxY);
        }

        // Generate Noise
        int[][] dist = new int[maxX][maxY];
        for( int y = 0; y < maxY; y++ ) {
            for( int x = 0; x < maxX; x++ ) {
                double d1 = Integer.MAX_VALUE;
                for( int i = 0; i < points.length; i++ ) {
                    double d2 = Math.sqrt(Math.pow(points[i][0]-x, 2.0) + Math.pow(points[i][1]-y, 2.0));
                    if( d2 < d1 ) {
                        d1 = d2;
                    }
                }
                dist[x][y] = (int) Math.round(d1);
            }
        }

        // Generate map
        boolean roverSet = true;
        char nextChar = '#';
        for( int y = 0; y < maxY; y++ ) {
            for( int x = 0; x < maxX; x++ ) {
                if( dist[x][y] > 3 ) {
                    int rand = r.nextInt(100);
                    if( rand <= 35 ) {
                        nextChar = 'g';
                    } else if( rand <= 40 ) {
                        nextChar = 'm';
                    } else {
                        nextChar = '#';
                    }
                    if( !roverSet && rand <= 15 ) {
                        char[] positions = new char[]{'>','<','^','v'};
                        nextChar = positions[r.nextInt(4)];
                        roverSet = true;
                    }
                } else if( dist[x][y] < 2 )  {
                    nextChar = 'h';
                } else if( dist[x][y] == 2 )  {
                    if( r.nextInt(100) <= 60 ) {
                        nextChar = 'h';
                    } else {
                        nextChar = '#';
                    }
                } else {
                    nextChar = '#';
                }

                map += nextChar;
            }
            map += "\n";
        }

        // Display map
        weltLeeren();
        weltErstellen(map);

        // Show seed on map
        //showText(seed+"", getWidth()-3, getHeight()-1);

        // Mark feature points on map
        //for( int i = 0; i < points.length; i++ ) {
        //    addObject(new Marke(), points[i][0], points[i][1]);
        //}
    }

    /**
     * Erstellt eine komplett zuf?llige Karte
     *
     * Alle Objekte werden rein zuf?llig platziert und
     * die Welt rundherum mit einer "H?gelwand" umrandet.
     * Daher kann die entstehende Karte unter Umst?nden
     * schlecht nutzbar sein.
     */
    public void zufallskarte2() {
        String map = "";
        for( int y = 0; y < getHeight()-1; y++ ) {
            for( int x = 0; x < getWidth(); x++ ) {
                if( x == 0 || y == 0 || x == getWidth()-1 || y == getHeight()-2 ) {
                    map += "h";
                } else {
                    Random r = new Random();
                    int i = r.nextInt(10);
                    if( i < 5 ) {
                        map += "#";
                    } else if( i < 8 ) {
                        map += "h";
                    } else {
                        map += "g";
                    }
                }
            }
            map += "\n";
        }
        weltLeeren();
        weltErstellen(map);
    }

    /**
     * Erstellt eine Welt, die einen Weg enth?lt
     */
    public void zufallsweg() {
        int maxX = getWidth(), maxY = getHeight();
        int halfX = (int) (maxX/2), halfY = (int) (maxY/2);

        char[][] map = new char[maxX][maxY];
        for( int y = 0; y < maxY; y++ ) {
            for( int x = 0; x < maxX; x++ ) {
                map[x][y] = 'H';
            }
        }

        Random r = new Random();
        // Generate random center points
        int[] p1 = new int[2], p2 = new int[2];
        // left half
        p1[0] = r.nextInt(halfX-6)+3;
        p1[1] = r.nextInt(maxY-8)+4;
        p2[0] = r.nextInt(halfX-6)+3+halfX;
        p2[1] = r.nextInt(maxY-8)+4;

        // Generate random edge points

        // Generate path
        //int startX = p1[0]+3, startY = p1[1];

        map[p1[0]][p1[1]] = 'M';
        map[p2[0]][p2[1]] = 'M';

        while(p1[0] != p2[0] || p1[1] != p2[1] ) {
            int q = r.nextInt(2);
            if( p1[q] != p2[q] ) {
                p1[q] -= Math.signum(p1[q]-p2[q]);
                map[p1[0]][p1[1]] = '#';
            }
        }

        // print(map);

        String m = "";
        for( int y = 0; y < maxY; y++ ) {
            for( int x = 0; x < maxX; x++ ) {
                m += map[x][y];
            }
            m += "\n";
        }
        // Display map
        weltLeeren();
        weltErstellen(m);
    }

    private boolean finished( int[][] arr ) {
        for( int y = 0; y < arr[0].length; y++ ) {
            for( int x = 0; x < arr.length; x++ ) {
                if( arr[x][y] != 2 && arr[x][y] != 0 ) {
                    return false;
                }
            }
        }
        return true;
    }

    private void print(int[][] arr) {
        for( int y = 0; y < arr[0].length; y++ ) {
            for( int x = 0; x < arr.length; x++ ) {
                System.out.print(arr[x][y]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void print(char[][] arr) {
        for( int y = 0; y < arr[0].length; y++ ) {
            for( int x = 0; x < arr.length; x++ ) {
                System.out.print(arr[x][y]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
