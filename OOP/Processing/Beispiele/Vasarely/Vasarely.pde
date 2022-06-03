/*
Hommage an Victor Vasarely

Basierend auf http://blog.schockwellenreiter.de/2022/02/2022021202.html
*/
int cols = 15;
int rows = 10;

void setup() {
  size(800, 600);
  rectMode(CENTER);
  frameRate(.5);
  drawTiles();
}

void draw() {
  drawTiles();
}

void drawTiles() {
  for ( int x = 0; x < cols; x++ ) {
    for ( int y = 0; y < rows; y++ ) {
      tile(x, y);
    }
  }
}

void mouseClicked() {
  drawTiles();
}

void tile( int x, int y ) {
  float tileWidth = width/cols;
  float tileHeight = height/rows;
  float tileWidthHalf = tileWidth/2.0;
  float tileHeightHalf = tileHeight/2.0;
  float dia = random(0.6, 0.9);

  noStroke();
  fill(randomBgColor());
  rect(tileWidthHalf + x*tileWidth, tileHeightHalf + y*tileHeight, tileWidth, tileHeight);
  fill(randomFgColor());
  ellipse(tileWidthHalf + x*tileWidth, tileHeightHalf + y*tileHeight, tileWidth*dia, tileHeight*dia);
}

int randomBgColor() {
  // int r = (int)random(256);
  //return color(256-r, constrain(r, 75, 125), r);
  return color((int)random(10, 180), (int)random(10, 180), (int)random(10, 180));
}

int randomFgColor() {
  // int r = (int)random(73, 144);
  // return color(r, r, r);
  return color((int)random(10, 230), (int)random(10, 230), (int)random(10, 230));
}
