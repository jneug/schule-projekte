class Muster3 {
 
 
  public void draw(int size, int colorBlack, int colorWhite ) {
    for( int i = 0; i*size < width; i++ ) {
      for( int j = 0; j*size < height; j++ ) {
        if( (i+j)%2 == 0 ) {
          fill(colorBlack);
        } else {
          fill(colorWhite);
        }
        noStroke();
        square(i*size,j*size,size);
      }
    }
  }
  
}
