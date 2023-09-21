int ANZ_M  = 8;
int ANZ_A = 1;


Mover[] movers;

Attractor[] attractors;

void setup() {
  size(800, 600);
  
  movers = new Mover[ANZ_M + ANZ_A];
  attractors = new Attractor[ANZ_A];

  for ( int i = 0; i < ANZ_M; i++ ) {
    PVector vel = PVector.random2D().mult(5.0);
    movers[i] = new Mover(random(50, 750), random(50, 550), random(50, 150), vel);
  }
  for ( int i = 0; i < ANZ_A; i++ ) {
    //attractors[i] = new Attractor(random(50, 750), random(50, 550), 3.0);
    attractors[i] = new Attractor(width/2, height/2, 50.0);
    movers[ANZ_M + i] = attractors[i]; 
  }
  
}

void draw() {
  background(0);

  for ( Mover mover : movers ) {
    for( Attractor a: attractors ) {
      if( a != mover ) {
        a.attract(mover);
      }
    }
    mover.update();
  }

  for ( Mover mover : movers ) {
    mover.draw();
  }
}
