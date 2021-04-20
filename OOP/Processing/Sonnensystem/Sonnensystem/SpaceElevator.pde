class SpaceElevator {
  
  private Planet planet;
  private Moon moon;
  
  private float pos;
  
  public SpaceElevator( Planet pPlanet, Moon pMoon) {
    planet = pPlanet;
    moon = pMoon;
    pos = 0.0;
  }
  
  public void draw() {
    stroke(235);    
    strokeWeight(2);
    line(moon.getX(), moon.getY(), planet.getX(), planet.getY()); //<>//
    
   
    PVector vElevator = new PVector(moon.getX(), moon.getY(), 0);
    PVector vEarth = new PVector(earth.getX(), earth.getY(), 0);
    vElevator.sub(vEarth);
    vElevator.mult((sin(pos)+1)/2);
    
    fill(235);
    noStroke();
    square(vEarth.x+vElevator.x+1, vEarth.y+vElevator.y+1, 4);
  }
  
  public void update() {
    pos += .01;
  }
}
