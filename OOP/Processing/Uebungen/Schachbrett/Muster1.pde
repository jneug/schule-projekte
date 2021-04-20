class Muster1 {
  
  private int size = 40;
 
  public void draw() {
    for( int i = 0; i*size < width; i++ ) {
      for( int j = 0; j*size < height; j++ ) {
        if( (i+j)%2 == 0 ) {
          fill(0);
        } else {
          fill(255);
        }
        noStroke();
        square(i*size,j*size,size);
      }
    }
  }
  
}
