class Muster4 {
 
  private float d = 1;
  private float n = 0;
 
  public void draw(int colorBlack, int colorWhite ) {
    float h = height/(15.0+n);
    float w = width/(20.0+n);
    
    for( int i = 0; i*w < width; i++ ) {
      for( int j = 0; j*h < height; j++ ) {
        if( (i+j)%2 == 0 ) {
          fill(colorBlack);
        } else {
          fill(colorWhite);
        }
        noStroke();
        rect(i*w,j*h,w,h);
      }
    }
  }
  
  public void update() {
    if( d > 0 ) { 
      if( n < 10 ) {
        n += d;
      } else {
        d = -1*d;
      }
    } else {
      if( n > -10 ) {
        n += d;
      } else {
        d = -1*d;
      }
    }
  }
  
}
