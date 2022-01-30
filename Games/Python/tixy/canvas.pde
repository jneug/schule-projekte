int w = 16;
int h = 16;
float size = 20;

void setup() {
  size(320, 320);
  noStroke();
  frameRate(60);
}

void draw() {
  background(0);
  for( int y = 0; y < h; y += 1 ) {
    for( int x = 0; x < w; x += 1 ) {
      int i = y*w + x;
      drawDot(float(i), float(x), float(y));
    }
  }
}

void drawDot(float i, float x, float y) {
  float result = dot(float(millis()) / 1000.0, i, x, y);
  
  if( result < 0 ) {
    fill(244, 32, 65);
  } else {
    fill(255);
  }
  
  result = abs(result);
  float dSize = max(0, min((result * (size-1)), size-1)); 
  float r = size / 2.0;
  circle(r + x*size, r + y*size, dSize);
}
