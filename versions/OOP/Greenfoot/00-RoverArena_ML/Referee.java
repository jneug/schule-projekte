import java.security.MessageDigest;
import java.util.*;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Referee {

    // Arena Regeln
    public static final int COSTS_MOVE = 4;
    public static final int COSTS_TURN = 2;
    public static final int COSTS_SETMARK = 1;
    public static final int COSTS_DELMARK = 2;
    public static final int COSTS_ANALYZE = 4;
    public static final int COSTS_CONVERT_ENERGY = 0;
    public static final int RATE_CONVERT_ENERGY = 1;
    public static final int COSTS_CONVERT_MINERALS = 1;
    public static final int RATE_CONVERT_MINERALS = 2;

    public static final int ENERGY_START = 1000;
    public static final int ENERGY_MAX = ENERGY_START;
    public static final int ENERGY_DRAIN_MIN = 50;
    public static final int ENERGY_DRAIN_MAX = 250;

    private static final byte TIMEOUT_AFTER_TURNS = 8;

    public static final int MARKS_START = 5;
    public static final int MARKS_MAX = 8;

    public static final int WATER_START = 0;
    public static final int WATER_MAX = 5000;

    public static final boolean RANDOM_START = false;


    private static Referee ref;

    public static Referee getNewInstance() {
        ref = new Referee();
        return ref;
    }

    public static Referee getInstance() {
        if( ref == null ) {
            ref = new Referee();
        }
        return ref;
    }


    private HashMap<Rover, RoverState> rovers;

    private HashMap<Coordinate, List<Actor>> worldCache;

    private boolean gameRunning = false;

    private World world;

    private String checksum = null;

    private ActorDelegate actor;

    public Referee() {
        rovers = new HashMap<Rover, RoverState>();
        worldCache = new HashMap<Coordinate, List<Actor>>();
        actor = new ActorDelegate(this);
    }

    public void act() {
        if( checksum == null ) {
            updateWorldChecksum();
            initWorldCache();
        }

        Rover winner = checkWinCondition();
        if( !gameRunning ) {
            // Game is over
            // TODO: How to deal with draws?
            world.removeObjects(world.getObjects(Actor.class));
            worldCache.clear();

            int midX = (int) world.getWidth()/2;
            int midY = (int) world.getHeight()/2;
            if( winner != null ) {
                world.showText(winner.getName() + " ist der Gewinner!", midX, midY-4);
                world.showText("Herzlichen Glückwunsch!", midX, midY-2);
                world.addObject(winner, midX, midY);
                winner.setRotation(270);
                world.showText(winner.getMineralien() + " Mineralien gesammelt.", midX, midY+2);
            } else {
                world.showText("Es gibt keinen Gewinner!", midX, midY);
            }

            Greenfoot.stop();
            ref = null;
        } else {
            for( Rover r : rovers.keySet() ) {
                RoverState state = getState(r);
                if( !state.immobilized ) {
                    //updateState(r);
                    checkState(r);
                    getState(r).addTurn();
                }
            }

            this.checksum = getWorldChecksum();
        }
    }

    public void addRover(Rover pRover) {
        if( !rovers.containsKey(pRover) ) {
            // Rover an zufällige Startposition setzen
            if( RANDOM_START ) {
                int[] pos = new int[2];
                Huegel h;
                Gestein g;
                do {
                    pos[0] = Utils.zufallsInt(world.getWidth());
                    pos[1] = Utils.zufallsInt(world.getHeight());
                    h = getOneObjectAt(pos[0], pos[1], Huegel.class);
                    g = getOneObjectAt(pos[0], pos[1], Gestein.class);
                } while( h != null || g != null );
                pRover.setLocation(pos[0], pos[1]);
            }

            RoverState state = new RoverState();

            // Update state
            state.xPos = pRover.getX();
            state.yPos = pRover.getY();
            state.rotation = pRover.getRotation();

            this.rovers.put(pRover, state);
            System.out.println(pRover.getName()+" ist der Arena beigetreten.");

            // Set rover initial values
            pRover.setMineralien(state.minerals);
            pRover.setWasser(state.water);
            pRover.setEnergie(state.energy);
        }
    }

    public void fahre( Rover pRover ) {
        if( checkState(pRover) ) {
            RoverState state = getState(pRover);
            if( !pRover.huegelVorhanden("vorne") ) {
                if( pRover.getEnergie() >= COSTS_MOVE ) {
                    pRover.move(1);
                    pRover.setEnergie(pRover.getEnergie()-COSTS_MOVE);
                    state.didActionThisTurn = true;
                }
            }

            updateState(pRover);
            state.addAction();
        }
    }

    public void drehe( Rover pRover, String pRichtung ) {
        if( checkState(pRover) ) {
            RoverState state = getState(pRover);
            if( pRover.getEnergie() >= COSTS_TURN ) {
                if( pRichtung == "rechts" ) {
                    pRover.setRotation(pRover.getRotation() + 90);
                } else if( pRichtung == "links" ) {
                    pRover.setRotation(pRover.getRotation() - 90);
                }
                pRover.setEnergie(pRover.getEnergie() - COSTS_TURN);
                state.didActionThisTurn = true;
            }

            updateState(pRover);
            state.addAction();
        }
    }

    public void analysiereGestein( Rover pRover ) {
        if( checkState(pRover) ) {
            RoverState state = getState(pRover);
            if( pRover.getEnergie() >= COSTS_ANALYZE ) {
                if( pRover.gesteinVorhanden() ) {
                    List<Gestein> stones = getWorld().getObjectsAt(
                        pRover.getX(), pRover.getY(), Gestein.class);
                    if( stones.size() > 0 ) {
                        Gestein stone = stones.get(0);

                        int water = stone.getWassergehalt();
                        pRover.setWasser(pRover.getWasser() + water);
                        removeObject(stone);

                        pRover.setEnergie(pRover.getEnergie()-COSTS_ANALYZE);
                        state.didActionThisTurn = true;

                        updateWorldChecksum();
                    }
                }
            }

            updateState(pRover);
            state.addAction();
        }
    }

    public void setzeMarke( Rover pRover ) {
        if( checkState(pRover) ) {
            RoverState state = getState(pRover);
            if( pRover.getEnergie() >= COSTS_SETMARK ) {
                if( !pRover.markeVorhanden() && state.marks > 0 ) {
                    getWorld().addObject(new Marke(pRover), pRover.getX(), pRover.getY());
                    state.marks -= 1;

                    pRover.setEnergie(pRover.getEnergie()-COSTS_SETMARK);
                    state.didActionThisTurn = true;

                    updateWorldChecksum();
                }
            }

            updateState(pRover);
            state.addAction();
        }
    }

    public void entferneMarke( Rover pRover ) {
        if( checkState(pRover) ) {
            RoverState state = getState(pRover);
            if( state.marks < MARKS_MAX ) {
                if( pRover.getEnergie() >= COSTS_DELMARK ) {
                    if( pRover.markeVorhanden() ) {
                        List<Marke> marks = getWorld().getObjectsAt(
                            pRover.getX(), pRover.getY(), Marke.class);
                        if( marks.size() > 0 ) {
                            Marke mark = marks.get(0);
                            removeObject(mark);
                            state.marks += 1;

                            pRover.setEnergie(pRover.getEnergie() - COSTS_DELMARK);
                            state.didActionThisTurn = true;

                            updateWorldChecksum();
                        }
                    }
                }
            }

            updateState(pRover);
            state.addAction();
        }
    }

    public void konvertiereWasser( Rover pRover, int pMenge, String pProdukt ) {
        if( checkState(pRover) ) {
            RoverState state = getState(pRover);
            if( pProdukt.equals("energie") ) {
                if( pRover.getWasser() < pMenge ) {
                    pMenge = pRover.getWasser();
                }
                if( pRover.getEnergie()+(pMenge*RATE_CONVERT_ENERGY) > ENERGY_MAX ) {
                    pMenge = (int) (ENERGY_MAX-pRover.getEnergie())/RATE_CONVERT_ENERGY;
                }
                if( pMenge > 0 ) {
                    pRover.setEnergie(pRover.getEnergie() + (pMenge * RATE_CONVERT_ENERGY));
                    pRover.setWasser(pRover.getWasser() - pMenge);
                    state.didActionThisTurn = true;
                }
            } else if( pProdukt.equals("mineralien") ) {
                if( pRover.getEnergie() < (pMenge*COSTS_CONVERT_MINERALS) ) {
                    pMenge = (int) (pRover.getEnergie()/COSTS_CONVERT_MINERALS);
                }
                if( pRover.getWasser() < pMenge ) {
                    pMenge = pRover.getWasser();
                }
                if( pMenge > 0 ) {
                    pRover.setMineralien(pRover.getMineralien() + (pMenge*RATE_CONVERT_MINERALS));
                    pRover.setEnergie(pRover.getEnergie()-(pMenge*COSTS_CONVERT_MINERALS));
                    pRover.setWasser(pRover.getWasser()-pMenge);
                    state.didActionThisTurn = true;
                }
            }

            updateState(pRover);
            state.addAction();
        }
    }

    public void entzieheEnergie( Rover pRover ) {
        if( checkState(pRover) ) {
            RoverState state = getState(pRover);
            if( pRover.roverVorhanden("vorne") ) {
                int[] vorne = getFieldAt(pRover, "vorne");
                Rover otherRover = getOneObjectAt(vorne[0], vorne[1], Rover.class);
                if( otherRover != null ) {
                    int drain = Math.min(Utils.zufallsInt(ENERGY_DRAIN_MIN, ENERGY_DRAIN_MAX), otherRover.getEnergie());
                    if( drain > 0 ) {
                        otherRover.setEnergie(Math.max(0,otherRover.getEnergie()-drain));
                        pRover.setEnergie(Math.min(ENERGY_MAX,pRover.getEnergie()+drain));
                        state.didActionThisTurn = true;
                    }

                    updateState(otherRover);
                    updateState(pRover);
                    state.addAction();
                }
            }
        }
    }

    private <T> T getOneObjectAt( int xPos, int yPos, Class<T> clazz ) {
        List<T> objects = getWorld().getObjectsAt(xPos, yPos, clazz);
        if( objects.size() > 0 ) {
            return objects.get(0);
        } else {
            return null;
        }
    }

    private <T> T getOneObjectAtOffset( Rover pRover, int xOffset, int yOffset, Class<T> clazz ) {
        List<T> objects = getWorld().getObjectsAt(pRover.getX()+xOffset, pRover.getY()+yOffset, clazz);
        if( objects.size() > 0 ) {
            return objects.get(0);
        } else {
            return null;
        }
    }

    private <T> T getOneObjectAtDirection( Rover pRover, String direction, Class<T> clazz ) {
        int[] coords = getFieldAt(pRover, direction);
        return getOneObjectAt(coords[0], coords[1], clazz);
    }

    private int[] getFieldAt( Rover pRover, String direction ) {
        int[] offset = getOffsetForDirection(pRover, direction);
        return new int[]{pRover.getX()+offset[0],pRover.getY()+offset[1]};
    }

    private int[] getOffsetForDirection( Rover pRover, String direction ) {
        int rot = pRover.getRotation(), x = pRover.getX(), y = pRover.getY();

        if( direction == "vorne" && rot == 0 || direction == "rechts" && rot == 270
            || direction == "links" && rot == 90 ) {
            return new int[]{1,0};
        }

        if( direction == "vorne" && rot == 180 || direction == "rechts" && rot == 90
            || direction == "links" && rot == 270 ) {
            return new int[]{-1,0};
        }

        if( direction == "vorne" && rot == 90 || direction == "rechts" && rot == 0
            || direction == "links" && rot == 180 ) {
            return new int[]{0,1};
        }

        if( direction == "vorne" && rot == 270 || direction == "rechts" && rot == 180
            || direction == "links" && rot == 0 ) {
            return new int[]{0,-1};
        }

        return new int[]{1,0};
    }

    private void updateState( Rover pRover ) {
        RoverState state = getState(pRover);
        if( state != null ) {
            if( pRover.getWorld() == null ) {
                getWorld().addObject(pRover, state.xPos, state.yPos);
            }

            state.xPos = pRover.getX();
            state.yPos = pRover.getY();
            state.rotation = pRover.getRotation();

            state.water = pRover.getWasser();
            state.energy = pRover.getEnergie();
            state.minerals = pRover.getMineralien();
        } else {
            world.removeObject(pRover);
        }
    }

    private boolean checkState( Rover pRover ) {
        if( !gameRunning ) {
            // game over, dont process any more actions for rovers
            // (will produce NPE otherwise)
            return false;
        }

        verifyWorldChecksum();

        RoverState state = getState(pRover);
        if ( state != null ) {
            // check last known position
            try {
                if( pRover.getX() != state.xPos || pRover.getY() != state.yPos
                    || pRover.getRotation() != state.rotation ) {
                    pRover.setLocation(state.xPos, state.yPos);
                    pRover.setRotation(state.rotation);
                    state.penalties += 1;
                    state.actionsThisTurn += 1;
                    System.err.println(pRover.getName() + " wurde bestraft für den Versuch, seine Position unerlaubt zu ändern.");
                }
            } catch( IllegalStateException ex ) {
                // Rover not in world, remove from game
                rovers.remove(pRover);
                System.err.println(pRover.getName() + " wurde aus dem Spiel entfernt!");
                return false;
            }

            if( pRover.getWasser() != state.water || pRover.getEnergie() != state.energy
               || pRover.getMineralien() != state.minerals ) {
                pRover.setWasser(state.water);
                pRover.setEnergie(state.energy);
                pRover.setMineralien(state.minerals);
                state.penalties += 1;
                state.actionsThisTurn += 1;
                System.err.println(pRover.getName()+" wurde bestraft für den Versuch, seine Resourcen unerlaubt zu ändern.");
            }

            if( !state.immobilized && state.actionsThisTurn < 2 ) {
                return true;
            }
        } else {
            getWorld().removeObject(pRover);
        }
        return false;
    }

    private RoverState getState( Rover pRover ) {
        if( rovers.containsKey(pRover) ) {
            return rovers.get(pRover);
        } else {
            return null;
        }
    }

    private Rover checkWinCondition() {
        Rover winner = null;
        boolean allImmobile = true;
        for( Rover r: rovers.keySet() ) {
            // checkState(r);
            RoverState state = getState(r);

            if( state.immobilized ) {
                continue;
            }

            if( (r.getEnergie() < COSTS_MOVE && r.getWasser() <= 0)
                    || state.turnsWithoutActions >= TIMEOUT_AFTER_TURNS ) {
                state.immobilized = true;
                System.out.printf("%s ist ausgeschieden. (%d Mineralien gesammelt.)\n", r.getName(), r.getMineralien());
            }
            if( !state.immobilized ) {
                allImmobile = false;
            }
        }
        if( allImmobile ) {
            gameRunning = false;

            int maxMinerals = 0;
            for( Rover r: rovers.keySet() ) {
                checkState(r);
                if( getState(r) != null && r.getMineralien() > maxMinerals ) {
                    maxMinerals = r.getMineralien();
                    winner = r;
                }
            }
        }
        return winner;
    }

    protected void addedToWorld( World world ) {
        this.world = world;
        //updateWorldChecksum();
        gameRunning = true;
    }

    private World getWorld() {
        return this.world;
    }

    private String getWorldChecksum() {
        List<Actor> actors = world.getObjects(Actor.class);
        actors.sort(new Comparator<Actor>() {
            @Override
            public int compare( Actor o1, Actor o2 ) {
                return o1.hashCode() - o2.hashCode();
            }
        });
        String data = "", checksum = "";
        for( Actor a: actors ) {
            if( isCachableActor(a) ) {
                data += a.toString() + "\n";
            }
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            checksum = sb.toString();
        } catch(Exception ex) {
        }

        return checksum;
    }

    public boolean verifyWorldChecksum() {
        String newChecksum = getWorldChecksum();
        if( !newChecksum.equals(checksum) ) {
            // Welt überprüfen
            System.err.println("Die Welt wurde unerlaubt verändert. Der letzte Zustand wurde wiederhergestellt.");
            verifyWorldCache();
            return false;
        }
        return true;
    }

    public void updateWorldChecksum() {
        checksum = getWorldChecksum();
    }

    private void initWorldCache() {
        List<Actor> actors = world.getObjects(Actor.class);
        for( Actor a: actors ) {
            if( isCachableActor(a) ) {
                Coordinate coord = new Coordinate(a.getX(), a.getY());
                if( !worldCache.containsKey(coord) ) {
                    worldCache.put(coord, new ArrayList<Actor>(3));
                }
                worldCache.get(coord).add(a);
            }
        }
    }

    private void verifyWorldCache() {
        for( int x = 0; x < world.getWidth(); x++ ) {
            for( int y = 0; y < world.getHeight(); y++ ) {
                Coordinate coord = new Coordinate(x,y);
                List<Actor> worldActors = world.getObjectsAt(x, y, Actor.class);
                List<Actor> cacheActors = worldCache.getOrDefault(coord, Collections.EMPTY_LIST);


                for( Actor a: worldActors ) {
                    if( isCachableActor(a) ) {
                        if( !cacheActors.contains(a) ) {
                            world.removeObject(a);
                        }
                    }
                }
                for( Actor a: cacheActors ) {
                    if( !worldActors.contains(a) ) {
                        world.addObject(a, x, y);
                    }
                }
            }
        }

    }

    private static final Class<?>[] cachableClasses = new Class[]{
        Huegel.class, Gestein.class, Marke.class
    };

    private boolean isCachableActor( Actor pActor ) {
        for( Class<?> clazz: cachableClasses ) {
            if( clazz.isAssignableFrom(pActor.getClass()) ) {
                return true;
            }
        }
        return false;
    }

    private void removeObject( Actor pActor ) {
        Coordinate coord = new Coordinate(pActor.getX(), pActor.getY());
        if( worldCache.containsKey(coord) ) {
            worldCache.get(coord).remove(pActor);
        }
        world.removeObject(pActor);
    }

    public Actor getActor() {
        return this.actor;
    }

    class RoverState {
        public int xPos, yPos, rotation;
        public int water = WATER_START, energy = ENERGY_START, minerals = 0;
        public int marks = MARKS_START;

        public long turn = 0, actionsTotal = 0;

        // 0, 1 or 2
        public byte actionsThisTurn = 0, turnsWithoutActions = 0;

        public int penalties = 0;

        public boolean immobilized = false, didActionThisTurn = false;

        public void addTurn() {
            turn += 1;
            if( !didActionThisTurn ) {
                turnsWithoutActions += 1;
            } else {
                turnsWithoutActions = 0;
                didActionThisTurn = false;
            }
            actionsThisTurn = 0;
        }

        public void addAction() {
            actionsTotal += 1;
            actionsThisTurn += 1;
        }
    }

    class ActorDelegate extends Actor {
        Referee ref;
        public ActorDelegate( Referee ref ) {
            this.ref = ref;
        }

        public void act() {
            this.ref.act();
        }

        public void addedToWorld(World world) {
            setImage("images/boden.png");
            this.ref.addedToWorld(world);
        }
    }

    class Coordinate {
        public int x, y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals( Object o ) {
            try {
                Coordinate a = (Coordinate)o;
                return a.x == x && a.y == y;
            } catch( Exception ex ){
                return false;
            }
        }
        @Override
        public int hashCode() {
            return x*1000 + y;
        }
    }

}
