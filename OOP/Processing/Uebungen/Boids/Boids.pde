float COHESION_FACTOR = .5;
float SEPARATION_FACTOR = .5;
float ALIGNMENT_FACTOR = .5;
float VELOCITY_LIMIT = 3.0;
float FORCE_LIMIT = .1;
float FLOCK_RADIUS = 100;

boolean SHOW_RADIUS = false;
boolean BORDER_WARP = true;

int N_BOIDS = 200;

Boid[] boids;

public void setup() {
  size(1280, 720);

  boids = new Boid[N_BOIDS];

  for ( int i = 0; i < N_BOIDS; i++ ) {
    boids[i] = new Boid(
      new PVector(
      random(Boid.BOID_WIDTH, width - Boid.BOID_WIDTH),
      random(Boid.BOID_HEIGHT, height - Boid.BOID_HEIGHT)
      ),
      new PVector(
      random(-VELOCITY_LIMIT, VELOCITY_LIMIT),
      random(-VELOCITY_LIMIT, VELOCITY_LIMIT)
      )
      );
  }
}

public void draw() {
  for ( Boid b : boids ) {
    b.update(boids);
  }

  background(0, 125, 182);

  for ( Boid b : boids ) {
    b.draw();
  }
}
