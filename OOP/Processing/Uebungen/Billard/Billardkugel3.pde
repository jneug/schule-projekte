class Billardkugel3 {
   
   private PVector pos;
   private PVector vel;
   
   public Billardkugel3( float pX, float pY, float vX, float vY ) {
     pos = new PVector(pX, pY);
     vel = new PVector(vX, vY);
   }
   
   public void draw() {
     noStroke();
     fill(#AA0033);
     circle(pos.x, pos.y, 15);
   }
   
   public void update() {
     pos.add(vel);
     if( pos.x > width || pos.y > height || pos.x < 0 || pos.y < 0 ) {
       pos.sub(vel);
       vel.rotate(QUARTER_PI);
     }
   }
}
