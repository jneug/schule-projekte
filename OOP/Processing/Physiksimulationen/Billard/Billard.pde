PVector grav = new PVector(0, 0.06734);

Mover whiteBall;
Mover[] movers = new Mover[16];

int[][] holes = new int[6][2];

void setup() {
  size(800, 600);

  int start = 550;
  int n = 1;
  for ( int i = 1; i <= 5; i++ ) {
    for ( int j = 0; j < i; j++ ) {
      movers[n] = new Mover(start+i * Mover.SIZE, 300 - ((i-1)/2.0)*Mover.SIZE + j*Mover.SIZE);
      n += 1;
    }
  }

  movers[0] = new Mover(200, 300, #FFFFFF);
  movers[5].setColor(#000000);

  whiteBall = movers[0];

  holes = new int[][]{
    new int[]{15, 15},
    new int[]{400, 15},
    new int[]{785, 15},
    new int[]{15, 585},
    new int[]{400, 585},
    new int[]{785, 585}
  }; //<>//
} //<>//

void draw() {
  background(29, 181, 42);

  for ( int i = 0; i < movers.length; i++ ) {
    Mover mover = movers[i];
    if ( mover == null ) {
      continue;
    }

    for ( Mover mover2 : movers ) {
      if ( mover2 != null && mover != mover2 ) {
        if ( PVector.dist(mover.getPosition(), mover2.getPosition()) <= Mover.SIZE ) {
          PVector diff = PVector.sub(mover2.getPosition(), mover.getPosition());
          
          PVector force1 = calcForce(mover.getVelocity(), diff);
          PVector force2 = calcForce(mover2.getVelocity(), diff);
          
          diff.setMag((force1.mag()+force2.mag())/2.0);
          mover2.applyForce(diff);
          mover.applyForce(diff.mult(-1.0));
        }
      }
    }

    mover.update();

    PVector pos = mover.getPosition();

    if ( pos.x <= 0 || pos.x >= width ) {
      pos.x = constrain(pos.x, 0, width);
      mover.getVelocity().x *= -1.0;
    }
    if ( pos.y <= 0 || pos.y >= height ) {
      pos.y = constrain(pos.y, 0, height);
      mover.getVelocity().y *= -1.0;
    }

    for ( int[] hole : holes) {
      if ( pos.x >= hole[0]-15 && pos.x <= hole[0]+15 &&
        pos.y >= hole[1]-15 && pos.y <= hole[1]+15 ) {
        movers[i] = null;
      }
    }
  }

  if ( movers[0] == null ) {
    movers[0] = new Mover(200, 300, #FFFFFF);
    whiteBall = movers[0];
  }

  drawHoles();

  if ( mouseDown ) {
    stroke(#FF0000);
    strokeWeight(3);
    line(mouseX, mouseY, whiteBall.getPosition().x, whiteBall.getPosition().y);
    strokeWeight(1);
  }

  for ( Mover mover : movers ) {
    if ( mover != null ) {
      mover.draw();
    }
  }
}

PVector calcForce( PVector u, PVector s ) {
  float v = PVector.dot(u, s) / s.magSq();
  return s.copy().mult(v);
}

void drawHoles() {
  for ( int[] hole : holes ) {
    noStroke();
    fill(#000000);
    circle(hole[0], hole[1], 30);
  }
}

boolean mouseDown = false;

void mousePressed() {
  mouseDown = true;
}

void mouseReleased() {
  mouseDown = false;

  PVector force = whiteBall.getPosition().copy();
  PVector mouse = new PVector(mouseX, mouseY);
  force.sub(mouse).div(10.0);
  whiteBall.applyForce(force);
}

void mouseClicked2() {
  for ( Mover mover : movers ) {
    PVector force = mover.getPosition().copy();
    force.sub(new PVector(mouseX, mouseY)).setMag(10.0);
    mover.applyForce(force);
  }
}
