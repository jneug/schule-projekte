class Ballon {

  private float x, y, speed, size;

  private color clr;

  private boolean isAlive = true;

  public Ballon() {
    x = random(10, width-10);
    y = height-10;
    speed = random(20, 40)/10;
    size = random(20, 30);
    clr = color((int) random(50, 210), (int) random(50, 210), (int) random(50, 210));
  }

  public void draw() {
    noStroke();
    fill(clr);
    ellipse(x, y, size*.7, size);
  }

  public void update() {
    y -= speed;
  }

  public float getY() {
    return y;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void kill() {
    isAlive = false;
  }

  public boolean checkHit( float px, float py ) {
    isAlive = !( x >= px && x <= px + size
      && y >= py  && y <= py + size*.7);
    return !isAlive;
  }
}
