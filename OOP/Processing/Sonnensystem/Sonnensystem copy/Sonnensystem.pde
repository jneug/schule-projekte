// Eine Variable vom Typ Sun deklarieren
Sun sol;
// Eine Variable vom Typ Planet deklarieren
Planet earth;
/*aufg* >=1
Moon moon;
*aufg*/
/*aufg* >=2

Planet mars, saturn;
Moon iapetus, prometheus;
*aufg*/
/*aufg* >=3

ISS iss;
SpaceElevator elevator;
*aufg*/
//ml*
Moon moon;

Planet mars, saturn;
Moon iapetus, prometheus;
//*ml


void setup() {
  size(800, 600);

  // Ein Objekt der Klasse Sun erstellen
  // -> Führt den Konstruktor aus
  sol = new Sun();
  earth = new Planet("Earth", sol, 120.0, 0.0, -.01, #0000FF);  
  /*aufg* >=3
  earth.setImage("earth.png");
  *aufg*/
  /*aufg* >=1
  moon = new Moon("Moon", earth, 8, 30, .005);
  *aufg*/
  /*aufg* >=2
  
  mars = new Planet("Mars", sun, 250.0, 100.0, -.0128, #FF3311);
  saturn = new Planet("Saturn", sun, 250.0, 100.0, -.0128, #FF3311);
  iapetus = new Moon("Mars moon", mars, 8, 30, .005);
  *aufg*/
  /*aufg* >=3
  
  iss = new ISS(earth);
  elevator = new SpaceElevator(earth, moon);
  *aufg*/
  //ml*
  moon = new Moon("Moon", earth, 8, 30, .005);
  
  mars = new Planet("Mars", sol, 250.0, PI, -.0128, #FF3311);
  saturn = new Planet("Saturn", sol, 250.0, TWO_PI, -.0028, #7A81FF);
  iapetus = new Moon("Iapetus", saturn, 8, 30, .005);
  prometheus = new Moon("Prometheus", saturn, 8, -30, .0055);
  //*ml
}

void draw() {
  background(0);
  // Mittelpunkt verschieben
  translate(width/2, height/2);
  
  // Methoden der Klassen benutzen
  sol.draw();
  /*aufg* >=3
  elevator.draw();
  *aufg*/
  earth.draw();
  /*aufg* >=1
  moon.draw();
  *aufg*/
  /*aufg* >=2
  
  mars.draw();
  saturn.draw();
  iapetus.draw();
  prometheus.draw();
  *aufg*/
  /*aufg* >=3
  iss.draw();
  *aufg*/
  //ml*
  moon.draw();
  
  mars.draw();
  saturn.draw();
  iapetus.draw();
  prometheus.draw();
  //*ml
 
 
  /*aufg* >=1
  moon.update();
  *aufg*/
  /*aufg* >=2
  earth.update();
  mars.update();
  saturn.update();
  iapetus.update();
  prometheus.update();
  *aufg*/
  /*aufg* >=3
  elevator.update();
  iss.update();
  *aufg*/
  //ml*
  moon.update();
  earth.update();
  mars.update();
  saturn.update();
  iapetus.update();
  prometheus.update();
  //*ml
}
