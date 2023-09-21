class VerletObject {
  
  private PVector position, position_old, acceleration;
  
  private float radius = 25.0;
  
  public VerletObject() {
    this(new PVector(width/2, height/2), new PVector());
  }
  
  public VerletObject(PVector pos) {
     this(pos, new PVector());
  }
  
  public VerletObject(PVector pos, PVector acc) {
     this(pos, acc, random(10.0, 36.0));
  }
  
  public VerletObject(PVector pos, float radius) {
     this(pos, new PVector(), radius);
  }
  
  public VerletObject(PVector pos, PVector acc, float radius) {
     position = pos.copy();
     position_old = position.copy();
     acceleration = acc.copy();
     this.radius = radius;
  }
  
  public float getRadius() {
    return radius;    
  }
  
  public PVector getPosition() {
    return position;
  }
  
  public void setPosition( PVector pos ) {
    position = pos.copy();
  }
  
  public void applyForce( PVector force ) {
    acceleration.add(force);    
  }
  
  public void update( float delta ) {
    PVector vel = PVector.sub(position, position_old);
    position_old = position.copy();
    position.add(vel).add(acceleration.mult(delta * delta));
    acceleration.mult(0.0);
  }
  
  public void draw() {
    fill(radius*10, 85, 90);
    noStroke();
    circle(position.x, position.y, radius+radius);
  }
  
}
