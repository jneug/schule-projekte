class Sun {

  public float getX() {
    //return mouseX-400;
    return 0;
  }

  public float getY() {
    //return mouseY-300;
    return 0;
  }
  
  public void draw() {
    noStroke();
    fill(240,200,0);
    ellipse(getX(),getY(),50,50);
  }

}
