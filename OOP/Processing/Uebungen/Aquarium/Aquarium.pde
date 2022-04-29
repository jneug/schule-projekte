Fish[] fish;
Shark shark;

PImage background;

void setup() {
  size(640, 480);
  background = loadImage("background.png");

  fish = new Fish[10];
  for ( int i = 0; i < fish.length; i++ ) {
    fish[i] = new Fish();
  }
  
  shark = new Shark();
}

void draw() {
  background(0);
  image(background, 0, 0);

  shark.update();
  shark.draw();
  
  for ( int i = 0; i < fish.length; i++ ) {
    if( shark.eats(fish[i]) ) {
      fish[i].randomize();
    } else {
      fish[i].update();
      fish[i].draw();
    }
  }
}
