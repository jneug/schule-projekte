/* //<>//
Hitomezashi Muster
 
 Basierend auf https://aperiodical.com/2020/12/mobile-numbers-hitomezashi-stitching/
 
 Zeichnet ein zufälliges Hitomezashi Muster mit einer Zweifärbung.
 */

// Konfiguration
int TILE_SIZE = 20;
int COLUMNS = 30;
int ROWS = 30;

// W'keit dafür, dass eine Kante erzeugt wird.
float p = .5;

// Speicher für die X- und Y-Kanten.
int[] stitchesX, stitchesY;

// Verwendete Farben (nur Index 1 und 2)
color[] colors = new color[]{
  color(241, 124, 55),
  color(62, 156, 191)
};

void settings() {
  size(COLUMNS*TILE_SIZE, ROWS*TILE_SIZE);
}

void setup() {
  stroke(0);
  fill(0);
  textAlign(CENTER);

  generatePattern();

  noLoop();
}

/*
Erzeugt ein zufälliges Hitomezashi Pattern.
 */
void generatePattern() {
  stitchesX = new int[COLUMNS];
  for ( int i = 0; i < COLUMNS; i++ ) {
    stitchesX[i] = random(0, 1) < p ? 1 : 0;
  }
  stitchesY = new int[COLUMNS];
  for ( int j = 0; j < ROWS; j++ ) {
    stitchesY[j] = random(0, 1) < p ? 1 : 0;
  }
}

/*
Prüft, ob eine Zelle in einer Richtung eine Kante besitzt.
 */
boolean hasStitch( int i, int j, char dir ) {
  switch( dir ) {
  case 'u':
    return (j > 0 && (stitchesY[j-1]+i)%2 == 1);
  case 'd':
    return (j >= 0 && (stitchesY[j]+i)%2 == 1);
  case 'l':
    return (i > 0 && (stitchesX[i-1]+j)%2 == 1);
  case 'r':
    return (i >= 0 && (stitchesX[i]+j)%2 == 1);
  default:
    return false;
  }
}

void draw() {
  background(200);

  int clr = round(random(0, 1)), clr2 = clr;
  noStroke();
  for ( int i = 0; i < COLUMNS; i++ ) {
    if ( hasStitch(i, 0, 'l') ) {
      clr = (clr2+1)%2;
      clr2 = clr;
    } else {
      clr = clr2;
    }

    for ( int j = 0; j < ROWS; j++ ) {
      fill(colors[clr]);
      rect(i*TILE_SIZE, j*TILE_SIZE, TILE_SIZE, TILE_SIZE);

      if ( hasStitch(i, j, 'd') ) {
        clr = (clr+1)%2;
      }
    }
  }

  stroke(0);
  strokeWeight(3);
  for ( int i = 0; i < COLUMNS; i++ ) {
    for ( int j = 0; j < ROWS; j++ ) {
      boolean stitchX = (stitchesX[i]+j)%2 == 1;
      if ( i < COLUMNS-1 && stitchX ) {
        line((i+1)*TILE_SIZE, j*TILE_SIZE, (i+1)*TILE_SIZE, (j+1)*TILE_SIZE);
      }

      boolean stitchY = (stitchesY[j]+i)%2 == 1;
      if ( j < ROWS-1 && stitchY ) {
        line(i*TILE_SIZE, (j+1)*TILE_SIZE, (i+1)*TILE_SIZE, (j+1)*TILE_SIZE);
      }
    }
  }
}

void mousePressed() {
  generatePattern();
  redraw();
}
