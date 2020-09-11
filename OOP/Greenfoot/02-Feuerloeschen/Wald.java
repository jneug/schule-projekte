import greenfoot.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.lang.Math;
import java.io.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Die einzigen aktiven Akteure im Wald sind die Feuerbots.
 * Die Welt besteht aus 15 * 12 Feldern.
 */
public class Wald extends World {

    private static final int breite = 15;
    private static final int hoehe = 12;
    private static final int zellenGroesse = 50;

    /**
     * Erschaffe eine Welt mit 15 * 12 Zellen.
     */
    public Wald() {
        // Initialisierung der Welt
        super(breite, hoehe, zellenGroesse);
        setBackground("images/gras.png");
        setPaintOrder(String.class, Feuerbot.class, Alarm.class, Feuer.class, Pflanze.class, Stein.class);
        Greenfoot.setSpeed(50);

        // Diese Zeile auskommentieren, um beim Start eine Zufallskarte zu erstellen.
        //zufallskarte();

        // Diese Zeile auskommentieren, um beim Start eine Karte aus dem Maps-Order zu laden.
        //karteLaden("empty");

        // Diese Zeile auskommentieren, um beim Start eine eigene Karte zu erstellen.
        //weltAusKarteErstellen(".\n.\n....HH.HHH.\n..R..G.GG.G..M\n......HH.HH.H.");
    }

    /**
     * Löscht alle Objekte (Rover,Hügel,Gesteine,Markierungen,...) aus der Welt.
     */
    public void weltLeeren() {
        removeObjects(getObjects(Actor.class));
    }

    /**
     * Es werden Rober, Hügel, Gesteine und Markierungen erstellt.
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
     * <dd>Platziert einen Hügel.</dd>
     * <dt><code>g</code</dt>
     * <dd>Platziert ein Gestein.</dd>
     * <dt><code>m</code</dt>
     * <dd>Platziert eine Marke.</dd>
     * <dt><code>z</code</dt>
     * <dd>Platziert ein zufälliges Objekt (oder keines).</dd>
     * </dl>
     * <p>
     * Die Welt hat normalerweise 15-mal-12 Zellen. Das bedeutet, der
     * Karten-String soltle maximal 12 Zeilen und jede Zeile maximal
     * 15 Zeichen enthalten. Alle Zeichen darüber hinaus werden ignoriert.
     * Groß- und Kleinschreibung wird nicht beachtet.
     * <p>
     * Beispiel 1: Eine Welt, die von Hügeln umgeben ist, einen Rover mit
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
     * <p>
     * Beispiel 2: Es wird der Rover und ein Gestein platziert.
     * <pre>
     *
     *
     *     R     G
     * </pre>
     * <p>
     * Beispiel 3: Es werden der Rover und zwei Gesteine platziert.
     * Der Wassergehalt des ersten Gesteins wird auf 12 festgelegt.
     * <pre>
     *
     *
     *     R     G(12)G
     * </pre>
     */
    /*
     * Erstellt eine Welt aus einer als Text codierten Karte.
     */
    public void weltAusKarteErstellen( String map ) {
        HashMap<String, String[][]> mapData = this.parseMap(map);

        // this.weltLeeren();
        this.createWorld(mapData);
    }

    private HashMap<String, String[][]> parseMap( String map ) {
        HashMap<String, String[][]> parts = new HashMap<>(12);

        String currentPart = "map";
        ArrayList<String[]> partList = new ArrayList<>(getHeight());

        String[] lines = map.trim().split("\n");

        for( String line: lines ) {
            line = line.toLowerCase().trim();

            if( line.startsWith("//") || line.isEmpty() ) {
                continue;
            }

            // Start of a new map part
            if( line.matches("^\\{([A-Za-z][A-Za-z0-9-_]*)\\}$") ) {
                if( currentPart.equals("map") ) {
                    String[][] fullMap = partList.toArray(new String[getHeight()][0]);
                    // TODO: Why are there null elements in the full map array???
                    // Is there a better way to do this?
                    for( int k = 0; k < fullMap.length; k++ ) {
                        if( fullMap[k] == null || fullMap[k].length < getWidth() ) {
                            fullMap[k] = new String[getWidth()];
                        }
                    }
                    parts.put(currentPart, fullMap);
                } else {
                    parts.put(currentPart, partList.toArray(new String[0][0]));
                }

                currentPart = line.substring(1, line.length()-1);
                partList.clear();
            } else {
                partList.add(parseLine(line, currentPart.equals("map")));
            }
        }
        parts.put(currentPart, partList.toArray(new String[0][0]));

        return parts;
    }

