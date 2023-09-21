
Sun sol;

Planet[] planets;

Raumschiff[] ships;

boolean shipRepell = false;

int targetPlanet = 5;

void setup() {
  size(800, 600);

  // Ein Objekt der Klasse Sun erstellen
  // -> Führt den Konstruktor aus
  sol = new Sun(0.0, 0.0);
  
  planets = new Planet[10];
  for( int i = 0; i < planets.length; i++ ) {
    planets[i] = new Planet("Planet "+i, sol, random(50,300), random(0, TWO_PI), random(-0.025, 0.025), color(random(255), random(255), random(255)), random(10,50));
  }
  
  ships = new Raumschiff[12];
  for( int i = 0; i < ships.length; i++ ) {
    ships[i] = new Raumschiff(new PVector(random(-300,300), random(-300,300)), new PVector(0.0, 0.0), random(1.0, 6.0));
  }
  
  targetPlanet = (int)random(0, planets.length-1);
}

void draw() {
  background(0);
  // Mittelpunkt verschieben
  translate(width/2, height/2);

  sol.draw();

  for( int i = 0; i < planets.length; i++ ) {
    planets[i].move();
    sol.attract(planets[i]);
    planets[i].draw();
  }
  
  //PVector target = new PVector(mouseX-width/2.0, mouseY-height/2.0);
  
  PVector target = planets[targetPlanet].getPosition().copy();
  int shipsInRange = 0;
  
  for( int i = 0; i < ships.length; i++ ) {
    if( ships[i] != null ) {
      PVector newVel = PVector.sub(target, ships[i].getPosition());
      if ( mousePressed ) {
        newVel.mult(-1.0);
        ships[i].beschleunigen(newVel);
      } else if( ships[i].getPosition().dist(target) > 10.0 ) {
        ships[i].beschleunigen(newVel);
      } else {
        shipsInRange += 1;
      }
      
      ships[i].draw();
      
      /*if( shipsInRange >= ships.length / 2 ) {
        targetPlanet = (int)random(0, planets.length-1);
      }*/
    }
  }

}

void mouseClicked() {
  targetPlanet = (int)random(0, planets.length-1);
}
