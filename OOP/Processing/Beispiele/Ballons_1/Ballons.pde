

float[] xcoords = new float[10];
float[] ycoords = new float[10];
float[] speeds = new float[10];
color[] colors = new color[10];
float[] deltas = new float[10];

void setup() {
  size(400, 500);
  
  for( int i = 0; i < xcoords.length; i++ ) {
    xcoords[i] = random(width);
    ycoords[i] = height + 10;
    speeds[i] = random(1, 4);
    colors[i] = color(int(random(256)), int(random(256)), int(random(256)));
    deltas[i] = random(1000);
  }
}

void draw() {
  background(93, 160, 185);
  
  for( int i = 0; i < xcoords.length; i++ ) {
    float xd = sin(deltas[i] + millis()/350.0) * speeds[i];
    ycoords[i] -= speeds[i];
    fill(colors[i]);
    ellipse(xcoords[i] + xd, ycoords[i], 20, 40);
    
    if( ycoords[i]+40 < 0 ) {
      xcoords[i] = random(width);
      ycoords[i] = height;
      speeds[i] = random(1, 4);
      colors[i] = color(int(random(256)), int(random(256)), int(random(256)));
      deltas[i] = random(1000);
    }
  }
}
