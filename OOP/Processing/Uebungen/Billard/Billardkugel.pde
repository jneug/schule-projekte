class Billardkugel {
   
   private PVector pos;
   private PVector vel;
   
   public Billardkugel( float pX, float pY, float vX, float vY ) {
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
   }
}
