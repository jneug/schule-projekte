/**
 * Gravitationsquelle in der Simulation.
 * <p>
 * Eine Gravitationsquelle zieht mit einer Anziehungskraft proportional zu
 * seiner Masse alle {@link Mover}-Objekte an. Dabei kommt die Newtonsche
 * Gravitationsformel zur Anwendung.
 * <p>
 * Ein <code>Attractor</code> ist auch ein {@link Mover} und wird von anderen
 * Gravitationsquellen beeinflusst. Dieses Verhalten kann durch Setzen von
 * <code>setMovable(false)</code> abgeschaltet werden.
 */
public class Attractor extends Mover {

  /**
   * Gravitationskonstante
   * <p>
   * Beeinflusst die Stärke der Anziehungskraft der {@link Attractor}en.
   */
  public static final float G = 10.0;

  /**
   * Erstellt einen <code>Attractor</code> an der angegebenen Position mit der angegebenen
   * Masse.
   *
   * @param pX    x-Koordinate des Objektes.
   * @param pY    y-Koordinate des Objektes.
   * @param pMass Masse des Objektes.
   */
  public Attractor( float pX, float pY, float pMass ) {
    super(pX, pY, pMass);
    size = sqrt(pMass) * SIZE_FACTOR * 2.0;
  }

  /**
   * Wendet die Anziehungskraft des <code>Attractor</code> auf einen
   * <code>Mover</code> an.
   *
   * @param pMover Das Objekt, das angezogen wird.
   */
  public void attract( Mover pMover ) {
    PVector force = PVector.sub(this.position.copy(), pMover.getPosition());
    
    float distSq = constrain(force.magSq(), 25, 2500); 
    float strength = (G * mass * pMover.getMass()) / distSq;
    
    force.setMag(strength);
    pMover.applyForce(force);
  }

  public void draw() {
    noStroke();
    fill(120, 205, 237);
    circle(position.x, position.y, size);
  }

}
