public class Fish {

  private PVector pos;

  private float speed;

  private PImage links, rechts;

  public Fish() {
    randomize();
  }

  private void randomize() {
    int i = (int) random(1, 7);
    links = loadImage("tiles/fish" + i + "_links.png");
    rechts = loadImage("tiles/fish" + i + "_rechts.png");
    speed = int(random(-3, 3));
    pos = new PVector(random(50, width-rechts.width-50), random(30, height-64-rechts.height));
  }

  public void draw() {
    if( speed < 0 ) {
      image(links, pos.x, pos.y);
    } else {
      image(rechts, pos.x, pos.y);
    }
  }

  public void update() {
    pos.add(speed, .5*sin(frameCount / (speed * 10.0)));
    if ( pos.x <= 0 || pos.x+rechts.width >= width ) {
      speed *= -1;
    }
  }
 
}
