// Eine Variable vom Datentyp Sun deklarieren //<>//
Sun sol;
// Eine Variable vom Typ Planet
Planet earth;
Planet mars;

Raumschiff ship, ship2;

void setup() {
  size(800, 600);

  // Ein Objekt der Klasse Sun erstellen
  // -> Führt den Konstruktor aus
  sol = new Sun(0.0, 0.0);
  earth = new Planet("Erde", sol, 100.0, 0.0, 0.05, color(0, 0, 255));
  mars = new Planet("Mars", sol, 150.0, 10.0, 0.075, color(255, 0, 0));
  
  PVector pos = new PVector(0.0, 0.0);
  ship = new Raumschiff(pos, new PVector(0.0, 0.0), 2.0);
}

void draw() {
  background(0);
  // Mittelpunkt verschieben
  translate(width/2, height/2);

  earth.move();
  mars.move();

  PVector newVel = PVector.sub(new PVector(mouseX-width/2.0, mouseY-height/2.0), ship.getPosition());
  ship.beschleunigen(newVel);

  // Methoden der Klassen benutzen
  sol.draw();
  earth.draw();
  mars.draw();
  ship.draw();

  sol.attract(earth);
  sol.attract(mars);
}
