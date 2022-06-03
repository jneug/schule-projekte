
void setup() {
  size(400, 400);
  
  stroke(#000000);
  fill(#AA0033);
}

void draw() {
  background(255);
  
  line(0, height*.25, width, height*.25);
  line(0, height*.75, width, height*.75);
  
  // Bewegung abhängig von der Anzahl der gezeichneten Frames
  // Ist abhängig von frameRate()
  float x = frameCount / 20.0;
  
  // Bewegung abhängig von der Anzahl der Millisekunden, die
  // das Programm läuft. Unabhängig von frameRate()
  //float x = millis() / 200.0;
  
  circle(width*.25, (height/2)+(sin(x*2.0)*(height/4.0)), 30);
  circle(width*.5, (height/2)+(sin(x)*(height/4.0)), 30);
  circle(width*.75, (height/2)+(sin(x)*(height/2.0)), 30);
}
