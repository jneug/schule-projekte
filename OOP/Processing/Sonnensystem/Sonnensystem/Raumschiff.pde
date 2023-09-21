class Raumschiff {
  private PVector pos, vel;

  public Raumschiff(PVector pPos, PVector pVel) {
    pos = pPos;
    vel = pVel;
  }

  public void setVeloctiy( PVector pVel ) {
    vel = pVel;
  }

  public void update() {
    pos.add(vel);
  }
}
