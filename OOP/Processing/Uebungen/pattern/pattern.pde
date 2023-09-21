
color top = color(255,204,2);//color(210, 199, 183);
color left = color(255);
color right = color(0);

int size = 20;

void setup() {
  size(800, 800);
  noStroke();
  noLoop();
}

void drawCubes() {
  /*for ( int x = 0; x < width; x += dx ) {
    for ( int y = height + 3*size; y > 0; y -= 3*size ) {
    }
  }*/
  drawCube(400+2.25*size, 400+1.5*size);
  drawCube(400+2.25*size, 400+1.5*size-3*size);
  drawCube(400, 400);
  drawCube(400, 400-3*size);
}

void draw() {
  float dx = 3*size*cos(radians(-30));
  float dy = 3*size*sin(radians(-30));
  int i = 0;

  for ( int x = 0; x < width; x += dx ) {
    float ddy = 0;
    if ( i%2 == 1 ) {
      ddy = dy;
    }

    for ( int y = height + 3*size; y > 0; y -= 3*size ) {
      drawPattern((int)x, (int)(y + ddy));
    }
    i++;
  }
}

void drawPattern( int x, int y ) {
  int angle;

  fill(right);
  beginShape();
  vertex(x, y);

  vertex(x, y - 2*size);

  angle = -30;
  vertex(x + 2*size*cos(radians(angle)), y - 2*size + 2*size*sin(radians(angle)));

  vertex(x + 2*size*cos(radians(angle)), y - size + 2*size*sin(radians(angle)));

  angle = -30;
  vertex(x + size*cos(radians(angle)), y - size + size*sin(radians(angle)));
  vertex(x + size*cos(radians(angle)), y + size*sin(radians(angle)));

  endShape(CLOSE);


  fill(left);
  beginShape();
  vertex(x, y);

  vertex(x, y - 2*size);

  angle = -30;
  vertex(x - 2*size*cos(radians(angle)), y - 2*size + 2*size*sin(radians(angle)));

  vertex(x - 2*size*cos(radians(angle)), y - size + 2*size*sin(radians(angle)));

  angle = -30;
  vertex(x - size*cos(radians(angle)), y - size + size*sin(radians(angle)));
  vertex(x - size*cos(radians(angle)), y + size*sin(radians(angle)));

  endShape(CLOSE);


  fill(top);
  beginShape();
  vertex(x, y - 2*size);

  angle = -30;
  vertex(x + 2*size*cos(radians(angle)), y - 2*size + 2*size*sin(radians(angle)));
  vertex(x, y - 4*size);
  vertex(x - 2*size*cos(radians(angle)), y - 2*size + 2*size*sin(radians(angle)));

  endShape(CLOSE);
}

void drawCube( float x, float y ) {
  fill(right);
  beginShape();
  vertex(x,y);
  vertex(x,y-2*size);
  vertex(x+1.5*size,y-3*size);
  vertex(x+1.5*size,y-size);
  endShape(CLOSE);
  
  fill(left);
  beginShape();
  vertex(x,y);
  vertex(x,y-2*size);
  vertex(x-1.5*size,y-3*size);
  vertex(x-1.5*size,y-size);
  endShape(CLOSE);
  
  
  fill(top);
  beginShape();
  vertex(x,y-2*size);
  vertex(x+1.5*size,y-3*size);
  vertex(x,y-4*size);
  vertex(x-1.5*size,y-3*size);
  endShape(CLOSE);
  
  /*
   float angle = radians(-30);
  pushMatrix();
  translate(x, y);
  
  pushMatrix();
  shearY(angle);
  fill(right);
  square(0, 0, 2*size);
  popMatrix();
  
  pushMatrix();
  shearY(radians(30));
  fill(left);
  square(0, 2*size, -2*size);
  popMatrix();
  
  pushMatrix();
  shearY(radians(-30));
  shearX(radians(30));
  fill(top);
  square(0, 0, -2*size);
  popMatrix();
  popMatrix();
  */
}
