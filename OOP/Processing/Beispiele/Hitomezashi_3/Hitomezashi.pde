/*
Hitomezashi Muster
 
 Basierend auf https://aperiodical.com/2020/12/mobile-numbers-hitomezashi-stitching/
  
 Zeichnet ein zufälliges Hitomezashi Muster. Die Flächen werden mit den 
 vorgegebenen Farben zufällig eingefärbt. Dabei wird nicht geprüft, ob 
 benachbarte Flächen dieselbe Farbe haben.
 */

// Konfiguration
int TILE_SIZE = 20;
int COLUMNS = 30;
int ROWS = 30;

// W'keit dafür, dass eine Kante erzeugt wird.
float p = .5;

// Speicher für die X- und Y-Kanten.
int[] stitchesX, stitchesY;
int[][] cellColors;

// Farben
color[] colors = new color[]{
  #7e2453,
  #008751,
  #ab5236,
  #c2c3c7,
  #fff2e9,
  #ff004d,
  #ffa300,
  #ffec27,
  #00e536,
  #29aeff,
  #83769c,
  #ff77a8,
  #ffcdaa,
  #76b956,
  #e9b648,
  #e88739,
  #cd4b47,
  #8a4193,
  #459bd7,
  #4c79a6,
  #f28e2c,
  #e0575a,
  #75b7b2,
  #58a050,
  #edc949,
  #b079a0,
  #fc9ea7,
  #9b755f,
  #bab0ac
};

void settings() {
  size(COLUMNS*TILE_SIZE, ROWS*TILE_SIZE);
}

void setup() {
  stroke(0);
  fill(0);
  textAlign(CENTER);

  generatePattern();

  noLoop(); //<>//
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
  
  cellColors = new int[COLUMNS][ROWS];
  for ( int i = 0; i < COLUMNS; i++ ) {
    for ( int j = 0; j < ROWS; j++ ) {
      cellColors[i][j] = -1;
    }
  }

  for ( int i = 0; i < COLUMNS; i++ ) {
    for ( int j = 0; j < ROWS; j++ ) {
      if ( cellColors[i][j] == -1 ) {
        setCellColor(i, j, int(random(0, colors.length)));
      }
    }
  }
}

void setCellColor( int i, int j, int clr ) {
  if ( i < 0 || j < 0 || i >= COLUMNS || j >= ROWS || cellColors[i][j] != -1 ) {
    return;
  }

  cellColors[i][j] = clr;

  if ( !hasStitch(i, j, 'u') ) {
    setCellColor(i, j-1, clr);
  }
  if ( !hasStitch(i, j, 'd') ) {
    setCellColor(i, j+1, clr);
  }
  if ( !hasStitch(i, j, 'l') ) {
    setCellColor(i-1, j, clr); //<>//
  }
  if ( !hasStitch(i, j, 'r') ) {
    setCellColor(i+1, j, clr);
  }
}

color mapColor( int value ) {
  return colors[value%colors.length];
}

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
  noStroke();
  for ( int i = 0; i < COLUMNS; i++ ) {
    for ( int j = 0; j < ROWS; j++ ) {
      fill(mapColor(cellColors[i][j]));
      rect(i*TILE_SIZE, j*TILE_SIZE, TILE_SIZE, TILE_SIZE);
      fill(0);
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
