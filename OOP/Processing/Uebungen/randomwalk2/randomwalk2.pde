/*
Random walker 2
 
 Basierend auf https://www.redblobgames.com/x/2202-turing-patterns/#random-walks
 */
int step = 6;
int size = 8;
int count = 4;

int[] x = new int[count];
int[] y = new int[count];
int[] colors = new int[count];

int[] palette = new int[]{#b13254, #ff5349, #ff7249, #ff9248};

// required to use transparancy in background
PGraphics buffer;

void setup() {
  size(400, 400);
  background(0);
  frameRate(30);

  while (count > 0) {
    x[count-1] = int(random(100, 300));
    y[count-1] = int(random(100, 300));
    colors[count-1] = randomColor();

    count -= 1;
  }

  buffer = createGraphics(400, 400);
}

void draw() {
  buffer.beginDraw();
  buffer.noStroke();
  buffer.background(0, 5);

  for (int i = 0; i < x.length; i++) {
    buffer.fill(colors[i]);
    buffer.rect(x[i], y[i], size, size);
  }


  update();

  for (int i = 0; i < x.length; i++) {
    buffer.fill(colors[i]);
    buffer.rect(x[i], y[i], size, size);
    buffer.fill(255, 180);
    buffer.rect(x[i], y[i], size-2, size-2);
  }
  buffer.endDraw();
  image(buffer, 0, 0);
}

void update() {
  for (int i = 0; i < x.length; i++) {
    x[i] += int(random(-step, step));
    y[i] += int(random(-step, step));
    x[i] = constrain(x[i], 10, width - 10);
    y[i] = constrain(y[i], 10, height - 10);
  }
}

int randomColor() {
  return palette[int(random(palette.length))];
}
