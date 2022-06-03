/*
Random walker
 
 Basierend auf https://www.redblobgames.com/x/2202-turing-patterns/#random-walks
 
 Zufallslauf bei dem der Hintergund leicht Transparent übermalt wird, um einen
 Verblassenseffekt zu erzielen. Da Java-Processing auf der Hauptzeichenfläche
 keine Transparenz unterstützt, muss ein zusätzlicher Zeichenbereich (ein Puffer)
 erstellt werden.
 */
int step = 6;
int size = 8;

int x = 200;
int y = 200;

// required to use transparancy in background
PGraphics buffer;

void setup() {
  size(400, 400);
  background(0);
  frameRate(30);

  x = int(random(100, 300));
  y = int(random(100, 300));

  buffer = createGraphics(width, height);
}

void draw() {
  buffer.beginDraw();
  buffer.noStroke();
  buffer.background(0, 5);

  buffer.fill(233, 75, 0);
  buffer.rect(x, y, size, size);

  update();

  buffer.fill(233, 75, 0);
  buffer.rect(x, y, size, size);
  buffer.fill(255);
  buffer.rect(x+1, y+1, size-2, size-2);
  buffer.endDraw();
  image(buffer, 0, 0);
}

void update() {
  x += (int) random(-step, step);
  y += (int) random(-step, step);

  x = constrain(x, 10, width-10);
  y = constrain(y, 10, height-10);
}
