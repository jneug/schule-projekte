
class Lukas {
  
  private float x, y;
  
  private int timer;
  
  public Lukas() {
    x = random(width);
    y = random(height);
    
    timer = (int) random(10, 100);
  }
  
  public void draw() {
    if( timer > 0 ) {
      timer -= 1;
    } else {
      noStroke();
      fill(#AA0033);
      circle(x, y, 40);
    }
  }
  
  public boolean checkMouse() {
    return abs(mouseX-x) < 15.0 && (mouseY-y) < 15.0;
  }
  
}
