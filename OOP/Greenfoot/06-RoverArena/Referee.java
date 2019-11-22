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
                getWorld().showText("Herzlichen Glückwunsch!", 7, 2);
            } else {
                getWorld().showText("Es gibt keinen Gewinner!", 7, 1);
            }
            Greenfoot.delay(4);
            Greenfoot.stop();
        } else {
            for( Rover r : rovers.keySet() ) {
                RoverState state = getState(r);
                if( !state.imobilized ) {
                    updateState(r);
                    getState(r).addTurn();
                }
            }
        }
    }

    public void fahre( Rover pRover ) {
        if( checkState(pRover) ) {
            if( !pRover.huegelVorhanden("vorne") ) {
                if( pRover.getEnergie() >= COSTS_MOVE ) {
                    pRover.move(1);
                    pRover.setEnergie(pRover.getEnergie()-COSTS_MOVE);
                }
            }

            updateState(pRover);
            getState(pRover).addAction();
        }
    }

    public void drehe( Rover pRover, String pRichtung ) {
        if( checkState(pRover) ) {
            if( pRover.getEnergie() >= COSTS_TURN ) {
                if( pRichtung == "rechts" ) {
                    pRover.setRotation(pRover.getRotation() + 90);
                } else if( pRichtung == "links" ) {
                    pRover.setRotation(pRover.getRotation() - 90);
                }
                pRover.setEnergie(pRover.getEnergie() - COSTS_TURN);
            }

            updateState(pRover);
            getState(pRover).addAction();
        }
    }

    public void analysiereGestein( Rover pRover ) {
        if( checkState(pRover) ) {
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
                    }
                }
            }

            updateState(pRover);
            getState(pRover).addAction();
        }
    }

    public void setzeMarke( Rover pRover ) {
        if( checkState(pRover) ) {
            if( pRover.getEnergie() >= COSTS_SETMARK ) {
                RoverState state = getState(pRover);
                if( !pRover.markeVorhanden() && state.marks > 0 ) {
                    getWorld().addObject(new Marke(pRover), pRover.getX(), pRover.getY());
                    state.marks -= 1;

                    pRover.setEnergie(pRover.getEnergie()-COSTS_SETMARK);
                }
            }
            updateState(pRover);
            getState(pRover).addAction();
        }
    }

    public void entferneMarke( Rover pRover ) {
        if( checkState(pRover) ) {
            if( pRover.getEnergie() >= COSTS_DELMARK ) {
                if( pRover.markeVorhanden() ) {
                    RoverState state = getState(pRover);
                    List<Marke> marks = getWorld().getObjectsAt(
                        pRover.getX(), pRover.getY(), Marke.class);
                    if( marks.size() > 0 ) {
                        Marke mark = marks.get(0);
                        getWorld().removeObject(mark);
                        state.marks += 1;

                        pRover.setEnergie(pRover.getEnergie()-COSTS_DELMARK);
                    }
                }
            }
            updateState(pRover);
            getState(pRover).addAction();
        }
    }

    public void konvertiereWasser( Rover pRover, int pMenge, String pProdukt ) {
        if( checkState(pRover) ) {
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
            }

            updateState(pRover);
            getState(pRover).addAction();
        }
    }

    public void energieAbziehen( Rover pRover ) {
        if( checkState(pRover) ) {
            if( pRover.roverVorhanden("vorne") ) {
                int[] vorne = getFieldAt(pRover, "vorne");
                Rover otherRover = getOneObjectAt(vorne[0], vorne[1], Rover.class);
                if( otherRover != null ) {
                    int drain = Math.min(ENERGY_DRAIN, otherRover.getEnergie());
                    otherRover.setEnergie(otherRover.getEnergie()-drain);
                    pRover.setEnergie(pRover.getEnergie()+drain);

                    updateState(otherRover);
                    updateState(pRover);
                    getState(pRover).addAction();
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

    private int[] getFieldAt( Rover pRover, String richtung ) {
        int rot = pRover.getRotation(), x = pRover.getX(), y = pRover.getY();

        if( richtung == "vorne" && rot == 0 || richtung == "rechts" && rot == 270
            || richtung == "links" && rot == 90 ) {
            return new int[]{x+1,y};
        }

        if( richtung == "vorne" && rot == 180 || richtung == "rechts" && rot == 90
            || richtung == "links" && rot == 270 ) {
            return new int[]{x-1,y};
        }

        if( richtung == "vorne" && rot == 90 || richtung == "rechts" && rot == 0
            || richtung == "links" && rot == 180 ) {
            return new int[]{x,y+1};
        }

        if( richtung == "vorne" && rot == 270 || richtung == "rechts" && rot == 180
            || richtung == "links" && rot == 0 ) {
            return new int[]{x,y-1};
        }

        return new int[]{x+1,y};
    }

    private void updateState( Rover pRover ) {
        RoverState state = getState(pRover);
        if( state != null ) {
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
                System.err.println(pRover.getName()+" wurde bestraft für den Versuch, seine Position unerlaubt zu ändern.");
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

            if( !state.imobilized && state.actionsThisTurn < 2 ) {
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

            if( r.getEnergie() < COSTS_MOVE && r.getWasser() <= 0 ) {
                state.imobilized = true;
            }
            if( !state.imobilized ) {
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
        public byte actionsThisTurn = 0;

        public int penalties = 0;

        public boolean imobilized = false;

        public void addTurn() {
            turn += 1;
            actionsThisTurn = 0;
        }

        public void addAction() {
            actionsTotal += 1;
            actionsThisTurn += 1;
        }
    }

    private class ActorDelegate extends Actor {
        Referee ref;
        public ActorDelegate( Referee ref ) {
            this.ref = ref;
        }

        public void act() {
            this.ref.act();
        }

        public void addedToWorld(World world) {
            this.ref.addedToWorld(world);
        }
    }

}
