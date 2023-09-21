int PICK_SIZE = 60;
int STROKE_WEIGHT = 5;
boolean LOOP = false; 

int max = 8;

int h = 13;

ArrayList<Toothpick> picks;

void setup() {
  size(600, 600);

  stroke(h);
  strokeWeight(STROKE_WEIGHT);
  strokeCap(SQUARE);

  if( LOOP ) {
    frameRate(1);
  } else {
    noLoop();
  }

  colorMode(HSB, 100);
  
  picks = new ArrayList<Toothpick>();
}


void draw() {
  background(0);
  
  picks.add(new Toothpick(width/2, height/2, false, color(h, 100, 100)));
  drawPicks();
  max += 1; //<>//
}

void drawPicks() {
  int i = 0;
  
  while( !picks.isEmpty() ) {
    Toothpick pick = picks.get(0);
    picks.remove(0);
    pick.draw();
    
    if( i < max ) {
      if ( pick.isAfree() ) {
        picks.add(new Toothpick(pick.getA(), !pick.vertical, color(h, 100, 100)));
      }
      if ( pick.isBfree() ) {
        picks.add(new Toothpick(pick.getB(), !pick.vertical, color(h, 100, 100)));
      }
    }
  
    i += 1;
  }
}

void mousePressed() {
  println(get(mouseX, mouseY));
}
