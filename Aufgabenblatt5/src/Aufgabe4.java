/*
    Aufgabe 4) Rekursion und Zeichnen mit StdDraw
*/

import java.awt.*;
import java.util.Random;

public class Aufgabe4 {
    
    private static void waterFlow(int[][] map, int row, int col, int prevValue) {
        int currentValue = map[row][col];
        if (currentValue<=prevValue){
            map[row][col]=-1;
            if (row-1!=-1){
                if(map[row-1][col]!=-1)waterFlow(map,row-1,col,currentValue);
            }
            if (row+1!=map.length){
                if (map[row+1][col]!=-1) waterFlow(map,row+1,col,currentValue);
            }
            if (col-1!=-1) {
                if (map[row][col-1]!=-1) waterFlow(map, row, col - 1, currentValue);
            }
            if (col+1!=map[0].length) {
                if (map[row][col+1]!=-1) waterFlow(map, row, col + 1, currentValue);
            }
        }
    }
    
    private static void drawMap(int[][] map) {
        double size = 450/map.length;
        for (int i = 0; i<map.length;i++){
            for (int j = 0; j<map.length;j++) {
                if (map[i][j]==-1){
                    StdDraw.setPenColor(Color.BLUE);
                } else {
                    StdDraw.setPenColor(Color.GREEN);
                }
                StdDraw.filledSquare(size/2+(i*size),size/2+(j*size),size/2);
            }
        }
    }
    
    private static void printArray(int[][] inputArray) {
        for (int y = 0; y < inputArray.length; y++) {
            for (int x = 0; x < inputArray[y].length; x++) {
                System.out.print(inputArray[y][x] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    //generates random map for testing
    private static int[][] genMap(int size) {
        int[][] myMap = new int[size][size];
        Random myRand = new Random();
        int rand;
        double min, max, factor = 0.3;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int manDistFromMiddle = Math.abs(x - size / 2) + Math.abs(y - size / 2);
                min = (size / 2 * 10 - manDistFromMiddle / 2 * 10) * (1.0 + factor);
                max = (size * 10 - manDistFromMiddle / 2 * 10) * (1.0 - factor);
                //System.out.println("min: " + min + " max: " + max);
                rand = myRand.nextInt((int) (max - min)) + (int) min;
                myMap[y][x] = rand;
            }
        }
        return myMap;
    }
    
    public static void main(String[] args) {
        int width = 450;
        int height = 450;
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, 450);
        StdDraw.setYscale(0, 450);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.0025);

        int[][] map = {{9, 5, 2, 9, 6, 11, 7, 8, 9},
                {9, 6, 3, 4, 6, 11, 1, 1, 7},
                {6, 9, 8, 5, 10, 11, 1, 1, 6},
                {9, 7, 9, 7, 9, 3, 2, 6, 5},
                {9, 12, 8, 8, 20, 8, 6, 7, 8},
                {9, 12, 8, 5, 7, 9, 5, 7, 8},
                {6, 4, 8, 4, 9, 10, 5, 4, 3},
                {5, 3, 3, 4, 11, 10, 8, 9, 9},
                {2, 2, 6, 6, 9, 10, 10, 10, 9},
        };
        
        printArray(map);
        waterFlow(map, map.length / 2, map.length / 2, Integer.MAX_VALUE);
        System.out.println();
        printArray(map);
        drawMap(map);
        StdDraw.show();
        StdDraw.pause(5000);
        StdDraw.clear();

        int size = 15;
        map = genMap(size);
        map[size / 2][size / 2] = size * 10;
        printArray(map);
        System.out.println();
        waterFlow(map, map.length / 2, map.length / 2, Integer.MAX_VALUE);
        System.out.println();
        printArray(map);
        drawMap(map);
        StdDraw.show();

    }
}

