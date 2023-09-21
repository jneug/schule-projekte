// Eine Variable vom Typ Sun deklarieren
Sun sol;
// Eine Variable vom Typ Planet deklarieren
Planet earth;
//ml* >=1
Moon moon;
//*ml
//ml* >=2

Planet mars, saturn;
Moon iapetus, prometheus; 
//*ml
//ml* >=3

ISS iss;
SpaceElevator elevator;
//*ml


void setup() {
  size(800, 600);

  // Ein Objekt der Klasse Sun erstellen
  // -> Führt den Konstruktor aus
  sol = new Sun();
  earth = new Planet("Earth", sol, 100.0, 0.0, -.01, #0000FF);  
  //ml* >=3
  earth.setImage("earth.png");
  //*ml
  //ml* >=1
  moon = new Moon("Moon", earth, 20, radians(180), .005, 8);
  //*ml
  //ml* >=2
  
  print(radians(35));
  mars = new Planet("Mars", sol, 152, radians(35), -.0128, color(255, 51, 17));
  saturn = new Planet("Saturn", sol, 304, radians(192), -.0015, color(122, 129, 255));
  iapetus = new Moon("Iapetus", saturn, 30, radians(-12), .0048, 6);
  prometheus = new Moon("Prometheus", saturn, 55, radians(48), -.0062, 9);
  //*ml
  //ml* >=3
  
  iss = new ISS(earth);
  elevator = new SpaceElevator(earth, moon);
  //*ml
}

void draw() {
  background(0);
  // Mittelpunkt verschieben
  translate(width/2, height/2);
  
  // Methoden der Klassen benutzen
  sol.draw();
  //ml* >=3
  elevator.draw();
  //*ml
  earth.draw();
  //ml* >=1
  moon.draw();
  //*ml
  //ml* >=2
  
  mars.draw();
  saturn.draw();
  iapetus.draw();
  prometheus.draw();
  //*ml
  //ml* >=3
  iss.draw();
 
  //ml* >=2
  earth.update();
  moon.update();
  mars.update();
  saturn.update();
  iapetus.update();
  prometheus.update();
  //*ml
  //ml* >=3
  elevator.update();
  iss.update();
  //*ml
  
}
