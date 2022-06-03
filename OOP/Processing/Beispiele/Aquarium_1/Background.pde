public class Background {

  public static final int TILE_SIZE = 64;

  private int tile_count;

  private PImage[] floor;

  private PImage water;

  public Background() {
    tile_count = (int)Math.ceil(width/TILE_SIZE);
    floor = new PImage[tile_count];

    for ( int i = 0; i < tile_count; i++ ) {

      floor[i] = loadImage("tiles/floor"+(int)random(1, 8)+".png");
    }

    water = loadImage("tiles/water.png");
  }

  public void draw() {
    for ( int i = 0; i < tile_count; i++ ) {
      for ( int j = 0; j < height; j += TILE_SIZE ) {
        image(water, i*TILE_SIZE, j);
      }
    }

    for ( int i = 0; i < tile_count; i++ ) {
      image(floor[i], i*TILE_SIZE, height-TILE_SIZE);
    }
  }
}
