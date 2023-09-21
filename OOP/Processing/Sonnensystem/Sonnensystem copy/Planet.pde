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
  /*aufg* >=3
   
  private PImage img;
  *aufg*/

  // Der Konstruktor
  public Planet( String pName, Sun pSun, float pDistance, float pAngle,
    float pSpeed, int pColor ) {
    name = pName;
    sun = pSun;

    distance = pDistance;
    angle = pAngle;

    /*aufg* =1
    x = distance * cos(angle);
    y = distance * sin(angle);
    *aufg*/
    /*aufg* >=2
    x = distance * cos(angle) + sun.getX();
    y = distance * sin(angle) + sun.getY();
    *aufg*/

    speed = pSpeed;
    clr = pColor;
  }
  /*aufg* >=3
   
  public void setImage(String pImg) {
    img = loadImage(pImg);
    img.resize(20, 20);
  }
  *aufg*/

  /*aufg* >=1
  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }
  *aufg*/
  //ml*
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
    /*aufg* >=3
    if ( img != null ) {
      image(img, x-10.0, y-10.0);
    } else {
      ellipse(x-10.0, y-10.0, 20.0, 20.0);
    }
    *aufg*/
    /*aufg* <23
    ellipse(x-10.0, y-10.0, 20.0, 20.0);
    *aufg*/
    /*aufg* >=2
    text(name, x+10, y+15);
    *aufg*/
    //ml*
    ellipse(x-10.0, y-10.0, 20.0, 20.0);
    text(name, x+10, y+15);
    //*ml
  }

  /*aufg* >=2
  public void update() {
    angle += speed;
    x = distance * cos(angle) + sun.getX();
    y = distance * sin(angle) + sun.getY();
  }
  *aufg*/
  //ml*
  public void update() {
    angle += speed;
    x = distance * cos(angle) + sun.getX();
    y = distance * sin(angle) + sun.getY();
  }
  //*ml
}
