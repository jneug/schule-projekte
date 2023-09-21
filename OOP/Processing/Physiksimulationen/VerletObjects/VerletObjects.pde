// https://www.youtube.com/watch?v=lS_qeBy3aQI

int N_OBJECTS = 4;

VerletObject[] objects;
VerletLink[] links;

int SUBSTEPS = 8;

long lastMillis = 0L;

PVector gravity = new PVector(0, 0.6739);

PVector center = new PVector(400, 400);

int radius = 350;

int n = N_OBJECTS;

void setup() {
  size(800, 800);

  colorMode(HSB, 360, 100, 100);

  objects = new VerletObject[N_OBJECTS];
  links = new VerletLink[N_OBJECTS-1];
  
  objects[0] = new VerletObject(new PVector(width/2, 100), 15.0);
  for ( int i = 1; i < N_OBJECTS; i++ ) {
    //objects[i-1] = new VerletObject(new PVector(width*(.25*i), height/2.0));
    objects[i] = new VerletObject(new PVector(width/2 + (i*40), 100), 15.0);
    links[i-1] = new VerletLink(objects[i-1], objects[i]);
  }
}

void draw() {
  background(200);

  fill(0);
  noStroke();
  circle(center.x, center.y, radius+radius);

  float delta = (millis() - lastMillis) / 1000.0;
  float subdelta = delta / SUBSTEPS;

  for ( int s = 0; s < SUBSTEPS; s++ ) {
    solveCollisions();
    for ( int i = 1; i < n; i++ ) {
      VerletObject obj = objects[i];

      obj.applyForce(gravity);
      obj.update(subdelta);
      for( VerletLink link: links ) {
        link.update(subdelta);
      }
      applyConstraint(obj);
      obj.draw();
    }
  }
}

void applyConstraint( VerletObject obj ) {
  PVector diff = PVector.sub(obj.getPosition(), center);
  float dist = diff.mag();
  if ( dist > radius - obj.getRadius() ) {
    diff.setMag(radius - obj.getRadius());
    obj.setPosition(PVector.add(center, diff));
  }
}

void solveCollisions() {
  for ( int i = 0; i < n; i++ ) {
    VerletObject obj1 = objects[i];
    for ( int j = i+1; j < n; j++ ) {
      VerletObject obj2 = objects[j];

      PVector diff = PVector.sub(obj1.getPosition(), obj2.getPosition());
      float dist = diff.mag();
      float diameter = obj1.getRadius()+obj2.getRadius();
      if ( dist < diameter ) {
        float delta = diameter - dist;
        diff.setMag(0.5 * delta);
        obj1.getPosition().add(diff);
        obj2.getPosition().sub(diff);
      }
    }
  }
}
