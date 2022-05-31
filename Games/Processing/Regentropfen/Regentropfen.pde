PImage tropfen;
PImage eimer;

float speed = 3;
float x1 = 0, y1 = 0;
float x2 = 0, y2 = 0;
float x3 = 0, y3 = 0;
float x4 = 0, y4 = 0;
float x5 = 0, y5 = 0;

int catched = 0, dropped = 0;

void setup() {
  size(400, 600);
  
  eimer = loadImage("bucket_02.png");
  tropfen = loadImage("droplet.png");
  
  float d = 90;
  x1 = random(30, 350);
  y1 = 10;
  x2 = random(30, 350);
  y2 = y1-d-random(10,50);
  x3 = random(30, 350);
  y3 = y2-d-random(10,50);
  x4 = random(30, 350);
  y4 = y3-d-random(10,50);
  x5 = random(30, 350);
  y5 = y4-d-random(10,50);
}

void draw() {
  background(0,150,255);
  drawFloor();
  
  image(eimer, mouseX-25, 530, 50, 50);
  
  image(tropfen, x1, y1, 21.35, 30);
  image(tropfen, x2, y2, 21.35, 30);
  image(tropfen, x3, y3, 21.35, 30);
  image(tropfen, x4, y4, 21.35, 30);
  image(tropfen, x5, y5, 21.35, 30);
  drawClouds();
  
  textSize(66);
  textAlign(LEFT);
  fill(0, 144, 81);
  text("" + catched, 10, 100);
  textAlign(RIGHT);
  fill(255, 126, 121);
  text("" + dropped, 390, 100);
  
  
  update();
}

void drawFloor() {
  fill(0,143,0);
  noStroke();
  rect(0,580, 400,600);
}

void drawClouds() {
  noStroke();
  for( int  i = 10; i < 420; i+= 20 ) {
    int factor = (floor(i/20)%3+1);
    fill(230+factor*8);
    ellipse(i, 20, 40+(factor*10), 50+(factor*5));
  }
}

void update() {
  
  y1 += speed;
  y2 += speed;
  y3 += speed;
  y4 += speed;
  y5 += speed;
  
  if( eimerHit(x1,y1) ) {
    x1 = random(30, 350);
    y1 = 10;
    catched += 1;
  } else if( y1 > height ) {
    x1 = random(30, 350);
    y1 = 10;
    dropped += 1;
  }
  if( eimerHit(x2,y2) ) {
    x2 = random(30, 350);
    y2 = 10;
    catched += 1;
  } else if( y2 > height ) {
    x2 = random(30, 350);
    y2 = 10;
    dropped += 1;
  }
  if( eimerHit(x3,y3) ) {
    x3 = random(30, 350);
    y3 = 10;
    catched += 1;
  } else if( y3 > height ) {
    x3 = random(30, 350);
    y3 = 10;
    dropped += 1;
  }
  if( eimerHit(x4,y4) ) {
    x4 = random(30, 350);
    y4 = 10;
    catched += 1;
  } else if( y4 > height ) {
    x4 = random(30, 350);
    y4 = 10;
    dropped += 1;
  }
  if( eimerHit(x5,y5) ) {
    x5 = random(30, 350);
    y5 = 10;
    catched += 1;
  } else if( y5 > height ) {
    x5 = random(30, 350);
    y5 = 10;
    dropped += 1;
  }
  
}

boolean eimerHit( float x, float y ) {
  return (x+10.675<mouseX+25) && (x+10.675>mouseX-25) && (y+30>545) && (y+30<580);
}
