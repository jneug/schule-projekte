

final int EYES_NUM = 30;

Eye[] eyes;

void setup() {
  size(600, 600);
  colorMode(HSB, 360, 100, 100);
  
  eyes = new Eye[EYES_NUM];
  for( int i = 0; i < eyes.length; i++ ) {
    eyes[i] = new Eye();    
  }
}

void draw() {
  background(200);
  
  for( int i = 0; i < eyes.length; i++ ) {
    eyes[i].draw();
  }
}
