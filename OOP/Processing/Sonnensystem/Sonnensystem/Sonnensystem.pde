Sun sun = new Sun();
Planet earth = new Planet("Earth", sun, 120.0, -.01, #0000FF);
Moon moon = new Moon("Moon", earth, 8, 30, .005);
ISS iss = new ISS(earth);
SpaceElevator elevator = new SpaceElevator(earth, moon);

Planet mars = new Planet("Mars", sun, 250.0, -.0128, #FF3311);
Moon marsmoon = new Moon("Mars moon", mars, 8, 30, .005);

void setup() {
  size(800, 600);
  earth.setImage("earth.png");
}

void draw() {
  // width und height sind die Breite / Höhe des Programmfensters.
  // Du könntest hier auch einfach 400 und 300 benutzen.
  translate(width/2, height/2);
  background(0);
  
  sun.draw();
  elevator.draw();
  earth.draw();
  moon.draw();
  mars.draw();
  marsmoon.draw();
  iss.draw();
 
  
  earth.update();
  moon.update();
  elevator.update();
  mars.update();
  marsmoon.update();
  iss.update();
}
