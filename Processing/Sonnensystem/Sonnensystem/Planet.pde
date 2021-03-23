class Planet {

  private String name;

  private float x;
  
  private float y;
  
  private float distance;
  
  private float angle;

  private float speed;

  private Sun sun;
  
  private int clr;

  public Planet( String pName, Sun pSun, float pDist, float pSpeed, int pColor ) {
    name = pName;
    sun = pSun;
    distance = pDist;
    clr = pColor;
    
    speed = pSpeed;
    angle = 0.0;
    x = 0.0;
    y = 0.0;
  }
  
  private PImage img;
  
  public void setImage( String pImg ) {
     img = loadImage(pImg);
     img.resize(20,20);
  }
  
  public float getX() {
    return sun.getX()+x;
  }

  public float getY() {
    return sun.getY()+y;
  }
  
  public void draw() {
    noStroke();
    fill(clr);
    if( img != null ) {
      image(img, getX()-10, getY()-10);
    } else {
      ellipse(getX(),getY(),20,20);
    }
    text(name, getX()+10, getY()+15); 
  }
  
  public void update() {
    angle += speed;
    x = distance * cos(angle);
    y = distance * sin(angle);
  }

}
