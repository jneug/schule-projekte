float size = 50;
float x = 0;
float y = 0;

void setup() {
  size(400, 400, P3D);
}

void draw() {
  background(200);

  //float s = size+(size*sin(millis()/1000.0)*0.5);
  float s = size;

  translate(0,0,size*sin(millis()/500.0));
  if( millis() > 8000 ) {
    rotate(sin((millis()-8000)/2000.0)*QUARTER_PI);
  }
  if( millis() > 16000 ) {
    shearX(cos((millis()-16000)/1000.0)*0.5);
  }

  for ( float i = -20; i < width/s+20; i+=1.0 ) {
    for ( float j = -20; j < height/s+20; j+=1.0 ) {
      fill(255);
      stroke(0);
      strokeWeight(2);
      rect(x+i*s, y+j*s, s, s);
    }
  }
}
