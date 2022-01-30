Ballon[] ballons = new Ballon[10];
Spitze[] spitzen = new Spitze[60];

void setup() {
	size(600, 600);
	// Spitzen erstellen
	for( int i = 0; i < spitzen.length; i++ ) {
		spitzen[i] = new Spitze(i*10);
	}

	// Ballons erstellen
	for( int i = 0; i < ballons.length; i++ ) {
			ballons[i] = new Ballon();
	}
}

void draw() {
  background(34, 28, 196);

	// Spitzen zeichnen
	for( int i = 0; i < spitzen.length; i++ ) {
		spitzen[i].draw();
	}

	// Ballons zeichnen und aktualisieren
	for( int i = 0; i < ballons.length; i++ ) {
		ballons[i].draw();
		ballons[i].update();

		if( ballons[i].getY() < 20 ) {
			ballons[i] = new Ballon();
		}
	}
}
