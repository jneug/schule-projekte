int V = 3;
int N = 4*V+17;
int QUIET_ZONE = 4;

int CELL_SIZE = 10;

color white = color(255), black = color(0);

byte DATA_NONE = 0, DATA_RESERVED = 1, DATA_ZERO = 2, DATA_ONE = 3;

byte[][] data = new byte[N][N];
;

void settings() {
  size((N+QUIET_ZONE+QUIET_ZONE)*CELL_SIZE, (N+QUIET_ZONE+QUIET_ZONE)*CELL_SIZE);
}

void setup() {
  noStroke();

  noLoop();
}

void draw() {
  drawFinderPattern(0, 0);
  drawFinderPattern(0, N-7);
  drawFinderPattern(N-7, 0);

  drawAlignment(V*7-1, V*7-1);

  drawTiming();

  reserveModules();

  byte[] encodedData = encodeData("Hello World! 123");
  drawDataBytes(encodedData);
  
  //drawFormat();
  //drawVersion();

  // Draw modules from data array
  background(white);
  fill(black);
  for ( int x = 0; x < data.length; x++ ) {
    for ( int y = 0; y < data[0].length; y++ ) {
      if ( data[x][y] == DATA_ONE ) {
        fill(black);
        square((x+QUIET_ZONE)*CELL_SIZE, (y+QUIET_ZONE)*CELL_SIZE, CELL_SIZE);
      } else if ( data[x][y] == DATA_ZERO ) {
        fill(white);
        square((x+QUIET_ZONE)*CELL_SIZE, (y+QUIET_ZONE)*CELL_SIZE, CELL_SIZE);
      } else if ( data[x][y] == DATA_RESERVED ) {
        fill(200, 0, 0);
        square((x+QUIET_ZONE)*CELL_SIZE, (y+QUIET_ZONE)*CELL_SIZE, CELL_SIZE);
      } else {
        fill(200);
        square((x+QUIET_ZONE)*CELL_SIZE, (y+QUIET_ZONE)*CELL_SIZE, CELL_SIZE);
      }
    }
  }
}

void drawDataBytes( byte[] dataBytes ) {
  int bit = 0;

  int max = min(dataBytes.length, 55);
  for ( int i = 0; i < max; i++ ) {
    println(toBinary(dataBytes[i]));
    for ( int b = 7; b >= 0; b-- ) {
      // Get next free module coordinates
      int x = -1;
      int y = -1;
      do {
        if ( int(bit/(N*2))%2 == 0 ) {
          x = N - 1 - (2*int(bit/(N*2)) + (bit%2));
          y = N - 1 - int((bit%(N*2))/2);
        } else {
          x = N - 1 - (2*int(bit/(N*2)) + (bit%2));
          y = int((bit%(N*2))/2);
        }
        bit += 1;
      } while ( data[x][y] != DATA_NONE );

      if ( (1 & (dataBytes[i] >> b)) == 1 ) {
        data[x][y] = DATA_ONE;
      } else {
        data[x][y] = DATA_ZERO;
      }
    }
  }
}

byte[] encodeData ( String text ) {
  byte[] text_bytes = text.getBytes();
  byte size = (byte)text_bytes.length;
  byte[] bytes = new byte[55];
  
  // Format encoding and length
  bytes[0] = (byte) (64 | (size >> 4));
  
  // Data enconding
  bytes[1] = (byte) ((size << 4) | (text_bytes[0] >> 4));
  for ( int i = 1; i < size; i++ ) {
    bytes[2+i] = (byte) ((text_bytes[i-1] << 4) | (text_bytes[i] >> 4));
  }
  
  // Ending pattern bit padding
  bytes[size+2] = (byte)(text_bytes[size-1] << 4);
  
  // Byte padding
  for ( int i = 0; i < bytes.length-size-3; i++ ) {
    if ( i%2 == 0 ) {
      bytes[size+3+i] = (byte)0xEC;
    } else {
      bytes[size+3+i] = (byte)0x11;
    }
  }

  return bytes;
}

String toBinary( byte b ) {
  return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
}

void drawFinderPattern( int x, int y ) {
  for ( int i = 0; i < 9; i++ ) {
    for ( int j = 0; j < 9; j++ ) {
      if ( (i == 0 || i == 8 || j  == 0 || j == 8)
        || ((i == 2 || i == 6) && j != 1 && j != 7)
        || ((j == 2 || j == 6) && i != 1 && i != 7) ) {
        drawModule(x-1+i, y-1+j, DATA_ZERO);
      } else {
        drawModule(x-1+i, y-1+j, DATA_ONE);
      }
    }
  }
}

void drawAlignment( int x, int y ) {
  for ( int i = 0; i < 5; i++ ) {
    for ( int j = 0; j < 5; j++ ) {
      if ( i == 0 || i == 4|| j == 0 || j == 4 || (i == 2 && j == 2) ) {
        drawModule(x+i, y+j, DATA_ONE);
      } else {
        drawModule(x+i, y+j, DATA_ZERO);
      }
    }
  }
}

void drawTiming() {
  for ( int i = 8; i <= N-8; i++ ) {
    if ( i%2 == 0 ) {
      drawModule(i, 6, DATA_ONE);
      drawModule(6, i, DATA_ONE);
    } else {
      drawModule(i, 6, DATA_ZERO);
      drawModule(6, i, DATA_ZERO);
    }
  }
}

void reserveModules() {
  for ( int i = 0; i < 8; i++ ) {
    drawModule(i, 8, DATA_RESERVED);
    drawModule(N-8+i, 8, DATA_RESERVED);
    drawModule(8, i, DATA_RESERVED);
    drawModule(8, N-8+i, DATA_RESERVED);
  }
  drawModule(8, 8, DATA_RESERVED); // ?
  for ( int i = 0; i < 18; i++ ) {
    drawModule(18+(i%3), int(i/3), DATA_RESERVED);
    drawModule(int(i/3), 18+(i%3), DATA_RESERVED);
  }
}

void drawFormat() {
  boolean[][] format = new boolean[][]{
    {true, true, false, true, false, false, false, true, false, false, true, true, true, false, true, true, false},
    {false, true, true, false, true, true, false, true, false, true, true, false, false, true, false, true, true}
  };
  for ( int i = 0; i < format[0].length; i++ ) {
    if ( format[0][i] ) {
      if ( i <= 8 ) {
        drawModule(i, 8);
      } else {
        drawModule(12+i, 8);
      }
    }
    if ( format[1][i] ) {
      if ( i <= 8 ) {
        drawModule(8, i);
      } else {
        drawModule(8, 12+i);
      }
    }
  }
}

void drawVersion() {
  char[][] version = new char[][]{
    "111100000010101010".toCharArray(),
    "101000111001110101".toCharArray()
  };
  for ( int i = 0; i < version[0].length; i++ ) {
    if ( version[0][i] == '1' ) {
      drawModule(18+(i%3), int(i/3));
    }
    if ( version[1][i] == '1' ) {
      drawModule(int(i/3), 18+(i%3));
    }
  }
}

void drawModule( int x, int y ) {
  drawModule(x, y, DATA_ONE);
}

void drawModule( int x, int y, byte type ) {
  if ( x >= 0 && y >= 0 && x < N && y < N ) {
    data[x][y] = type;
  }
}
