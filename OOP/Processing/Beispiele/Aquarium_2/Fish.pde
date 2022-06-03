public class Fish {

  private PVector pos;

  private float speed;

  private PImage alive, dead;
  
  private boolean isAlive;

  public Fish() {
    randomize();
    pos.x = random(30, width-30);
  }

  private void randomize() {
    alive = loadImage("tiles/fish" + int(random(1, 7)) + ".png");
    dead = loadImage("tiles/skeleton" + int(random(1,10)) + ".png");
    speed = random(1, 3);
    pos = new PVector(random(-270, -30), random(30, height-30-alive.height));
    isAlive = true;
  }
  
  public void die() {
    isAlive = false;
  }
  
  public boolean isAlive() {
    return isAlive;
  }

  public void draw() {
    if( isAlive ) {
      image(alive, pos.x, pos.y);
    } else {
      image(dead, pos.x, pos.y);
    }
  }

  public void update() {
    if( isAlive ) { 
      pos.add(speed, .5*sin(frameCount / (speed * 10.0)));
      if ( pos.x > width+30 ) {
        randomize();
      }
    } else {
      pos.add(0, 0.69);
      if ( pos.y > height-60 ) {
        randomize();
      }
    }
  }

  public PVector getPosition() {
    return pos;
  }

  public PVector getSize() {
    return new PVector(alive.width, alive.height);
  }
}
