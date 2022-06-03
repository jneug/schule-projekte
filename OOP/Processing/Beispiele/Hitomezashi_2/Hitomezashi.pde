/*
Hitomezashi Muster
 
 Basierend auf https://aperiodical.com/2020/12/mobile-numbers-hitomezashi-stitching/
 
 Zeichnet ein zufälliges Hitomezashi Muster. Die Flächen werden
 basierend auf ihrer Größe (Anzahl zusammenhängende Felder) eingefärbt.
 */

// Konfiguration
int TILE_SIZE = 20;
int COLUMNS = 30;
int ROWS = 30;

// W'keit dafür, dass eine Kante erzeugt wird.
float p = .5;

// Speicher für die X- und Y-Kanten.
int[] stitchesX, stitchesY;
// Speicher für die Farben der Felder.
Num[][] cellColors;

// Hilfsklasse, die einen Integer speichert.
// Durch die Verwendung einer Wrapper-Klasse können
// alle Zellen, mit einer Referenz auf dasselbe Objekt
// gleichzeitig aktualisiert werden.
class Num {
  public int val;
  public Num(int pNum) {
    val = pNum;
  }
}

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
    stitchesX[i] = round(random(0, 1));
    // text(stitchesX[i]+"", i*TILE_SIZE + TILE_SIZE/2, TILE_SIZE/4);
  }
  stitchesY = new int[COLUMNS];
  for ( int j = 0; j < ROWS; j++ ) {
    stitchesY[j] = round(random(0, 1));
    // text(stitchesY[j]+"", TILE_SIZE/4, j*TILE_SIZE + TILE_SIZE/2);
  }

  cellColors = new Num[COLUMNS][ROWS];

  int clr = 0;
  for ( int i = 0; i < COLUMNS; i++ ) {
    for ( int j = 0; j < ROWS; j++ ) {
      if ( cellColors[i][j] == null ) {
        if ( i+j > 0 ) {
          clr = clr+1; //<>//
        }
        setCellColor(i, j, new Num(0));
      }
    }
  }
}

void setCellColor( int i, int j, Num clr ) {
  if ( i < 0 || j < 0 || i >= COLUMNS || j >= ROWS || cellColors[i][j] != null ) {
    return;
  }

  clr.val += 1;
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
  int max = int(COLUMNS*ROWS / 2);
  colorMode(HSB, max);
  return color(constrain(value, 0, max), max*.6, max*.9);
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
      fill(mapColor(cellColors[i][j].val));
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
