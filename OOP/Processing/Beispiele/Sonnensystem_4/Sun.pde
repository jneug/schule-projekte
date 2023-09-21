public class Sun {
  // Deklaration von Objektvariablen
  private float x;
  private float y;

  // Ein Konstruktor ohne Parameter
  public Sun() {
    x = 0.0;
    y = 0.0;
  }
  
  // Ein zweiter Konstruktor mit Parametern
  public Sun( float pX, float pY) {
    x = pX;
    y = pY;
  }
  
  public float getX() {
    return 0.0;
  }
  
  public float getY() {
    return 0.0;
  }

  // Methode der Klasse
  public void draw() {
    noStroke();
    fill(255,252,64);
    ellipse(getX(), getY(), 30, 30);
  }
  
  public void attract( Planet pPlanet ) {
    float d = pPlanet.getDistance();
    pPlanet.setDistance(d + sin(millis()/1000.0)*0.5);
    //pPlanet.setDistance(d - 1);
  }
  
}
