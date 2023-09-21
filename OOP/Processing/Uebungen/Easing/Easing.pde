// https://easings.net

void setup() {
  size(800, 600);


  background(200);
  stroke(0);
  fill(255, 0, 0);
}

void draw() {
  float t = 1.0;
  if( frameCount < frameRate ) {
    t = frameCount/frameRate;
  }
  
  fill(255,0,0, 128);
  circle(width*.25 + t*(width*.5), height*.75 - logit(t)*(height*.5), 10);
  fill(0,0,255, 128);
  circle(width*.25 + t*(width*.5), height*.75 - inOutExp(t)*(height*.5), 10);
}

float easing( float t ) {
  return inOutExp(t);
}

float inOutQuad( float t ) {
  if( t < 0.5 ) {
    return 2 * t * t;
  } else {
    return 1 - pow(-2 * t + 2, 2) / 2;
  }
}

float logit( float t ) {
  return 1 / (1 + exp(-12*t+6));  
}


float inOutExp( float t ) {
  if( t == 0 || t == 1 ) {
    return t;
  } else if( t < 0.5 ) {
    return pow(2, 20*t-10) / 2; 
  } else {
    return (2 - pow(2, -20*t+10)) / 2;
  }
}

float inOutQubic( float t ) {
  return -2*t*t*t + 3*t*t;
}


float outQuint( float t ) {
  return 1 - pow(1 - t, 5);
}

float outBounce( float t ) {
  float n1 = 7.5625;
  float d1 = 2.75;
  
  if (t < 1 / d1) {
      return n1 * t * t;
  } else if (t < 2 / d1) {
      return n1 * (t -= 1.5 / d1) * t + 0.75;
  } else if (t < 2.5 / d1) {
      return n1 * (t -= 2.25 / d1) * t + 0.9375;
  } else {
      return n1 * (t -= 2.625 / d1) * t + 0.984375;
  }
}

float inBounce( float t ) {
  return 1.0 - outBounce(t);
}

float thereAndBack( float t ) {
  if( t < 0.25 ) {
    return 8 * t * t;
  } else if ( t < 0.75 ) {
    return -8 * (t-0.5) * (t-0.5) + 1;
  } else {
    return 8 * (t-1) * (t-1);
  }
}
