Billardkugel3 kugel;

void setup() {
  size(800, 600);
  kugel = new Billardkugel3(width/2, height/2, 2, 4);
}

void draw() {
  background(128);
  
  kugel.draw();
  
  kugel.update();
}
