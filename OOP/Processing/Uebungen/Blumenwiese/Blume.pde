public class Blume { //<>// //<>//

  public final int MAX_ALTER = 60;


  private PVector pos;

  private float alter = 8;

  private int nektar = 500;

  private color farbe;

  public Blume(int x, int y) {
    pos = new PVector(x, y);

    farbe = color(random(0, 255), random(0, 255), random(0, 255));
  }

  public int getAlter() {
    return floor(alter);
  }

  public int getNektar() {
    return nektar;
  }

  public int sammeln() {
    if ( nektar > 0 ) {
      nektar -= 1;
      return 1;
    } else {
      return 0;
    }
  }

  public PVector getPosition() {
    return pos;
  }

  public boolean istVerwelkt() {
    return getAlter() == MAX_ALTER && random(0, 1) > P_VERWELKEN;
  }

  public void altern() {
    if ( alter < MAX_ALTER ) {
      alter += (1.0/frameRate);
    } else {
      alter = MAX_ALTER;
    }
  }

  public void draw() {
    fill(farbe);
    stroke(lerpColor(farbe, color(0), .33));
    strokeWeight(1);
    ellipse(pos.x, pos.y, 2*getAlter(), 1*getAlter());
    ellipse(pos.x, pos.y, 1*getAlter(), 2*getAlter());
    ellipse(pos.x, pos.y, 1*getAlter()*(nektar/500.0), 1*getAlter()*(nektar/500.0));
  }
}
