class Spitze {

	private float x, size;

	public Spitze(int pX) {
		x = pX;
		size = random(10, 50);
	}

	public void draw() {
		noStroke();
		fill(33,33,33);
		triangle(x, 0, x+10, 0, x+5, size);
	}

}
