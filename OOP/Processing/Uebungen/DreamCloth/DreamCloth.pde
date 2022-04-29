// Based on 
// https://youtu.be/u3d-n41Tobw
float TOTAL_DEGREES = 360.0;

float radius = 0.0;

void setup() {
  size(1024, 768);
  background(0);

  noFill();
  stroke(255, 32);
  
  radius = height / 2.0;
}

void draw() {
  translate(frameCount, 0);
  // background(0);
  float center_x = width / 2.0;
  float center_y = height / 2.0;

  beginShape();
  for ( int i = 0; i < TOTAL_DEGREES; i += 1 ) {
    // float x = center_x + radius * cos(radians(i)) + random(100);
    // float y = center_y + radius * sin(radians(i)) + random(100);
    float noiseFactor = noise(i * 0.02, float(frameCount) / 150.0);
    float x = center_x + radius * cos(radians(i)) * noiseFactor;
    float y = center_y + radius * sin(radians(i)) * noiseFactor;
    curveVertex(x, y);
  }
  endShape(CLOSE);
  
  radius -= 1;
  if( radius <= 0.0 ) {
    noLoop();
  }
}
