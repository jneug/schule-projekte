float y = 0;

void setup() {
  size(800, 600);
}

void draw() {
  background(255);
  
  stroke(#000000);
  fill(#AA0033);
  circle(width/2, (height/4)+(sin(y)*140), 5);
  
  y += PI/140;
}
