float ringbreite = 10;
float pulsierRate = 5;
float ausbreitGeschwindigkeit = 10;

ArrayList<PVector> clicks = new ArrayList<PVector>();
ArrayList<Float> time = new ArrayList<Float>();

int getX() {
 return floor((mouseX/sizeb));
}
int getY() {
 return floor((mouseY/sizeb));
}

float dot(float t, float i, float x, float y) {
  //Prüfen ob aktueller Punkt in click ist
   for(int z = 0;z<clicks.size();z++){
    if(x==clicks.get(z).x && y==clicks.get(z).y)
      return -1;
  }
  
  float erg = 0;
  float anzahl = 0;
  for(int z = 0;z<clicks.size();z++){
    float d = dist(x, y, clicks.get(z).x, clicks.get(z).y);
    
    if((float(millis())/1000.0)-time.get(z)>=d/ausbreitGeschwindigkeit){
       erg+=sin(((-t*pulsierRate)+d)/ringbreite);
       anzahl++;
    }
     
  }  

  if(erg<0)
    erg*=-1;
  
  if(anzahl!=0)
    return erg/anzahl;  
   return 0;
  
  //return sin(t-sqrt(pow((x-7.5),2)+pow((y-6),2)));
  //return i%4 - y%4;
}

void mouseClicked() {
  clicks.add(new PVector(getX(), getY()));
  time.add(float(millis()) / 1000.0);
}


//---------------------------------
  //for(int z = 0;z<8;z++){
    
  //  if(z==x||z==y||z==15-x||z==15-y){
  //    return sin(t*(8-z)*(8-z)/5);
  //  }
  //}


  //print("Error");
  //return sin(t*(8-x)*(8-y)/5);
  //------------------------------------
  //float[]d  = new float[]{ dist(x, y, 0, 0), dist(x, y, 15, 15),dist(x, y, 15, 0),dist(x, y, 0, 15)};
  
  //float erg = 0;
  //for(int z = 0;z<d.length;z++){
  //  erg+=sin((t*5)+d[z])/d.length;
  //}  
  //return erg;
  //--------------------------------------------
//  ArrayList<PVector> clicks = new ArrayList<PVector>();

//int getX() {
// return floor((mouseX/20));
//}
//int getY() {
// return floor((mouseY/20));
//}

//float dot(float t, float i, float x, float y) {
  
  
  
//  float erg = 0;
//  for(int z = 0;z<clicks.size();z++){
//    erg+=sin((-t*5)+dist(x, y, clicks.get(z).x, clicks.get(z).y))/clicks.size();
//  }  
  
//  if(erg>0)
//    return erg;
//  return 0;
  
//  //return sin(t-sqrt(pow((x-7.5),2)+pow((y-6),2)));
//  //return i%4 - y%4;
//}

//void mouseClicked() {
//  clicks.add(new PVector(getX(), getY()));
//}
