public class VerletLink {
  
  private VerletObject obj1, obj2;
  
  private float targetDist;
  
  public VerletLink( VerletObject o1, VerletObject o2 ) {
    this(o1, o2, o1.getRadius()+o2.getRadius()+5);
  }
  
  public VerletLink( VerletObject o1, VerletObject o2, float dist ) {
    obj1 = o1;
    obj2 = o2;
    targetDist = dist;
  }
  
  public void update( float delta ) {
    PVector diff = PVector.sub(obj1.getPosition(), obj2.getPosition());
    float dist = diff.mag();
    diff.setMag(0.5 * (targetDist - dist));
    obj1.getPosition().add(diff);
    obj2.getPosition().sub(diff);
  }
  
}
