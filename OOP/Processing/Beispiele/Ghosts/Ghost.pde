
public class Ghost {

  public static final int TAIL_LENGTH = 30;


  private float x;

  private float y;

  private float size;

  private float offset;

  private float wiggliness;

  private float floatiness;

  private PVector[] tail;

  private color clr;

  public Ghost() {
    x = random(10, width-10);
    y = random(10, height-10);
    randomize();
  }

  public void randomize() {
    size = random(10, 100);
    offset = random(100);
    wiggliness = random(2, 10);
    floatiness = random(2, 10);
    clr = color(random(0, 256), random(0, 256), random(0, 256));

    tail = new PVector[TAIL_LENGTH];
  }

  public void update() {
    // Move the ghost left and right.
    x += cos((offset + frameCount) / 10) * wiggliness;
    // Move the ghost up.
    y -= floatiness;
    // If this ghost goes off the top, start it back at the bottom.
    if (y < -size) {
      y = height + size;
      // randomize();
    }

    // Add a point to the beginning of the array.
    for ( int i = tail.length; i > 1; i-- ) {
      tail[i-1] = tail[i-2];
    }
    tail[0] = new PVector(x, y);
  }

  public void draw() {
    for ( int i = 0; i < tail.length; i++ ) {
      if ( tail[i] == null ) {
        break;
      }
      drawDot(i);
    }
    drawFace();
  }

  public void drawDot( int i ) {
    // Tail gets smaller and more transparent.
    float pointSize = size * (tail.length - i) / tail.length;
    float pointAlpha = 255 * (tail.length - i) / tail.length;

    fill(clr, pointAlpha);
    //noStroke();
    stroke(33, 50);
    circle(tail[i].x, tail[i].y, pointSize);
  }

  public void drawFace() {
    // Draw this ghost's face. O_O
    fill(32);
    circle(x - size * .2,
      y - size * .1,
      size * .2);
    circle(x + size * .2,
      y - size * .1,
      size * .2);
    circle(x,
      y + size * .2,
      size * .2);
  }
}
