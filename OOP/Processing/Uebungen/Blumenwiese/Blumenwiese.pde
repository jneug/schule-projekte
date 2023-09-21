
float P_SAMMELN = .005;
float P_BESTAEUBEN = .2;
float P_VERWELKEN = .88;



Bienenkorb korb;

Blume[] blumen;

Biene[] bienen;


void setup() {
  size(600, 600);

  blumen = new Blume[80];
  for ( int i = 0; i < 10; i++ ) {
    blumen[i] = new Blume((int)random(10, 590), (int)random(10, 590));
  }
  
  korb = new Bienenkorb(300, 300);
  
  bienen = new Biene[8];
  for ( int i = 0; i < 8; i++ ) {
    bienen[i] = korb.abfliegen();
    bienen[i].setzeZiel(blumen[(int)random(0,8)]);
  }
}


void draw() {
  background(#11aa00);

  wasMacheIch();

  for ( int i = 0; i < blumen.length; i++ ) {
    if (blumen[i] != null) {
      blumen[i].draw();
    }
  }
  
  korb.draw();
  
  for ( int i = 0; i < bienen.length; i++ ) {
    if (bienen[i] != null) {
      bienen[i].aktion();
      bienen[i].draw();
    }
  }
}

void wasMacheIch() {
  Blume[] arr = new Blume[blumen.length];

  for ( int i = 0; i < blumen.length; i++ ) {
    if (blumen[i] != null) {
      blumen[i].altern();

      if ( blumen[i].istVerwelkt() ) {
        blumen[i] = null;
      }
    }
  }

  int j = 0;
  for ( int i = 0; i < blumen.length; i++ ) {
    if (blumen[i] != null) {
      arr[j] = blumen[i];
      j += 1;
    }
  }

  blumen = arr;
}
