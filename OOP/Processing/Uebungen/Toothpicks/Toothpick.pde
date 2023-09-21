public class Toothpick {

  public int x, y;

  public boolean vertical;

  public color clr;

  public Toothpick( int[] xy, boolean vertical, color clr ) {
    this(xy[0], xy[1], vertical, clr);
  }

  public Toothpick( int x, int y, boolean vertical, color clr ) {
    this.x = x;
    this.y = y;
    this.vertical = vertical;
    this.clr = clr;
  }

  public int[] getCenter() {
    return new int[]{x, y};
  }

  public int[] getA() {
    if ( !vertical ) {
      return new int[]{x, y - PICK_SIZE - STROKE_WEIGHT};
    } else {
      return new int[]{x - PICK_SIZE - STROKE_WEIGHT, y};
    }
  }

  public int[] getB() {
    if ( !vertical ) {
      return new int[]{x, y + PICK_SIZE + STROKE_WEIGHT};
    } else {
      return new int[]{x + PICK_SIZE + STROKE_WEIGHT, y};
    }
  }

  public boolean isAfree() {
    if ( !vertical ) {
      return (get(x, y - PICK_SIZE - STROKE_WEIGHT*4) == color(0));
    } else {
      return (get(x - PICK_SIZE - STROKE_WEIGHT*4, y) == color(0));
    }
  }

  public boolean isBfree() {
    if ( !vertical ) {
      return (get(x, y + PICK_SIZE + STROKE_WEIGHT*4) == color(0));
    } else {
      return (get(x + PICK_SIZE + STROKE_WEIGHT*4, y) == color(0));
    }
  }

  public void draw() {
    stroke(clr);
    if ( !vertical ) {
      line(x, y - PICK_SIZE, x, y + PICK_SIZE);
    } else {
      line(x - PICK_SIZE, y, x + PICK_SIZE, y);
    }

    noFill();
    strokeWeight(1);
    if ( !vertical ) {
      circle(x, y - PICK_SIZE - STROKE_WEIGHT*4, STROKE_WEIGHT*2);
      circle(x, y + PICK_SIZE + STROKE_WEIGHT*4, STROKE_WEIGHT*2);
    } else {
      circle(x - PICK_SIZE - STROKE_WEIGHT*4, y, STROKE_WEIGHT*2);
      circle(x + PICK_SIZE + STROKE_WEIGHT*4, y, STROKE_WEIGHT*2);
    }
    strokeWeight(STROKE_WEIGHT);
  }
}
