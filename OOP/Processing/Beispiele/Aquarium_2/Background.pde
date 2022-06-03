public class Background {

  public static final int TILE_SIZE = 64;
  
  private int tile_width;
  
  private PImage[] floor, plants;
  
  private PImage water;
  
  public Background() {
    tile_width = (int)Math.ceil(width/TILE_SIZE);
    floor = new PImage[tile_width];
    plants = new PImage[tile_width];
    
    for ( int i = 0; i < tile_width; i++ ) {
      floor[i] = loadImage("tiles/floor"+(int)random(1,8)+".png");
      if( random(1,10) < 4 ) {
        plants[i] = loadImage("tiles/plant"+(int)random(1,14)+".png");
      }
    }
    
    water = loadImage("tiles/water.png");
  }
  
  public void draw() {
    for ( int i = 0; i < height; i += TILE_SIZE ) {
      for ( int j = 0; j < width; j += TILE_SIZE ) {
        image(water, j, i);
      }
    }
    
    for ( int i = 0; i < tile_width; i++ ) {
      if( plants[i] != null ) {
        image(plants[i], i*TILE_SIZE, height - (2*TILE_SIZE) + 10);
      }
      image(floor[i], i*TILE_SIZE, height - TILE_SIZE);
    }
  }

}
