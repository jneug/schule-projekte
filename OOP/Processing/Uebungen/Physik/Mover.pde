public class Mover {

  private float mass;
  
  private PVector position, velocity, acceleration;

  public Mover( PVector pPosition, PVector pVelocity, float pMass ) {
    position = pPosition;
    velocity = pVelocity;
    acceleration = new PVector();
    
    mass = pMass;
  }

  public void update() {
    velocity.add(acceleration);
    position.add(velocity);
    acceleration.mult(0.0);
  }

  public void draw() {
    ellipse(position.x, position.y, 20, 20);
  }

  public void applyForce( PVector pForce ) {
    acceleration.add(pForce);
  }

}
