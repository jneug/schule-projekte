
public class Boid {

  public static final int BOID_WIDTH = 8;

  public static final int BOID_HEIGHT = 16;


  private PVector position, velocity, acceleration;
  

  public Boid() {
    this(new PVector(), PVector.random2D().mult(random(1.5, 4.0)));
  }

  public Boid( PVector pos, PVector vel ) {
    position = pos.copy();
    velocity = vel.copy();
    acceleration = new PVector();
  }

  private PVector getPosition() {
    return position;
  }

  public PVector getVelocity() {
    return velocity;
  }

  public void draw() {
    fill(251, 241, 195);
    stroke(239, 191, 77);
    strokeWeight(2);

    pushMatrix();
    translate(position.x, position.y);
    rotate(HALF_PI + velocity.heading());
    triangle(BOID_WIDTH/-2.0, BOID_HEIGHT/2.0, 0, BOID_HEIGHT/-2.0, BOID_WIDTH/2.0, BOID_HEIGHT/2.0);
    
    if( SHOW_RADIUS ) {
      fill(251, 241, 195, 20);
      noStroke();
      circle(0, 0, FLOCK_RADIUS);
    }
    popMatrix();
  }

  public void update( Boid[] boids ) {
    position.add(velocity);

    acceleration
      .mult(0.0)
      .add(separation(boids))
      .add(cohesion(boids))
      .add(alignment(boids));
    
    limitPosition();

    velocity.add(acceleration).limit(VELOCITY_LIMIT);
  }

  private void limitPosition() {
    if( position.x < 0 ) {
      if( BORDER_WARP ) {
        position.x = width;
      } else {
        position.x = 0;
        //velocity.mult(-1);
        acceleration.add(new PVector(2*VELOCITY_LIMIT, 0));
      }
    } else if( position.x > width ) {
      if( BORDER_WARP ) {
        position.x = 0;
      } else {
        position.x = width;
        //velocity.mult(-1);
        acceleration.add(new PVector(-2*VELOCITY_LIMIT, 0));
      }
    }
    if( position.y < 0 ) {
      if( BORDER_WARP ) {
        position.y = height;
      } else {
        position.y = 0.0;
        //velocity.mult(-1);
        acceleration.add(new PVector(0, 2*VELOCITY_LIMIT));
      }
    } else if( position.y > height ) {
      if( BORDER_WARP ) {
        position.y = 0.0;
      } else {
        position.y = height;
        //velocity.mult(-1);
        acceleration.add(new PVector(0, -2*VELOCITY_LIMIT));
      }
    }
  }

  public PVector cohesion( Boid[] boids ) {
    PVector steering = new PVector();
    int n = 0;

    for( Boid b : boids ) {
      if( isInFlock(b) ) {
        steering.add(b.getPosition());
        n += 1;
      }
    }
    if( n > 0 ) {
      steering.div(n).sub(position);
      steering.setMag(VELOCITY_LIMIT).sub(velocity);
      steering.limit(FORCE_LIMIT);
      steering.mult(COHESION_FACTOR);
    }

    return steering;
  }

  public PVector separation( Boid[] boids ) {
    PVector steering = new PVector();
    int n = 0;

    for( Boid b : boids ) {
      if( isInFlock(b) ) {
        float distSq = PVector.sub(position, b.getPosition()).magSq();
        steering.add(PVector.sub(position, b.getPosition()).div(distSq));
        n += 1;
      }
    }
    if( n > 0 ) {
      steering.div(n);
      steering.setMag(VELOCITY_LIMIT).sub(velocity);
      steering.limit(FORCE_LIMIT);
      steering.mult(SEPARATION_FACTOR);
    }

    return steering;
  }

  public PVector alignment( Boid[] boids ) {
    PVector steering = new PVector();
    int n = 0;

    for( Boid b : boids ) {
      if( isInFlock(b) ) {
        //steering.add(Vector.normalize(b.getVelocity()));
        steering.add(b.getVelocity());
        n += 1;
      }
    }
    if( n > 0 ) {
      steering.div(n);
      steering.setMag(VELOCITY_LIMIT).sub(velocity);
      steering.limit(FORCE_LIMIT);
      steering.mult(ALIGNMENT_FACTOR);
    }

    return steering;
  }

  private boolean isInFlock( Boid aBoid ) {
    return aBoid != this && aBoid != null && position.dist(aBoid.getPosition()) < FLOCK_RADIUS;
  }

}
