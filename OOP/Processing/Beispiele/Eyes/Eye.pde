public class Eye {
  
  private PVector position;
  
  private float size;
  
  public Eye() {
    position = new PVector(random(width), random(height));
    size = random(20, 50);
  }
  
  public Eye( float x, float y ) {
    position = new PVector(x, y);
    size = random(20, 50);
  }
  
  public void draw() {
    PVector dir = PVector.sub(new PVector(mouseX, mouseY), position);
    float mag = dir.mag();
    
    stroke(0);
    fill(354, 100-constrain(mag, 0, 100), 100); //<>//
    circle(position.x, position.y, size);
    
    
    PVector pupil = PVector.add(position, dir.limit(size*.2));
    
    fill(0);
    circle(pupil.x, pupil.y, size*.4);
  }
  
}
