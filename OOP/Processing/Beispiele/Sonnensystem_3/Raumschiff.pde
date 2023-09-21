class Raumschiff {
  private PVector pos, vel;
  
  private PImage img;
  
  private float speed;

  public Raumschiff(PVector pPos, PVector pVel, float pSpeed ) {
    pos = pPos;
    vel = pVel;
    speed = pSpeed;
    
    img = loadImage("rocket.png");
    img.resize(32, 32);
  }
  
  public PVector getPosition() {
    return pos;
  }

  public void setVelocity( PVector pVel ) {
    vel = pVel;
  }

  public void update() {
    pos.add(vel);
  }
  
  public void beschleunigen( PVector pGeschw ) {
    vel = pGeschw.copy();
    vel.setMag(speed);
    update();
  }
  
  public void draw() {
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(vel.heading());
    image(img, 0.0, 0.0);
    popMatrix();
  }
}
