PVector grav = new PVector(0, 6.734);

Mover[] movers = new Mover[5];

long lastMillis = 0L;

void setup() {
  size(800, 600);

  for ( int i = 0; i < movers.length; i++ ) {
    movers[i] = new Mover(random(50, 750), random(50, 550), random(10.0,100.0), PVector.random2D().mult(2.0));
  }
}

void draw() {
  background(200);

  for ( Mover mover : movers ) {
    mover.applyForce(grav);
    mover.update();

    if ( mover.getPosition().x <= 0 || mover.getPosition().x >= width ) {
      mover.getPosition().x = constrain(mover.getPosition().x, 0, width);
      mover.getVelocity().x *= -1.0;
    }
    // Abprallen vom Boden
    if ( mover.getPosition().y >= height ) { //<>//
      mover.getPosition().y = height;
      mover.getVelocity().y *= -1.0*constrain((100-mover.getMass())/100, 0.1, 0.98); // -1.0; //<>//
      
      //mover.applyForce(new PVector(0.0,-1.0).setMag(mover.getVelocity().mag()*10.0/mover.getMass()));
    } //<>//
  }

  for ( Mover mover : movers ) {
    mover.draw();
  }
}
