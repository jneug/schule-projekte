Ballon[] ballons = new Ballon[10];
Spitze[] spitzen = new Spitze[60];

float pingX = -10, pingY = -10, pingSize = 1000;

void setup() {
	size(600, 600);
	for( int i = 0; i < spitzen.length; i++ ) {
		spitzen[i] = new Spitze(i*10);
	}
}

void draw() {
  background(34, 28, 196);
  
  if( pingSize < 50 ) {
    stroke(0); //<>//
    noFill();
    ellipse(pingX, pingY, pingSize, pingSize);
    pingSize *= 1.2;
  }

	for( int i = 0; i < spitzen.length; i++ ) {
		spitzen[i].draw();
	}

	for( int i = 0; i < ballons.length; i++ ) {
		if( ballons[i] == null || !ballons[i].isAlive() ) {
			ballons[i] = new Ballon();
		} else {
			ballons[i].draw();
			ballons[i].update();

			if( ballons[i].getY() < 20 ) {
				ballons[i].kill();
			}
		}
	}
}

void mouseClicked() {
  pingX = mouseX;
  pingY = mouseY;
  pingSize = 2;
  
  for( int i = 0; i < ballons.length; i++ ) {
    ballons[i].checkHit(mouseX, mouseY);
  }
}
  
