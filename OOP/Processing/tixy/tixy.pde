/**
 * Bestimmt, wie ein Punkt gezeichnet werden soll.
 *
 * Die Methode wird für jeden Punkt auf dem Feld einmal
 * aufgerufen und muss für jeden Punkt einen Wert zwischen
 * -1.0 und 1.0 zurückgeben.
 *
 * Der Wert bestimmt die Darstellung des Punktes auf folgende Weise:
 * - Negative Werte färben den Punkt rot, positive weiß.
 * - Der Absolutbetrag legt die Größe des Punktes fest.
 *
 * Der Wert -1.0 zeichent also einen großen roten Punkt, der Wert
 * 0.5 zeichnet einen weißen Punkt mit halber Größe.
 *
 * Für das Ergebnis können optional vier Parameter einbezogen werden:
 * - t ist die vergangene Zeit seit Start in Sekunden.
 * - i ist die Nummer des Punktes, angefangen von oben links (0) 
 *     zweilenweise nach unten rechts (255).
 * - x ist die Spaltennummer des Punktes (0 bis 15).
 * - y ist die Zeilennummer des Puntkes (0 bis 15).
 *
 * Für die Berechnungen können mathematische Funktionen wie
 * sin(), cos(), pow(), sqrt(), etc. genutzt werden.
 */
float dot(float t, float i, float x, float y) {
  return sin(t-sqrt(pow((x-7.5),2)+pow((y-6),2)));
  //return x%4 - y%4;
}
