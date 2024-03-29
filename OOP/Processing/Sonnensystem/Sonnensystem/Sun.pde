public class Sun {

  // Deklaration von Objektvariablen
  private float x;
  private float y;

  // Ein Konstruktor ohne Parameter
  public Sun() {
    x = 0.0;
    y = 0.0;
  }

  //ml* >=1
  // Ein zweiter Konstruktor mit Parametern
  public Sun( float pX, float pY) {
    x = pX;
    y = pY;
  }
  //*ml

  // Methode der Klasse
  public void draw() {
    noStroke();
    fill(255, 252, 64);
    ellipse(x, y, 50, 50);
  }

  //ml* >=1
  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }
  //*ml

}
