class Muster2 {
  
 
  public void draw() {
    float h = height/15.0;
    float w = width/20.0;
    
    for( int i = 0; i*w < width; i++ ) {
      for( int j = 0; j*h < height; j++ ) {
        if( (i+j)%2 == 0 ) {
          fill(0);
        } else {
          fill(255);
        }
        noStroke();
        rect(i*w,j*h,w,h);
      }
    }
  }
  
}
