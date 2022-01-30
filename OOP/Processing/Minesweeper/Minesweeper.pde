int w = 15;
int h = 15;
float size;
Field[][] fields;

void setup() {
	size(600, 600);

  size = width/w;

	int bombs = (int) random((w*h)*.06, (w*h)*.12);

	fields = new Field[w][h];
	for( int i = 0; i < w; i++ ) {
		for( int j = 0; j < h; j++ ) {
			boolean hasBomb = round(random(1)) == 1;
      if( bombs > 0 && hasBomb ) {
			  fields[i][j] = new Field(i, j, size, hasBomb);
        bombs--;
      }
		}
	}
	for( int i = 0; i < w; i++ ) {
		for( int j = 0; j < h; j++ ) {
			int n = 0;
			for( int k = 0; k < 9; k++ ) {
				int dx = i + (-1 + k%3);
				int dy = j + (-1 + (int) k/3);
				if( k != 4 && dx >= 0 && dx < w && dy >= 0 && dy < h ) {
					if( fields[dx][dy].hasBomb() ) {
						n++;
					}
				}
			}
			fields[i][j].setNumber(n);
		}
	}

}

void draw() {
  background(#dfdcb9);

	for( int i = 0; i < w; i++ ) {
		for( int j = 0; j < h; j++ ) {
			fields[i][j].draw();
		}
	}
}

void mousePressed() {
	int mx = (int) (mouseX / size);
	int my = (int) (mouseY / size);

	fields[mx][my].click();
}
