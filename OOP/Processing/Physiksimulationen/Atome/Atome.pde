
Mover[] movers = new Mover[5];

Attractor atom;

long lastMillis = 0L;

void setup() {
  size(800, 600);

  for ( int i = 0; i < movers.length; i++ ) {
    movers[i] = new Mover(random(50, 750), random(50, 550), new PVector(0, 10));
  }

  atom = new Attractor(400, 300, 80);
}

void draw() {
  background(0);
  
  float delta = (millis()-lastMillis) / 1000.0;
  lastMillis = millis();

  for ( Mover mover : movers ) {
    atom.attract(mover);  //<>//
    mover.update(delta);
  }

  for ( Mover mover : movers ) {
    atom.draw();
    mover.draw();
  }
}
