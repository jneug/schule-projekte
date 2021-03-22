Sun sun = new Sun();
Planet earth = new Planet("Earth", sun, 120.0, -.01, #0000FF);
Moon moon = new Moon("Moon", earth, 8, 30, .005);

Planet mars = new Planet("Mars", sun, 250.0, -.0128, #FF3311);

void setup() {
  size(800, 600);
}

void draw() {
  // width und height sind die Breite / Höhe des Programmfensters.
  // Du könntest hier auch einfach 400 und 300 benutzen.
  translate(width/2, height/2);
  background(0);
  
  sun.draw();
  earth.draw();
  moon.draw();
  mars.draw();
  
  earth.update();
  moon.update();
  mars.update();
}
