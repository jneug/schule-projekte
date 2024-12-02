package view;

import schule.ngb.zm.Constants;
import schule.ngb.zm.Vector;
import schule.ngb.zm.Zeichenmaschine;
import schule.ngb.zm.anim.*;
import schule.ngb.zm.layers.ShapesLayer;
import schule.ngb.zm.shapes.Shape;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.DoubleUnaryOperator;

/**
 * Hauptklasse des Projekts
 */
public class Hochregallager extends Zeichenmaschine {

    private static final int ANIMATION_RUNTIME = 800;
    private static final DoubleUnaryOperator ANIMATION_EASING = DEFAULT_EASING;

    private ShapesLayer boxLayer, craneLayer;

    private Lager lager;

    private Kran kran;

    private Kiste kiste;

    private int fach = 1, ebene = 1;

    private Animation<?> currentAnimation;

    private java.util.Queue<Kran.Kommando> commands = new LinkedList<>();

    public Hochregallager() {
        super(Lager.WIDTH, Lager.HEIGHT, "Hochregallager", false);
    }

    @Override
    public void settings() {
        lager = new Lager();
        Vector fach0 = lager.getKoordinatenVonFach(1, 1, SOUTH);
        kran = new Kran(fach0.x, fach0.y);
        this.addLayer(lager);

        boxLayer = new ShapesLayer();
        craneLayer = new ShapesLayer();
        this.addLayer(boxLayer);
        this.addLayer(craneLayer);

        craneLayer.add(kran);
    }

    @Override
    public void setup() {
    }

    @Override
    public void draw() {
    }

    public void update( double delta ) {
        if( currentAnimation == null ) {
            Kran.Kommando cmd = commands.poll();
            if( cmd != null ) {
                switch( cmd.typ ) {
                    case Kran.Kommando.BACK:
                        fach = 1;
                        ebene = 1;
                        Vector fach0 = lager.getKoordinatenVonFach(ebene, fach, SOUTH);
                        currentAnimation = new AnimationGroup<>(
                            Arrays.asList(
                                new SizeAnimation(0.0, ANIMATION_RUNTIME, ANIMATION_EASING),
                                new MoveAnimation(kran, fach0.x, kran.getY(), ANIMATION_RUNTIME, ANIMATION_EASING)
                            )
                        );
                        break;

                    case Kran.Kommando.LINKS:
                        if( fach > 1 ) {
                            fach -= 1;
                            Vector ziel = lager.getKoordinatenVonFach(ebene, fach, SOUTH);
                            currentAnimation = new MoveAnimation(kran, ziel.x, kran.getY(), ANIMATION_RUNTIME, ANIMATION_EASING);
                        } else {
                            System.err.println("Kran kann sich nicht weiter links bewegen!");
                        }
                        break;

                    case Kran.Kommando.RECHTS:
                        if( fach < Lager.FAECHER ) {
                            fach += 1;
                            Vector ziel = lager.getKoordinatenVonFach(ebene, fach, SOUTH);
                            currentAnimation = new MoveAnimation(kran, ziel.x, kran.getY(), ANIMATION_RUNTIME, ANIMATION_EASING);
                        } else {
                            System.err.println("Kran kann sich nicht weiter rechts ewegen!");
                        }
                        break;

                    case Kran.Kommando.HOCH:
                        if( ebene < Lager.EBENEN ) {
                            ebene += 1;
                            currentAnimation = new SizeAnimation(
                                (ebene - 1) * Lager.LAGERPLATZ_HOEHE,
                                ANIMATION_RUNTIME, ANIMATION_EASING
                            );
                        } else {
                            System.err.println("Kran kann sich nicht hoch bewegen!");
                        }
                        break;

                    case Kran.Kommando.RUNTER:
                        if( ebene > 1 ) {
                            ebene -= 1;
                            currentAnimation = new SizeAnimation(
                                (ebene - 1) * Lager.LAGERPLATZ_HOEHE,
                                ANIMATION_RUNTIME, ANIMATION_EASING
                            );
                        } else {
                            System.err.println("Kran kann sich nicht tiefer als Ebene A bewegen!");
                        }
                        break;

                    case Kran.Kommando.AUFLADEN:
                        if( kiste == null ) {
                            System.err.println("Box kann nicht geladen werden, keine Box vorhanden!");
                        } else if( fach != 1 || ebene != 1 ) {
                            System.err.println("Box kann nicht geladen werden, Kran befindet sich nicht bei Fach #A1!");
                        } else {
                            kran.setKiste(cmd.kiste);
                            currentAnimation = null;
                        }
                        return;

                    case Kran.Kommando.ABLADEN:
                        if( this.kiste != kran.getKiste() ) {
                            System.err.println("Box kann nicht abgeladen werden, Kran hat keine Box geladen!");
                        } else {
                            kran.setKiste(null);
                            kiste = null;
                            currentAnimation = null;
                        }
                        return;
                }
                Animations.play(currentAnimation);
            }
        } else if( !currentAnimation.isActive() ) {
            currentAnimation = null;
        }
    }

    public void newBox( String pCode ) {
        if( kiste == null ) {
            Vector fach0 = lager.getKoordinatenVonFach(1, 1, CENTER);
            kiste = new Kiste(pCode, fach0.x, fach0.y);
            boxLayer.add(kiste);
        } else {
            System.err.println("Kann keine neue Box erstellen, da schon eine Box vorhanden ist, die noch nicht geliefert wurde.");
        }
    }

    public void moveCraneRight() {
        commands.add(new Kran.Kommando(Kran.Kommando.RECHTS));
    }

    public void moveCraneLeft() {
        commands.add(new Kran.Kommando(Kran.Kommando.LINKS));
    }

    public void moveCraneUp() {
        commands.add(new Kran.Kommando(Kran.Kommando.HOCH));
    }

    public void moveCraneDown() {
        commands.add(new Kran.Kommando(Kran.Kommando.RUNTER));
    }

    public void loadBox() {
        commands.add(new Kran.Kommando(Kran.Kommando.AUFLADEN, this.kiste));
    }

    public void unloadBox() {
        commands.add(new Kran.Kommando(Kran.Kommando.ABLADEN, null));
    }

    public void moveCraneToStart() {
        commands.add(new Kran.Kommando(Kran.Kommando.BACK));
    }

    private final class SizeAnimation extends Animation<Shape> {

        double initial, target;

        public SizeAnimation( double target, int runtime, DoubleUnaryOperator easing ) {
            super(runtime, easing);
            this.target = target;
        }

        @Override
        public void initialize() {
            initial = kran.getSize();
        }

        @Override
        public void animate( double v ) {
            kran.setSize(Constants.interpolate(initial, target, v));
        }

        @Override
        public Shape getAnimationTarget() {
            return kran;
        }

    }

}
