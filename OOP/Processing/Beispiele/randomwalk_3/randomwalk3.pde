/*
Random walker
 
 Basierend auf https://happycoding.io/examples/p5js/animation/random-walker
 
 Eine beschleunigte Version von randomwalk_0, die 100 Schritte pro Frame macht.
 */
int step = 4;
int size = 4;

int x, y;

int r, g, b;

void setup() {
  size(600, 600);
  background(0);
  frameRate(60);
  noStroke();

  x = int(random(100, 300));
  y = int(random(100, 300));

  r = int(random(255));
  g = int(random(255));
  b = int(random(255));
}

void draw() {
  for (int i = 0; i < 100; i++) {
    update();
    fill(r, g, b);
    rect(x, y, size, size);
  }
}

void update() {
  x += (int) random(-step, step);
  y += (int) random(-step, step);

  x = constrain(x, 0, width);
  y = constrain(y, 0, height);

  r += (int) random(-step, step);
  g += (int) random(-step, step);
  b += (int) random(-step, step);

  r = constrain(r, 0, 255);
  g = constrain(g, 0, 255);
  b = constrain(b, 0, 255);
}
