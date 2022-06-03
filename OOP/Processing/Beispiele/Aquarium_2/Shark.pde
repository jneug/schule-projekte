public class Shark {

  private PVector pos;

  private float speed;

  private PImage img;

  public Shark() {
    img = loadImage("tiles/shark.png");
    randomize();
  }
  
  private void randomize() {
    speed = random(3, 5);
    pos = new PVector(width + random(30, 270), random(30, height-94));
  }

  public void draw() {
    image(img, pos.x, pos.y);
  }
  
  public void update() {
    pos.add(-1*speed, .5*sin(frameCount / 15.0));
    if( pos.x < -180 ) {
      randomize();
    }
  }
  
  public boolean eats( Fish pFish ) {
    PVector fp = pFish.getPosition();
    PVector fs = pFish.getSize();
    return ( (fp.x <= pos.x+img.width && fp.x+fs.x >= pos.x) && 
        (fp.y <= pos.y+img.height && fp.y+fs.y >= pos.y) );
  }
  
}
