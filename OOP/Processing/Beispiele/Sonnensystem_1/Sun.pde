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

  // Methode der Klasse
  public void draw() {
    noStroke();
    fill(255,252,64);
    ellipse(x, y, 30, 30);
  }
  
}
