public class Bienenkorb {

  private PVector pos;
  
  private int honig = 0;

  public Bienenkorb( int x, int y ) {
    pos = new PVector(x, y);
  }

  public PVector getPosition() {
    return pos;
  }

  public Biene abfliegen() {
    return new Biene(pos.copy(), random(30.0, 50.0), this);
  }
  
  public void ankommen(Biene pBiene) {
    honig += pBiene.getNektar();
  }

  public void draw() {
    fill(#ffdb29);
    stroke(0);
    strokeWeight(2);

    circle(pos.x, pos.y, 50);
    circle(pos.x, pos.y, 30);
    circle(pos.x, pos.y, 10);
  }
}
