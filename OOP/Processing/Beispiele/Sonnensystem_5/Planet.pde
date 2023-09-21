public class Planet {
  // Deklaration von Objektvariablen
  private String name;
  
  private Sun sun;
  
  private float distance;
  private float angle;
  
  private float x;
  private float y;
  
  private float speed, size;
  
  private int clr;

  // Der Konstruktor
  public Planet( String pName, Sun pSun, float pDistance, float pAngle,
          float pSpeed, int pColor, float pSize ) {
    name = pName;
    sun = pSun;
    distance = pDistance;
    angle = pAngle;
    
    // Position des Planeten berechnen
    x = distance * cos(angle) + sun.getX();
    y = distance * sin(angle) + sun.getY();
    
    speed = pSpeed;
    size = pSize;
    clr = pColor;
  }
  
  public float getDistance() {
    return distance;
  }
  
  public void setDistance( float pDistance ) {
    distance = pDistance;
  }
  
  public PVector getPosition() {
    return new PVector(x,y);
  }

  // Methode der Klasse
  public void draw() {
    noStroke();
    fill(clr);
    ellipse(x, y, size, size);
  }
  
  public void move() {
    angle += speed;
    x = distance * cos(angle) + sun.getX();
    y = distance * sin(angle) + sun.getY();
  }
  
}
