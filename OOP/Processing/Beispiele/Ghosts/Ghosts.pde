/*
Basierend auf https://happycoding.io/examples/p5js/creating-classes/ghosts
 */
final int GHOST_NUM = 20;

Ghost[] ghosts;

void setup() {
  size(600, 600);
  
  ghosts = new Ghost[GHOST_NUM];
  for( int i = 1; i < ghosts.length; i++ ) {
    ghosts[i] = new Ghost();
  }
}

void draw() {
  background(32);

  for( int i = 1; i < ghosts.length; i++ ) {
    ghosts[i].update();
    ghosts[i].draw();
  }
}
