class Ballon {

	private float x, y, speed, size;

	private color clr;

	public Ballon() {
		x = random(10, width-10);
		y = height-10;
		speed = random(20, 40)/10;
		size = random(20, 30);
		clr = color((int) random(50, 210), (int) random(50, 210), (int) random(50, 210));
	}

	public void draw() {
		noStroke();
		fill(clr);
		ellipse(x, y, size*.7, size);
	}

	public void update() {
		y -= speed;
	}

	public float getY() {
		return y;
	}

}
