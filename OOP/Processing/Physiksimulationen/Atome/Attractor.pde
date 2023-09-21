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
public class Attractor {

  /**
   * Gravitationskonstante
   * <p>
   * Beeinflusst die Stärke der Anziehungskraft der {@link Attractor}en.
   */
  public static final float G = 1.0;

  /**
   * Größe der Objekte in der Simulation.
   */
  public static final int SIZE = 40;
  
  /**
   * Masse des Objektes.
   */
  protected float mass = 4.0;

  /**
   * Momentane position des Objektes.
   */
  private PVector position;
  
  private float size = SIZE;

  /**
   * Erstellt einen <code>Attractor</code> an der angegebenen Position mit der angegebenen
   * Masse.
   *
   * @param pX    x-Koordinate des Objektes.
   * @param pY    y-Koordinate des Objektes.
   * @param pMass Masse des Objektes.
   */
  public Attractor( float pX, float pY, float pMass ) {
    position = new PVector(pX, pY);
    mass = pMass;
  }

  /**
   * Wendet die Anziehungskraft des <code>Attractor</code> auf einen
   * <code>Mover</code> an.
   *
   * @param pMover Das Objekt, das angezogen wird.
   */
  public void attract( Mover pMover ) {
    PVector force = this.position.copy();
    force.sub(pMover.getPosition());
    
    float dist = constrain(force.magSq(), 0.01, 4 * G); 
    float v = (G * mass * pMover.getMass()) / dist;
    force.setMag(v);
    pMover.applyForce(force);
  }

  public void draw() {
    noStroke();
    fill(120, 205, 237);
    circle(position.x, position.y, size);
  }

}
