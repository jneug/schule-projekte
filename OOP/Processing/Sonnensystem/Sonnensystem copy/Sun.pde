public class Sun {

  // Deklaration von Objektvariablen
  private float x;
  private float y;

  // Ein Konstruktor ohne Parameter
  public Sun() {
    x = 0.0;
    y = 0.0;
  }

  /*aufg* >=1
  // Ein zweiter Konstruktor mit Parametern
  public Sun( float pX, float pY) {
    x = pX;
    y = pY;
  }
  *aufg*/

  // Methode der Klasse
  public void draw() {
    noStroke();
    fill(255, 252, 64);
    ellipse(x-25.0, y-25.0, 50, 50);
  }

  /*aufg* >=1
  public float getX() {
    return 0.0;
  }

  public float getY() {
    return 0.0;
  }
  *aufg*/
  //ml*
  public float getX() {
    return 0.0;
  }

  public float getY() {
    return 0.0;
  }
  //*ml

}
