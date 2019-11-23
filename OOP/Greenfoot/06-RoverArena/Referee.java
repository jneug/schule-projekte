import java.util.HashMap;
import java.util.List;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Referee {

    // Arena Regeln
    public static final int COSTS_MOVE = 4;
    public static final int COSTS_TURN = 2;
    public static final int COSTS_SETMARK = 1;
    public static final int COSTS_DELMARK = 1;
    public static final int COSTS_ANALYZE = 4;
    public static final int COSTS_CONVERT_ENERGY = 0;
    public static final int RATE_CONVERT_ENERGY = 1;
    public static final int COSTS_CONVERT_MINERALS = 1;
    public static final int RATE_CONVERT_MINERALS = 2;

    public static final int ENERGY_DRAIN = 10;
    private static final byte TIMEOUT_AFTER_TURNS = 8;


    private static Referee ref;

    public static Referee getInstance() {
        if( ref == null ) {
            ref = new Referee();
        }
        return ref;
    }


    private HashMap<Rover, RoverState> rovers;

    private boolean gameRunning = false;

    private World world;

    private ActorDelegate actor;

    public Referee() {
        rovers = new HashMap<Rover, RoverState>();
        actor = new ActorDelegate(this);
    }

    public void addRover(Rover pRover) {
        if( !rovers.containsKey(pRover) ) {
            // Rover an Startposition setzen ?

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

    public void act() {
        Rover winner = checkWinCondition();
        if( !gameRunning ) {
            // Game is over
            // TODO: How to deal with draws?
            for( Rover r : rovers.keySet() ) {
                if( !r.equals(winner) ) {
                    getWorld().removeObject(r);
                }
            }
            if( winner != null ) {
                getWorld().showText(winner.getName() + " ist der Gewinner!", 7, 1);
                getWorld().showText("Herzlichen Gl�ckwunsch!", 7, 2);
            } else {
                getWorld().showText("Es gibt keinen Gewinner!", 7, 1);
            }
            Greenfoot.delay(4);
            Greenfoot.stop();
        } else {
            for( Rover r : rovers.keySet() ) {
                RoverState state = getState(r);
                if( !state.immobilized ) {
                    //updateState(r);
                    checkState(r);
                    getState(r).addTurn();
                }
            }
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
                        getWorld().removeObject(stone);

                        pRover.setEnergie(pRover.getEnergie()-COSTS_ANALYZE);
                        state.didActionThisTurn = true;
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
                            getWorld().removeObject(mark);
                            state.marks += 1;

                            pRover.setEnergie(pRover.getEnergie() - COSTS_DELMARK);
                            state.didActionThisTurn = true;
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
                pRover.setEnergie(pRover.getEnergie() + (pMenge*RATE_CONVERT_ENERGY));
                pRover.setWasser(pRover.getWasser()-pMenge);
            } else if( pProdukt.equals("mineralien") ) {
                if( pRover.getEnergie() < (pMenge*COSTS_CONVERT_MINERALS) ) {
                    pMenge = (int) (pRover.getEnergie()/COSTS_CONVERT_MINERALS);
                }
                if( pRover.getWasser() < pMenge ) {
                    pMenge = pRover.getWasser();
                }
                pRover.setMineralien(pRover.getMineralien() + (pMenge*RATE_CONVERT_MINERALS));
                pRover.setEnergie(pRover.getEnergie()-(pMenge*COSTS_CONVERT_MINERALS));
                pRover.setWasser(pRover.getWasser()-pMenge);
                state.didActionThisTurn = true;
            }

            updateState(pRover);
            state.addAction();
        }
    }

    public void energieAbziehen( Rover pRover ) {
        if( checkState(pRover) ) {
            RoverState state = getState(pRover);
            if( pRover.roverVorhanden("vorne") ) {
                int[] vorne = getFieldAt(pRover, "vorne");
                Rover otherRover = getOneObjectAt(vorne[0], vorne[1], Rover.class);
                if( otherRover != null ) {
                    int drain = Math.min(ENERGY_DRAIN, otherRover.getEnergie());
                    otherRover.setEnergie(otherRover.getEnergie()-drain);
                    pRover.setEnergie(pRover.getEnergie()+drain);

                    updateState(otherRover);
                    updateState(pRover);
                    state.didActionThisTurn = true;
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
            getWorld().removeObject(pRover);
        }
    }

    private boolean checkState( Rover pRover ) {
        RoverState state = getState(pRover);
        if ( state != null ) {
            // check last known position
            if( pRover.getX() != state.xPos || pRover.getY() != state.yPos
                    || pRover.getRotation() != state.rotation ) {
                pRover.setLocation(state.xPos, state.yPos);
                pRover.setRotation(state.rotation);
                state.penalties += 1;
                state.actionsThisTurn += 1;
                System.err.println(pRover.getName()+" wurde bestraft f�r den Versuch, seine Position unerlaubt zu �ndern.");
            }

            if( pRover.getWasser() != state.water || pRover.getEnergie() != state.energy
               || pRover.getMineralien() != state.minerals ) {
                pRover.setWasser(state.water);
                pRover.setEnergie(state.energy);
                pRover.setMineralien(state.minerals);
                state.penalties += 1;
                state.actionsThisTurn += 1;
                System.err.println(pRover.getName()+" wurde bestraft f�r den Versuch, seine Resourcen unerlaubt zu �ndern.");
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
                if( r.getMineralien() > maxMinerals ) {
                    maxMinerals = r.getMineralien();
                    winner = r;
                }
            }
        }
        return winner;
    }

    protected void addedToWorld( World world ) {
        this.world = world;
        gameRunning = true;
    }

    private World getWorld() {
        return this.world;
    }

    public Actor getActor() {
        return this.actor;
    }

    class RoverState {
        public int xPos, yPos, rotation;
        public int water = 0, energy = 1000, minerals = 0;
        public int marks = 5;

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

}
