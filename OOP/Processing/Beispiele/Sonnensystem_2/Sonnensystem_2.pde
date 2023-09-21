// Eine Variable vom Datentyp Sun deklarieren
Sun sol;
// Eine Variable vom Typ Planet
Planet earth;
Planet mars;

void setup() {
  size(800, 600);

  // Ein Objekt der Klasse Sun erstellen
  // -> Führt den Konstruktor aus
  sol = new Sun(0.0, 0.0);
  earth = new Planet("Erde", sol, 100.0, 0.0, 0.05, color(0, 0, 255));
  mars = new Planet("Mars", sol, 150.0, 10.0, 0.075, color(255, 0, 0));
}

void draw() {
  background(0);
  // Mittelpunkt verschieben
  translate(width/2, height/2);

  earth.move();
  mars.move();

  // Methoden der Klassen benutzen
  sol.draw();
  earth.draw();
  mars.draw();
  
  sol.attract(earth);
  sol.attract(mars);
}
