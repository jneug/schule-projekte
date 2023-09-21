// Eine Variable vom Datentyp Sun deklarieren
Sun sol;

// Variablen für die Planeten
// TODO: Durch ein Array "planets" vom Typ "Planet" ersetzen
Planet earth;
Planet mars;

// Variablen für die Raumschiffe
// TODO: Durch ein Array "ships" vom Typ "Raumschiff" ersetzen
Raumschiff ship;
Raumschiff ship2;

void setup() {
  size(800, 600);

  // Sun Objekt erstellen
  sol = new Sun(0.0, 0.0);
  
  // Erstellen der Planeten-Objekte
  // TODO: Planeten im Array "planets" speichern
  // TODO2: Planeten in einer for-Schleife erstellen (ggf. mit Zufallswerten)
  earth = new Planet("Erde", sol, 100.0, 0.0, 0.005, color(0, 0, 255));
  mars = new Planet("Mars", sol, 150.0, 10.0, 0.0075, color(255, 0, 0));
  
  // Erstellen der Raumschiff-Objekte
  // TODO: Raumschiffe im Array "ships" speichern
  // TODO2: Raumschiffe in einer for-Schleife erstellen (ggf. mit Zufallswerten)
  ship = new Raumschiff(new PVector(), new PVector(0.0, 0.0), 2.0);
  ship2 = new Raumschiff(new PVector(), new PVector(0.0, 2.0), 4.0);
}

void draw() {
  background(0);
  // Mittelpunkt verschieben
  translate(width/2, height/2);

  // Planeten bewegen und anziehen
  // TODO: Durch eine for-Schleife ersetzen 
  earth.move();
  sol.attract(earth);
  mars.move();
  sol.attract(mars);

  
  // Bewegung der Raumschiffe berechnen
  // TODO: Durch eine for-Schleife ersetzen 
  PVector mouse = new PVector(mouseX-width/2.0, mouseY-height/2.0);
  PVector newVel1 = PVector.sub(mouse, ship.getPosition());
  PVector newVel2 = PVector.sub(mouse, ship2.getPosition());
  if ( mousePressed ) {
    newVel1.mult(-1.0);
    newVel2.mult(-1.0);
  }
  ship.beschleunigen(newVel1);
  ship2.beschleunigen(newVel2);


  // Alles zeichnen
  // TODO: Durch for-Schleifen ersetzen
  sol.draw();
  earth.draw();
  mars.draw();
  ship.draw();
  ship2.draw();
}
