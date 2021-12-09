class Field {

	private int x, y;

	private float size;

	private boolean hasBomb;

	private boolean revealed = false;

	private int number;

	public Field( int pX, int pY, float pSize, boolean pHasBomb ) {
		x = pX;
		y = pY;
		size = pSize;
		hasBomb = pHasBomb;
	}

	public void draw() {
		float rx = x * size, ry = y * size;

		if( !revealed ) {
			noStroke();
			fill(180);
			square(rx, ry, size);

			stroke(33);
			strokeWeight(2);
			strokeCap(SQUARE);
			noFill();
			square(rx, ry, size);

			stroke(220);
			line(rx, ry, rx+size, ry);
			line(rx, ry, rx, ry+size);
		} else if( hasBomb ) {
			imageMode(CENTER);
			image(loadImage("images/bomb.png"), rx+0.5*size, ry+0.5*size);
		} else {
			textSize(24);
			textAlign(CENTER, CENTER);
			fill(31*number, 31*(8-number), 52);

			text(number+"", rx, ry, size, size);
		}
	}

	public void click() {
		if( !revealed ) {
			revealed = true;
		}
	}

	public boolean hasBomb() {
		return hasBomb;
	}

	public void setNumber( int pNumber ) {
		number = pNumber;
	}

}
