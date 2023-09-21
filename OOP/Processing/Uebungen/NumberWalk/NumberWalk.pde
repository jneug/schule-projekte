import java.util.Arrays;

PVector pos, dir;

void setup() {
  size(800, 600);

  pos = new PVector(400, 300);
  dir = new PVector(0, -1);

  background(0);
  stroke(0, 0, 255);
}

void draw() {
  piWalk();
  //curveWalk();
}

void piWalk() {
  float dist = 30.0;
  float base = 6.0;
  float ang = 360.0/base;

  PVector newPos = PVector.add(pos, dir.setMag(dist));
  line(pos.x, pos.y, newPos.x, newPos.y);
  pos = newPos;

  dir.rotate(radians(next_pi()*ang));
}

// copied from https://stackoverflow.com/a/9005163
int q = 1, r = 0, t = 1, k = 1, m = 3, x = 3;
int next_pi() {
  int q2 = q, r2 = r, t2 = t, k2 = k, m2 = m, x2 = x;
  
  if( 4*q+r-t < m*t ) { 
    int digit = m;
    q = 10*q2;
    r = 10*(r2-m2*t2);
    m = ((int)((10*(3*q2+r2))/t2)) - 10*m2;
    return digit;
  } else {
    q = q2*k2;
    r = (2*q2+r2)*x2;
    t = t2*x2;
    k = k2+1;
    m = ((int)((q2*(7*k2+2)+r2*x2)/(t2*x2)));
    x = x2+2;
    
    return next_pi();
  }
}

void curveWalk() {
  float dist = 30.0;
  float t = 60.0654231173;

  PVector newPos = PVector.add(pos, dir.setMag(dist));
  line(pos.x, pos.y, newPos.x, newPos.y);
  pos = newPos;

  dir.rotate(radians(frameCount*t));
}