    private String[] parseLine( String pLine, boolean fullWidth ) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder currentString = new StringBuilder();
        boolean inParantheses = false;

        int x = 0;
        for( int i = 0; i < pLine.length(); i++ ) {
            char c = pLine.charAt(i);
            // Skip spaces
            if( Character.isWhitespace(c) ) {
                continue;
            }

            currentString.append(c);

            switch( c ) {
                case '[':
                case '(':
                case '{':
                    inParantheses = true;
                    break;
                case ')':
                    if( inParantheses && currentString.charAt(0) == '(') {
                        inParantheses = false;
                    }
                    break;
                case '}':
                    if( inParantheses && currentString.charAt(0) == '{') {
                        inParantheses = false;
                    }
                    break;
                case ']':
                    if( inParantheses && currentString.charAt(0) == '[') {
                        inParantheses = false;
                    }
                    break;
            }

            if( !inParantheses ) {
                result.add(currentString.toString());
                currentString = new StringBuilder();
                x += 1;
            }

            // Stop after exceeding the world bounds
            if( x >= getWidth() ) {
                break;
            }
        }

        if( fullWidth ) {
            return result.toArray(new String[getWidth()]);
        } else {
            return result.toArray(new String[0]);
        }
    }

    private void createWorld( HashMap<String, String[][]> mapData ) {
        String[][] map = mapData.get("map");

        Random r = new Random();

        for( int y = 0; y < map.length; y++ ) {
            for( int x = 0; x < map[y].length; x++ ) {
                if( map[y][x] == null ) {
                    continue;
                }

                // Random choices
                if( map[y][x].equals("z") ) {
                    String choices = ".pasf";
                    map[y][x] = Character.toString(choices.charAt(r.nextInt(choices.length())));
                } else if( map[y][x].startsWith("[") ) {
                    if( map[y][x].indexOf('{') >= 0 ) {
                        ArrayList<String> choices = new ArrayList<>(8);
                        StringBuilder s = new StringBuilder();
                        for( char c: map[y][x].substring(1,map[y][x].length()-1).toCharArray() ) {
                            if( s.length() > 0 && s.charAt(0) == '{' ) {
                                s.append(c);
                                if( c == '}' ) {
                                    choices.add(s.toString());
                                    s = new StringBuilder();
                                }
                            } else if( c == '{' ){
                                s.append(c);
                            } else {
                                choices.add(Character.toString(c));
                            }
                        }
                        map[y][x] = choices.get(r.nextInt(choices.size()));
                    } else {
                        String choices = map[y][x].substring(1, map[y][x].length() - 1);
                        map[y][x] = Character.toString(choices.charAt(r.nextInt(choices.length())));
                    }
                }

                // Replace parts before parsing real cell content
                if( map[y][x].startsWith("{") ) {
                    String partName = map[y][x].substring(1, map[y][x].length()-1);
                    String[][] partMap = mapData.getOrDefault(partName, new String[0][0]);
                    copyArea(partMap, map, x, y);
                }

                // Create object(s)
                if( map[y][x].startsWith("(")  ) {
                    for( char type: map[y][x].toCharArray() ) {
                        createObject(x, y, type);
                    }
                } else {
                    createObject(x, y, map[y][x].charAt(0));
                }
            }
        }
    }

    private void copyArea(String[][] from, String[][] to, int x, int y) {
        int maxY = Math.min(from.length, to.length-y);
        for(int i = 0; i < maxY; i++) {
            int maxX = Math.min(from[i].length, to[i].length-x);
            if( maxX >= 0 ) {
                System.arraycopy(from[i], 0, to[i + y], x, maxX);
            }
        }
    }

    /**
     * Erstellt ein Actor-Objekt in der Welt.
     * @param pX x-Position
     * @param pY y-Position
     * @param pType Typ des Actors
     * @see #weltAusKarteErstellen(String)
     */
    private void createObject( int pX, int pY, char pType ) {
        if( pX < 0 || pX >= getWidth() || pY < 0 || pY >= getHeight() ) {
            return;
        }

        switch( pType ) {
            case 'b':
            case '>':
                addObject(new Feuerbot(), pX, pY);
                break;

            case '^':
                Feuerbot r1 = new Feuerbot();
                addObject(r1, pX, pY);
                r1.setRotation(270);
                break;

            case 'v':
                Feuerbot r2 = new Feuerbot();
                addObject(r2, pX, pY);
                r2.setRotation(90);
                break;

            case '<':
                Feuerbot r3 = new Feuerbot();
                addObject(r3, pX, pY);
                r3.setRotation(180);
                break;

            case 'a':
                addObject(new Alarm(), pX, pY);
                break;

            case 'p':
                addObject(new Pflanze(), pX, pY);
                break;

            case 's':
                addObject(new Stein(), pX, pY);
                break;

            case 'f':
                addObject(new Feuer(), pX, pY);
                break;
        }
    }

    /**
     * Lädt eine Karte aus einer Datei im maps-Ordner.
     * @param pFile Dateiname einer Datei im maps-Ordner
     */
    public void karteLaden( String pFile ) {
        if( !pFile.contains(".") ) {
            pFile += ".map";
        }

        StringBuilder map = new StringBuilder();
        try {
            InputStream i = Wald.class.getResourceAsStream("maps/" + pFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(i));
            String st;
            while( (st = br.readLine()) != null ) {
                map.append(st);
                map.append("\n");
            }
            i.close();
        } catch( Exception e ) {
            System.err.println("Karte " + pFile + " konnte nicht geladen werden.");
        }

        weltLeeren();
        weltAusKarteErstellen(map.toString());
    }

    /**
     * Speichert die aktuelle Welt als Karte im maps-Ordner.
     * <p>
     * Wenn <var>pFile</var> <code>null</code> oder ein leerer String ist,
     * dann wird die Karte in der Konsole ausgegeben anstatt in einer Datei.
     * @param pFile Name der Karte.
     */
    public void karteSpeichern( String pFile ) {
        if( pFile == null ) {
            pFile = "";
        }

        StringBuilder map = new StringBuilder();
        for( int i = 0; i < getHeight(); i++ ) {
            for( int j = 0; j < getWidth(); j++ ) {
                java.util.List<Actor> actors = getObjectsAt(j, i, Actor.class);
                actors.removeIf(new Predicate<Actor>() {
                    @Override
                    public boolean test( Actor actor ) {
                        return !(actor.getClass().equals(Pflanze.class) ||
                            actor.getClass().equals(Stein.class) ||
                            actor.getClass().equals(Alarm.class) ||
                            actor.getClass().equals(Feuerbot.class) ||
                            actor.getClass().equals(Feuer.class));
                    }
                });
                if( actors.size() == 0 ) {
                    map.append('.');
                } else {
                    if( actors.size() > 1 ) {
                        map.append('(');
                    }
                    for( Actor actor : actors ) {
                        if( actor.getClass().equals(Pflanze.class) ) {
                            map.append('P');
                        } else if( actor.getClass().equals(Stein.class) ) {
                            map.append('S');
                        } else if( actor.getClass().equals(Alarm.class) ) {
                            map.append('A');
                        } else if( actor.getClass().equals(Feuer.class) ) {
                            map.append('F');
                        } else if( actor.getClass().equals(Feuerbot.class) ) {
                            Feuerbot feuerbot = (Feuerbot) actor;
                            switch( feuerbot.getRotation() ) {
                                case 0:
                                    map.append('B');
                                    break;
                                case 90:
                                    map.append('v');
                                    break;
                                case 180:
                                    map.append('<');
                                    break;
                                case 270:
                                    map.append('^');
                                    break;
                            }
                        }
                    }
                    if( actors.size() > 1 ) {
                        map.append(')');
                    }
                }
            }
            map.append('\n');
        }


        String mapStr = map.toString();
        // Optimize map
        // Remove empty cells at end of lines
        mapStr = Pattern.compile("\\.*(?=(\n|$))").matcher(mapStr).replaceAll("");
        // Remove empty lines at and of map
        mapStr = Pattern.compile("\\n+$").matcher(mapStr).replaceAll("");
        // Add one empty cell to empty lines at start of map
        mapStr = Pattern.compile("\\n(?=(\n|$))").matcher(mapStr).replaceAll("\n.");
        mapStr = Pattern.compile("^\\n").matcher(mapStr).replaceAll(".\n");

        if( pFile.length() == 0 ) {
            // Replace newline with "\n" (as string) for printing
            mapStr = Pattern.compile("\\n").matcher(mapStr).replaceAll("\\\\n");
            System.out.println(mapStr);
        } else {
            // Create maps file
            if( !pFile.contains(".") ) {
                pFile += ".map";
            }
            try {
                String maps_path = Wald.class
                    .getResource("maps/").getPath();
                PrintWriter i = new PrintWriter(
                    new File(maps_path + pFile));
                i.print(mapStr);
                i.flush();
                i.close();
            } catch( Exception e ) {
                System.err.println("Karte " + pFile + " konnte nicht gespeichert werden.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Erstellt eine prozedual generierte Zufallskarte.
     * @see #zufallswelt(long)
     */
    public void zufallswelt() {
        Random r = new Random();
        zufallswelt(r.nextLong());
    }

    /**
     * Erstellt eine prozedual generierte  Pseudo-Zufallskarte
     * <p>
     * Die generierte Karte wird abhänig vom <var>seed</var>
     * generiert. Dabei wird nicht rein zufällig entschieden,
     * welche Objekte wo platziert werden, sondern es wird versucht
     * zusammenhängende Hügelketten zu erstellen.
     * <p>
     * Bei gleichen Werten für <var>seed</var> werden gleiche
     * Karten erstellt. Die Objekte sind zufällig verteilt, es
     * kann aber eine Karte reproduziert werden, wenn derselbe
     * <var>seed</var> wiederverwendet wird.
     * <p>
     * Der aktuelle <var>seed</var> wird unten rechts angezeigt.
     * @param seed Eine Zahl, die als Seed für den Zufallsgenerator genutzt wird
     */
    public void zufallswelt( long seed ) {
        StringBuilder map = new StringBuilder();

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
                for( int[] point : points ) {
                    double d2 = Math.sqrt(Math.pow(point[0] - x, 2.0) + Math.pow(point[1] - y, 2.0));
                    if( d2 < d1 ) {
                        d1 = d2;
                    }
                }
                dist[x][y] = (int) Math.round(d1);
            }
        }

        // Generate map
        boolean roverSet = false;
        char nextChar;
        for( int y = 0; y < maxY; y++ ) {
            for( int x = 0; x < maxX; x++ ) {
                if( dist[x][y] > 3 ) {
                    int rand = r.nextInt(100);
                    if( rand <= 15 ) {
                        nextChar = 's';
                    } else if( rand <= 50 ) {
                        nextChar = 'p';
                    } else if( rand <= 58 ) {
                        nextChar = 'a';
                    } else if( rand <= 65 ) {
                        nextChar = 'f';
                    } else {
                        nextChar = '.';
                    }
                    if( !roverSet && rand <= 15 ) {
                        char[] positions = new char[]{'>', '<', '^', 'v'};
                        nextChar = positions[r.nextInt(4)];
                        roverSet = true;
                    }
                } else if( dist[x][y] < 2 ) {
                    nextChar = 'p';
                } else if( dist[x][y] == 2 ) {
                    if( r.nextInt(100) <= 60 ) {
                        nextChar = 's';
                    } else {
                        nextChar = '.';
                    }
                } else {
                    nextChar = '.';
                }

                map.append(nextChar);
            }
            map.append("\n");
        }

        // Display map
        weltLeeren();
        weltAusKarteErstellen(map.toString());

        // Show seed on map
        showText(seed + "", 12, 11);

        // Mark feature points on map
        //for( int i = 0; i < points.length; i++ ) {
        //    addObject(new Marke(), points[i][0], points[i][1]);
        //}
    }

    /**
     * Erstellt eine komplett zufällige Karte
     * <p>
     * Alle Objekte werden rein zufällig platziert und
     * die Welt rundherum mit einer "Hügelwand" umrandet.
     * Daher kann die entstehende Karte unter Umständen
     * schlecht nutzbar sein.
     */
    public void zufallswelt2() {
        StringBuilder map = new StringBuilder();
        for( int y = 0; y < getHeight(); y++ ) {
            for( int x = 0; x < getWidth(); x++ ) {
                if( x == 0 || y == 0 || x == getWidth() - 1 || y == getHeight() - 1 ) {
                    map.append("s");
                } else {
                    Random r = new Random();
                    int i = r.nextInt(100);
                    if( i < 5 ) {
                        map.append("f");
                    } else if( i < 10 ) {
                        map.append("a");
                    } else if( i < 45 ) {
                        map.append("s");
                    } else if( i < 80 ) {
                        map.append("p");
                    } else {
                        map.append(".");
                    }
                }
            }
            map.append("\n");
        }
        weltLeeren();
        weltAusKarteErstellen(map.toString());
    }

    //ml*

    /**
     * Erstellt eine Welt, die einen Weg enthält
     */
    public void zufallsweg() {
        int maxX = getWidth(), maxY = getHeight();
        int halfX = maxX / 2;

        char[][] map = new char[maxX][maxY];
        for( int y = 0; y < maxY; y++ ) {
            for( int x = 0; x < maxX; x++ ) {
                map[x][y] = 's';
            }
        }

        Random r = new Random();
        // Generate random center points
        int[] p1 = new int[2], p2 = new int[2];
        // left half
        p1[0] = r.nextInt(halfX - 6) + 3;
        p1[1] = r.nextInt(maxY - 8) + 4;
        p2[0] = r.nextInt(halfX - 6) + 3 + halfX;
        p2[1] = r.nextInt(maxY - 8) + 4;

        // Generate random edge points

        // Generate path
        //int startX = p1[0]+3, startY = p1[1];

        map[p1[0]][p1[1]] = 'a';
        map[p2[0]][p2[1]] = 'a';

        while( p1[0] != p2[0] || p1[1] != p2[1] ) {
            int q = r.nextInt(2);
            if( p1[q] != p2[q] ) {
                p1[q] -= Math.signum(p1[q] - p2[q]);
                map[p1[0]][p1[1]] = '.';
            }
        }

        // print(map);

        StringBuilder m = new StringBuilder();
        for( int y = 0; y < maxY; y++ ) {
            for( int x = 0; x < maxX; x++ ) {
                m.append(map[x][y]);
            }
            m.append("\n");
        }
        // Display map
        weltLeeren();
        weltAusKarteErstellen(m.toString());
    }

    private boolean finished( int[][] arr ) {
        for( int y = 0; y < arr[0].length; y++ ) {
            for( int[] ints : arr ) {
                if( ints[y] != 2 && ints[y] != 0 ) {
                    return false;
                }
            }
        }
        return true;
    }

    private void print( int[][] arr ) {
        for( int y = 0; y < arr[0].length; y++ ) {
            for( int[] ints : arr ) {
                System.out.print(ints[y]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void print( char[][] arr ) {
        for( int y = 0; y < arr[0].length; y++ ) {
            for( char[] chars : arr ) {
                System.out.print(chars[y]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    //*ml
}
