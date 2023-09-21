// Eine Variable vom Datentyp Sun deklarieren
Sun sol;
// Eine Variable vom Typ Planet
Planet earth;

void setup() {
  size(800, 600);

  // Ein Objekt der Klasse Sun erstellen
  // -> Führt den Konstruktor aus
  sol = new Sun(0.0, 0.0);
  earth = new Planet("Erde", sol, 100.0, 0.0, 0.5, color(0, 0, 255));
}

void draw() {
  background(0);
  // Mittelpunkt verschieben
  translate(width/2, height/2);

  // Methoden der Klassen benutzen
  sol.draw();
  earth.draw();
}
