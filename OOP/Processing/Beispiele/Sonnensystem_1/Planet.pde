public class Planet {
  // Deklaration von Objektvariablen
  private String name;
  
  private Sun sun;
  
  private float distance;
  private float angle;
  
  private float x;
  private float y;
  
  private float speed;
  
  private int clr;

  // Der Konstruktor
  public Planet( String pName, Sun pSun, float pDistance, float pAngle,
          float pSpeed, int pColor ) {
    name = pName;
    sun = pSun;
    distance = pDistance;
    angle = pAngle;
    
    // Position des Planeten berechnen
    x = distance * cos(angle);
    y = distance * sin(angle);
    
    speed = pSpeed;
    clr = pColor;
  }

  // Methode der Klasse
  public void draw() {
    noStroke();
    fill(clr);
    ellipse(x, y, 10, 10);
  }
  
}
