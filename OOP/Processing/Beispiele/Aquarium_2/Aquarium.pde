/*
Objektorientiertes Aquarium mit Hai
 
 Basierend auf http://blog.schockwellenreiter.de/2021/05/2021050301.html
  
 Im Aquarium schwimmen zufällig erstellte Fische von links nach rechts. 
 Ab und zu schwimmt ein Hai von rechts nach links und frisst alle Fische 
 auf seinem Pfad.
 */

Fish[] fish;
Shark shark;

Background bg;

void setup() {
  size(640, 480);
  bg = new Background();

  fish = new Fish[10];
  for ( int i = 0; i < fish.length; i++ ) {
    fish[i] = new Fish();
  }
  
  shark = new Shark();
}

void draw() {
  background(0);
  bg.draw();

  shark.update();
  shark.draw();
  
  for ( int i = 0; i < fish.length; i++ ) {
    if( fish[i].isAlive() && shark.eats(fish[i]) ) {
      //fish[i].randomize();
      fish[i].die();
    } else {
      fish[i].update();
      fish[i].draw();
    }
  }
}
