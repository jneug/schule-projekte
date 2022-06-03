/*
Objektorientiertes Aquarium
 
 Basierend auf http://blog.schockwellenreiter.de/2021/05/2021050301.html
 
 Im Aquarium schwimmen zufällig generierte Fische hin und her.
 */

Fish[] fish;

Background bg;

void setup() {
  size(640, 480);
  bg = new Background();

  fish = new Fish[10];
  for ( int i = 0; i < fish.length; i++ ) {
    fish[i] = new Fish();
  }
}

void draw() {
  background(0);
  bg.draw();

  for ( int i = 0; i < fish.length; i++ ) {
    fish[i].update();
    fish[i].draw();
  }
}
