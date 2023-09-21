public class Biene {

  private PVector position;

  private PVector richtung;

  private float geschw;

  private Blume ziel;

  private Bienenkorb korb;

  private int nektar = 0;

  public Biene( PVector pPosition, float pGeschw, Bienenkorb pKorb ) {
    position = pPosition;
    richtung = PVector.random2D();
    geschw = pGeschw;
    korb = pKorb;
  }
  
  public int getNektar() {
    return nektar;
  }

  public boolean zielErreicht() {
    return (nektar > 500 && position.dist(korb.getPosition()) <= 0.5) || (nektar <= 500 && position.dist(ziel.getPosition()) <= 0.5);
  }

  public void aktion() {
    if ( zielErreicht() ) {
      if ( ziel.getNektar() > 0 && random(0, 1) >= P_SAMMELN ) {
        nektar += ziel.sammeln();
      } else {
        waehleZiel();
      }
    } else {
      fliegen();
    }
  }

public void waehleZiel() {
  Blume neuesZiel = null;

  for ( int i = 0; i < blumen.length; i++ ) {
    if (blumen[i] != null ) {
      if ( blumen[i].getAlter() >= 4 && blumen[i].getNektar() > 100 ) {
        if ( neuesZiel == null ) {
          neuesZiel = blumen[i];
        } else if ( position.dist(blumen[i].getPosition()) < position.dist(neuesZiel.getPosition()) ) {
          neuesZiel = blumen[i];
        }
      }
    }
  }

  if ( neuesZiel == null ) {
    neuesZiel = blumen[(int)random(0, blumen.length)];
  }
  
  ziel = neuesZiel;
}

  public void fliegen() {
    if ( nektar >= 500 ) {
      if ( zielErreicht() ) {
        korb.ankommen(this);
      } else {
        richtung = PVector.sub(korb.getPosition(), position);
        richtung.setMag(geschw/frameRate);
        position.add(richtung);
      }
    } else if ( !zielErreicht() ) {
      richtung = PVector.sub(ziel.getPosition(), position);
      richtung.setMag(geschw/frameRate);
      position.add(richtung);
    }
  }

  public void setzeZiel( Blume pBlume ) {
    ziel = pBlume;
  }

  public void draw() {
    fill(#ffdb29);
    stroke(0);
    strokeWeight(2);

    pushMatrix();
    translate(position.x, position.y);
    rotate(richtung.heading() + radians(90));
    triangle(0, -8, -5, 8, 5, 8);
    popMatrix();
  }
}
