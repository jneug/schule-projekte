class SpaceElevator {
  
  private Planet planet;
  private Moon moon;
  
  public SpaceElevator( Planet pPlanet, Moon pMoon) {
    planet = pPlanet;
    moon = pMoon;
  }
  
  public void draw() {
    line(planet.getX(), planet.getY(), moon.getX(), moon.getY());
  }
  
  
}
