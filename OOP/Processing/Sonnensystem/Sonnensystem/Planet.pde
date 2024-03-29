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
  //ml* >=3
   
  private PImage img;
  //*ml

  // Der Konstruktor
  public Planet( String pName, Sun pSun, float pDistance, float pAngle,
    float pSpeed, int pColor ) {
    name = pName;
    sun = pSun;

    distance = pDistance;
    angle = pAngle;

    //ml* =1
    x = distance * cos(angle);
    y = distance * sin(angle);
    //*ml
    //ml* >=2
    x = distance * cos(angle) + sun.getX();
    y = distance * sin(angle) + sun.getY();
    //*ml

    speed = pSpeed;
    clr = pColor;
  }
  //ml* >=3
   
  public void setImage(String pImg) {
    img = loadImage(pImg);
    img.resize(20, 20);
  }
  //*ml

  //ml* >=1
  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }
  //*ml

  public void draw() {
    noStroke();
    fill(clr);
    //ml* >=3
    if ( img != null ) {
      image(img, x-10.0, y-10.0);
    } else {
      ellipse(x, y, 20.0, 20.0);
    }
    //*ml
    /*aufg* <3
    ellipse(x, y, 20.0, 20.0);
    *aufg*/
    //ml* >=2
    text(name, x+10, y+15);
    //*ml
  }

  //ml* >=2
  public void update() {
    angle += speed;
    x = distance * cos(angle) + sun.getX();
    y = distance * sin(angle) + sun.getY();
  }
  //*ml
}
