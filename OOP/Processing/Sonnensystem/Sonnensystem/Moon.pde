//ml* >=1
public class Moon {
  // Deklaration von Objektvariablen
  private String name;

  private Planet planet;

  private float distance;
  private float angle;

  private float x;
  private float y;

  private float speed;

  private float size;


  // Konstruktor
  public Moon( String pName, Planet pPlanet, float pDistance, float pAngle,
    float pSpeed, float pSize ) {
    name = pName;
    planet = pPlanet;
    
    distance = pDistance;
    angle = pAngle;
    
    x = distance * cos(angle) + planet.getX();
    y = distance * sin(angle) + planet.getY();
    
    speed = pSpeed;
    size = pSize;
  }

  // Getter für x
  public float getX() {
    return x;
  }

  // Getter für y
  public float getY() {
    return y;
  }

  public void draw() {
    noStroke();
    fill((size*12) % 200 + 55);
    ellipse(x, y, size, size);
  }

  public void update() {
    angle += speed;
    x = distance * cos(angle) + planet.getX();
    y = distance * sin(angle) + planet.getY();
  }
}
//*ml
