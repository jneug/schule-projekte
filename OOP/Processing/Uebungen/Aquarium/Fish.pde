public class Fish {

  private PVector pos;

  private float speed;

  private PImage img;

  public Fish() {
    randomize();
    pos.x = random(30, width-30);
  }

  private void randomize() {
    int i = (int) random(1, 4);
    img = loadImage("fish" + i + ".png");
    speed = random(1, 3);
    pos = new PVector(random(-270, -30), random(30, height-94));
  }

  public void draw() {
    image(img, pos.x, pos.y);
  }

  public void update() {
    pos.add(speed, .5*sin(frameCount / (speed * 10.0)));
    if ( pos.x > width+30 ) {
      randomize();
    }
  }

  public PVector getPosition() {
    return pos;
  }

  public PVector getSize() {
    return new PVector(img.width, img.height);
  }
}
