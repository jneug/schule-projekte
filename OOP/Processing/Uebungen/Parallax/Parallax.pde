

PVector[][] edges;
float[] speeds = new float[]{.5, 2, 5};

void setup() {
  size(800, 600);
  
  edges = new PVector[3][];
  for( int i = 0; i < edges.length; i += 1 ) {
    int x = 0;
    edges[i] = new PVector[3 * (5+6*i)];
    
    for( int j = 0; j < edges[i].length; j += 3 ) {
      float s = (.33/(i+1));
      int w = (int) random(width*s - 50, width*s + 50);
      
      x -= random(50, 100);
      
      edges[i][j] = new PVector(x, height+10);
      edges[i][j+1] = new PVector(x+w, height*random(.5 + .15*i, .5 + .15*(i+1)));
      edges[i][j+2] = new PVector(x+w+w, height+10);
      
      x += 2*w;
    }
  }
  
  noStroke(); //<>//
}

void draw() {
  background(73, 6, 72);
  
  for( int i = 0; i < edges.length; i += 1 ) {
    switch( i ) {
      case 0: fill(40, 21, 46); break;
      case 1: fill(70, 37, 81); break;
      case 2: fill(101, 54, 117); break;
    }
    for( int j = 0; j < edges[i].length; j += 3 ) {
      triangle(edges[i][j].x, edges[i][j].y, edges[i][j+1].x, edges[i][j+1].y, edges[i][j+2].x, edges[i][j+2].y);
      
      edges[i][j].sub(speeds[i], 0);
      edges[i][j+1].sub(speeds[i], 0);
      edges[i][j+2].sub(speeds[i], 0);
    
      if( edges[i][j+2].x <= 0 ) {
        float s = (.33/(i+1));
        int w = (int) random(width*s - 50, width*s + 50);
        
        edges[i][j] = new PVector(width + random(10, 50), height+10);
        edges[i][j+1] = new PVector(edges[i][j].x+w, height*random(.5 + .15*i, .5 + .15*(i+1)));
        edges[i][i+2] = new PVector(edges[i][j].x+w+w, height+10);
      }
    }
  }
}
