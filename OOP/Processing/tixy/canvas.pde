
//! ++++++++++++++++++++++++++++++++ !//
//!                                  !//
//!   Ändere diese Datei nur, wenn   !//
//!   Du genau weißt, was Du tust!   !//
//!                                  !//
//! ++++++++++++++++++++++++++++++++ !//


int w = 16;  // Anzahl Spalten mit Punkten
int h = 16;  // Anzahl Zeilen mit Punkten
float size = 20;  // Größe eines einzelnen Punktes

// Farbe für "rote" Punkte
int red = color(244, 32, 65);
// Farbe für "weiße" Punkte
int white = color(255);

// Um die Größe der Zeichenfläche dynamisch zu
// berechnen, muss settings() benutzt werden.
void settings() {
  size((int) (w*size), (int) (h*size));
}

// Setze grundlegende Einstellungen
void setup() {
  frameRate(60);
  noStroke();
}

// Zeichne die Punkte
void draw() {
  background(0);
  for( int y = 0; y < h; y += 1 ) {
    for( int x = 0; x < w; x += 1 ) {
      int i = y*w + x;
      drawDot(float(i), float(x), float(y));
    }
  }
}

// Zeichne einen Punkt
void drawDot(float i, float x, float y) {
  // Ergebnis der dot-Methode
  float result = dot(float(millis()) / 1000.0, i, x, y);
  
  // Farbe anhand des Vorzeichens setzen
  if( result < 0 ) {
    fill(red);
  } else {
    fill(white);
  }
  
  // Für den Rest ist das Vorzeichen nicht relevant
  result = abs(result);
  // Größe des Punktes (Durchmesser)
  float dSize = constrain((result * (size-1)), 0, size-1);
  // Halber Durchmesser (Radius)
  float r = size / 2.0;
  // Kreis zeichnen
  circle(r + x*size, r + y*size, dSize);
}
