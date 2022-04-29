
PVector grav = new PVector(0.0, 0.043);
Mover[] movers = new Mover[5];

void setup() {
  size(600, 600);
  
  for( int i = 0; i < movers.length; i++ ) {
    movers[i] = new Mover(new PVector(width/2.0, height/2.0), PVector.random2D().mult(2.0), 10.0);
  }
}

void draw() {
  background(200);
  
  for( int i = 0; i < movers.length; i++ ) {
    movers[i].applyForce(grav);
    movers[i].update();
  }
  
  for( int i = 0; i < movers.length; i++ ) {
    movers[i].draw();
  }
}
