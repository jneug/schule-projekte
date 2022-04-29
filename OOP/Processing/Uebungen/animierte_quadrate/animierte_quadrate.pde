/*
Animierte Quadrate

Basierend auf http://blog.schockwellenreiter.de/2021/04/2021042801.html
*/

int[] riley = new int[]{
  color(235, 200, 55), color(115, 165, 215), color(155, 195, 80),
  color(230, 135, 170), color(230, 80, 70), color(65, 80, 150)
};

int counter = 0;
int frontier = (int)random(45, 180);

void setup() {
  size(600, 600);
  stroke(255);
  strokeWeight(4);
  frameRate(30);

  float w = width/10.0;
  float h = height/10.0;

  for ( int i = 0; i < 10; i++ ) {
    for ( int j = 0; j < 10; j++ ) {
      fill(riley[(int) random(riley.length)]);
      rect(i*w, j*h, w, h);
    }
  }
}

void draw() {
  float w = width/10.0;
  float h = height/10.0;

  for ( int i = 0; i < 10; i++ ) {
    for ( int j = 0; j < 10; j++ ) {
      if( counter >= frontier ) {
        counter = 0; //<>//
        frontier = (int)random(45,180);
      }
      if( counter == 0 ) {
        fill(riley[(int) random(riley.length)]); //<>//
        rect(i*w, j*h, w, h);
      }
      counter += 1;
    }
  }
}
