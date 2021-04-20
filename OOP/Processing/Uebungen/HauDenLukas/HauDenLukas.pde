
Lukas lukas;

void setup() {
  size(800, 600);
}

void draw() {
  if( lukas == null ) {
    lukas = new Lukas();
  }
  
  background(255);
  lukas.draw();
}

void mousePressed() {
  if( lukas.checkMouse() ) {
    lukas = null;
  }
}
