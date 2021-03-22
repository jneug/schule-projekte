class Sun {

  public float getX() {
    return mouseX-400;
  }

  public float getY() {
    return mouseY-300;
  }
  
  public void draw() {
    noStroke();
    fill(240,200,0);
    ellipse(getX(),getY(),50,50);
  }

}
