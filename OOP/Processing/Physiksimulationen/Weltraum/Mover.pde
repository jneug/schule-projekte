public class Mover {

  /**
   * Größe der Objekte in der Simulation.
   */
  public static final float SIZE_FACTOR = 2.0;
  
  /**
   * Masse des Objektes.
   */
  protected float mass = 1.0;

  /**
   * Momentane position des Objektes.
   */
  protected PVector position;

  /**
   * Momentane Geschwindigkeit des Objektes.
   */
  protected PVector velocity;

  /**
   * Momentane Beschleunigung des Objektes.
   */
  protected PVector acceleration = new PVector();
  
  protected float size;

  /**
   * Erstellt einen Mover an der angegebenen Position mit der momentanen
   * Geschwindigkeit <code>(0, 0)</code>.
   *
   * @param pX x-Position des Objektes.
   * @param pY y-Position des Objektes.
   */
  public Mover( float pX, float pY ) {
    this(pX, pY, new PVector());
  }


  /**
   * Erstellt einen <code>Attractor</code> an der angegebenen Position mit der angegebenen
   * Masse.
   *
   * @param pX    x-Koordinate des Objektes.
   * @param pY    y-Koordinate des Objektes.
   * @param pMass Masse des Objektes.
   */
  public Mover( float pX, float pY, float pMass ) {
    this(pX, pY, pMass, new PVector());
  }

  /**
   * Erstellt einen Mover an der angegebenen Position mit der angegebenen
   * Initialgeschwindigkeit.
   *
   * @param pX        x-Position des Objektes.
   * @param pY        y-Position des Objektes.
   * @param pVelocity Momentane Geschwindigkeit des Objektes.
   */
  public Mover( float pX, float pY, PVector pVelocity ) {
    position = new PVector(pX, pY);
    velocity = pVelocity.copy();
    size = sqrt(mass) * SIZE_FACTOR;
  }

  /**
   * Erstellt einen <code>Attractor</code> an der angegebenen Position
   *
   * @param pX        x-Koordinate des Objektes.
   * @param pY        y-Koordinate des Objektes.
   * @param pMass     Masse des Objektes.
   * @param pVelocity Initialgeschwindigkeit des Objektes.
   */
  public Mover( float pX, float pY, float pMass, PVector pVelocity ) {
    position = new PVector(pX, pY);
    mass = pMass;
    velocity = pVelocity.copy();
    size = sqrt(mass) * SIZE_FACTOR;
  }

  /**
   * Gibt die Masse des Objektes zurück.
   *
   * @return Die Masse des Objektes.
   */
  public float getMass() {
    return mass;
  }
  
  public PVector getPosition() {
    return position;
  }

  /**
   * Gibt die momentane Geschwindigkeit zurück.
   * @return Die momentane Geschwindigkeit als Vektor.
   */
  public PVector getVelocity() {
    return velocity;
  }

  /**
   * Setzt die momentane Geschwindigkeit des Objektes.
   *
   * @param pDx Momentane Geschwindigkeit in x-Richtung.
   * @param pDy Momentane Geschwindigkeit in y-Richtung.
   */
  public void setVelocity( float pDx, float pDy ) {
    velocity.set(pDx, pDy);
  }

  /**
   * Addiert eine Kraft zur momentanen Beschleunigung des Objektes. Die
   * Beschleunigung wird einmal pro Update auf die Geschwindigkeit angewandt
   * und dann wieder auf <code>(0, 0)</code> gesetzt.
   *
   * @param force Ein Vektor, der die Kraft darstellt.
   */
  public void applyForce( PVector force ) {
    acceleration.add(PVector.div(force, mass));
  }

  /**
   * Aktualisiert die momentante Geschwindigkeit und Position des Objektes und
   * setzt die Beschleunigung zurück.
   *
   * @param delta Zeitintervall seit dem letzten Aufruf (in Sekunden).
   */
  public void update() {
    velocity.add(acceleration);
    acceleration.mult(0.0);

    this.position.x += velocity.x;
    this.position.y += velocity.y;
  }

  public void draw() {
    stroke(0);
    int clr = lerpColor(#d5c322, #d54222, mass/150.0);
    fill(clr);
    // fill(34, 213, 49);
    circle(position.x, position.y, size);
  }

}
