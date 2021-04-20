class ISS {

  private float x;
  
  private float y;
  
  private float distance;
  
  private float angle;

  private float speed;

  private Planet planet;

  public ISS( Planet pPlanet) {
    planet = pPlanet;
    distance = 12.0;
    
    speed = -0.008;
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
    translate(getX(), getY());
    rotate(1-angle);
    noStroke();
    fill(255);
    
    rect(0,0,6,2);
    rect(2,-3,2,8);
    rect(5,-3,2,8);
    
    rotate(angle);
    translate(-1*getX(), -1*getY());
  }
  
  public void update() {
    angle += speed;
    x = distance * cos(angle);
    y = distance * sin(angle);
  }

}
