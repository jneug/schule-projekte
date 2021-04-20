class Moon {

  private String name;

  private float x;
  
  private float y;
  
  private float distance;
  
  private float angle;

  private float speed;
  
  private float size;

  private Planet planet;

  public Moon( String pName, Planet pPlanet, float pSize, float pDist, float pSpeed ) {
    name = pName;
    planet = pPlanet;
    distance = pDist;
    size = pSize;
    
    speed = pSpeed;
    angle = 0.0;
    x = 0.0;
    y = 0.0;
  }
  
  public float getX() {
    return planet.getX()+x;
  }

  public float getY() {
    return planet.getY()+y;
  }
  
  public void draw() {
    noStroke();
    fill(178);
    ellipseMode(CENTER);
    ellipse(getX(),getY(),size,size);
  }
  
  public void update() {
    angle += speed;
    x = distance * cos(angle);
    y = distance * sin(angle);
  }

}
