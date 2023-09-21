float tileSize = 40;

void setup() {
  size(600, 600);
}

void draw() {
  drawIsoCube(6,6,1);
  drawOrthoCube(6,6,1);
}

void drawNormRect( float ox, float oy, float size ) {
  float[] norm = toNorm(ox, oy);
  float xA = norm[0];
  float yA = norm[1];
  norm = toNorm(ox + size, oy);
  float xB = norm[0];
  float yB = norm[1];
  norm = toNorm(ox + size, oy + size);
  float xC = norm[0];
  float yC = norm[1];
  norm = toNorm(ox, oy + size);
  float xD = norm[0];
  float yD = norm[1];

  noFill();
  strokeWeight(2);
  stroke(0, 0, 255);
  line(xA, yA, xB, yB);
  line(xB, yB, xC, yC);
  line(xC, yC, xD, yD);
  line(xD, yD, xA, yA);
}

void drawIsoRect( float ox, float oy, float size ) {
  float[] norm = toIso(ox, oy);
  float xA = norm[0];
  float yA = norm[1];
  norm = toIso(ox + size, oy);
  float xB = norm[0];
  float yB = norm[1];
  norm = toIso(ox + size, oy + size);
  float xC = norm[0];
  float yC = norm[1];
  norm = toIso(ox, oy + size);
  float xD = norm[0];
  float yD = norm[1];

  noFill();
  strokeWeight(2);
  stroke(255, 0, 0);
  line(xA, yA, xB, yB);
  line(xB, yB, xC, yC);
  line(xC, yC, xD, yD);
  line(xD, yD, xA, yA);
}

void drawOrthoRect( float ox, float oy, float size ) {
  float[] norm = toIso(ox, oy);
  float xA = norm[0];
  float yA = norm[1];
  norm = toIso(ox + size, oy);
  float xB = norm[0];
  float yB = norm[1];
  norm = toIso(ox + size, oy + size);
  float xC = norm[0];
  float yC = norm[1];
  norm = toIso(ox, oy + size);
  float xD = norm[0];
  float yD = norm[1];

  noFill();
  strokeWeight(2);
  stroke(255, 0, 0);
  line(xA, yA, xB, yB);
  line(xB, yB, xC, yC);
  line(xC, yC, xD, yD);
  line(xD, yD, xA, yA);
}

void drawIsoCube( float ox, float oy, float size ) {
  float[] norm = toIso(ox, oy);
  float xA = norm[0];
  float yA = norm[1];
  norm = toIso(ox + size, oy);
  float xB = norm[0];
  float yB = norm[1];
  norm = toIso(ox + size, oy + size);
  float xC = norm[0];
  float yC = norm[1];
  norm = toIso(ox, oy + size);
  float xD = norm[0];
  float yD = norm[1];

  noFill();
  strokeWeight(2);
  stroke(255, 0, 0);
  line(xA, yA, xB, yB);
  line(xB, yB, xC, yC);
  line(xC, yC, xD, yD);
  line(xD, yD, xA, yA);
  
  float tileHeight = tileSize*size*0.5;
  line(xA, yA - tileHeight, xB, yB - tileHeight);
  line(xB, yB - tileHeight, xC, yC - tileHeight);
  line(xC, yC - tileHeight, xD, yD - tileHeight);
  line(xD, yD - tileHeight, xA, yA - tileHeight);
  
  line(xA, yA, xA, yA - tileHeight);
  line(xB, yB, xB, yB - tileHeight);
  line(xC, yC, xC, yC - tileHeight);
  line(xD, yD, xD, yD - tileHeight);
}

void drawOrthoCube( float ox, float oy, float size ) {
  float[] norm = toOrtho(ox, oy);
  float xA = norm[0];
  float yA = norm[1];
  norm = toOrtho(ox + size, oy);
  float xB = norm[0];
  float yB = norm[1];
  norm = toOrtho(ox + size, oy + size);
  float xC = norm[0];
  float yC = norm[1];
  norm = toOrtho(ox, oy + size);
  float xD = norm[0];
  float yD = norm[1];

  noFill();
  strokeWeight(2);
  stroke(0, 255, 0);
  line(xA, yA, xB, yB);
  line(xB, yB, xC, yC);
  line(xC, yC, xD, yD);
  line(xD, yD, xA, yA);
  
  line(xA, yA - tileSize*size, xB, yB - tileSize*size);
  line(xB, yB - tileSize*size, xC, yC - tileSize*size);
  line(xC, yC - tileSize*size, xD, yD - tileSize*size);
  line(xD, yD - tileSize*size, xA, yA - tileSize*size);
  
  line(xA, yA, xA, yA - tileSize*size);
  line(xB, yB, xB, yB - tileSize*size);
  line(xC, yC, xC, yC - tileSize*size);
  line(xD, yD, xD, yD - tileSize*size);
}

float[] toNorm( float x, float y ) {
  return new float[]{
    x * tileSize,
    y * tileSize
  };
}

float[] toIso( float x, float y ) {
  return new float[]{
    .5 * tileSize * (x - y - 1) + 0.5 * width,
    0.25 * tileSize * (x + y)
  };
}

float[] fromIso( float x, float y ) {
  return new float[2];
}

float[] toOrtho( float x, float y ) {
  return new float[]{
    x * tileSize - y * 0.5 * tileSize - 0.5 * tileSize + 0.5 * width,
    y * 0.5 * tileSize
  };
}

float[] fromOrtho( float x, float y ) {
  return new float[2];
}
