/*
    Aufgabe 3) Rekursion - Blumenmuster mit Kreisbögen
*/
public class Aufgabe3 {
    
    private static void drawArcPattern(int x, int y, int radius) {
        StdDraw.arc(x, y+radius, radius, 0, 180);
        StdDraw.arc(x, y-radius, radius, 180, 360);
        StdDraw.arc(x+radius, y, radius, 270, 90);
        StdDraw.arc(x-radius, y, radius, 90, 270);
        if (radius>=8){
            drawArcPattern(x,y+radius,radius/2);
            drawArcPattern(x,y-radius,radius/2);
            drawArcPattern(x+radius,y,radius/2);
            drawArcPattern(x-radius,y,radius/2);
        }
    }
    
    public static void main(String[] args) {
        int width = 512;
        int height = 512;
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(-width / 2, width / 2);
        StdDraw.setYscale(-height / 2, height / 2);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.0025);
        StdDraw.setPenColor(StdDraw.BLACK);
        drawArcPattern(0,0,128);
        StdDraw.show();
    }
}


/*
1. Wie oft wird die Methode drawArcPattern aufgerufen, wenn als Abbruchbedingung die Auflösungsgrenze von radius < 8 gewählt wird?
4^0+4^1+4^2+4^3+4^4
1+4+16+64+256=341
2. Wie viele Kreisbögen werden auf der letzten Rekursionsstufe (die kleinsten Kreisbögen) gezeichnet, wenn als Abbruchbedingung die Auflösungsgrenze von radius < 8 gewählt wird?
256*4=1024
3. Wie müssen Sie Ihr Programm abändern, um das Muster in Abbildung 3b zu erzeugen?
Die aufrufe welche mit x+radius und y-radius entfernen.
 */