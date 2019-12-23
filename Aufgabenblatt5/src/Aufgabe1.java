/*
    Aufgabe 1) Zweidimensionale Arrays - Diverse Methoden
*/

import java.util.Arrays;

public class Aufgabe1 {
    
    private static int[][] genFilledArray(int n) {
        int[][] array = new int[n][n];
        for (int i = 0;i<n;i++){
            for (int j = 0;j<n-i;j++){
                array[i][j] = i+j+1;
            }
        }
        for (int i = 0;i<n;i++){
            for (int j = n-i; j<n;j++){
                array[i][j] = n-j+(n-i-1);
            }
        }
        return array;
    }
    
    private static int[][] extendArray(int[][] inputArray) {
        int length = 0;
        for (int i = 0; i<inputArray.length;i++){
            if (inputArray[i].length>length){
                length=inputArray[i].length;
            }
        }

        int[][] returnArray = new int[inputArray.length][length];

        for (int i = 0;i<returnArray.length;i++){
            int oldLength = inputArray[i].length;
            for (int j = 0;j<length-oldLength;j++){
                returnArray[i][j] = 0;
            }
            for (int j = length-oldLength;j<length;j++){
                returnArray[i][j]=inputArray[i][j-(length-oldLength)];
            }
        }
        return returnArray;
    }
    
    private static int[] reformatArray(int[][] inputArray) {
        int[] returnArray = new int[inputArray.length];
        for (int i = 0; i<returnArray.length;i++){
            int number = 0;
            for (int j=0;j<inputArray[i].length;j++){
                number = (int) (number+inputArray[i][j]* Math.pow(2,inputArray[i].length-j-1));
            }
            returnArray[i]=number;
        }
        return returnArray;
    }
    
    private static void printArray(int[][] inputArray) {
        if (inputArray != null) {
            for (int i = 0; i < inputArray.length; i++) {
                for (int j = 0; j < inputArray[i].length; j++) {
                    System.out.print(inputArray[i][j] + "\t");
                }
                System.out.println();
            }
        }
    }
    
    private static void printArray(int[] inputArray) {
        if (inputArray != null) {
            for (int i = 0; i < inputArray.length; i++) {
                System.out.print(inputArray[i] + "\t");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int[][] array = genFilledArray(2);
        printArray(array);
        assert (Arrays.deepEquals(array, new int[][]{{1, 2}, {2, 1}}));
        System.out.println();

        array = genFilledArray(4);
        printArray(array);
        assert (Arrays.deepEquals(array, new int[][]{{1, 2, 3, 4}, {2, 3, 4, 3}, {3, 4, 3, 2}, {4, 3, 2, 1}}));
        System.out.println();
        
        array = genFilledArray(7);
        printArray(array);
        System.out.println();

        int[][] array2 = new int[][]{{1, 1, 0}, {1}, {1, 0}, {1, 0, 0, 1}};
        int[][] array2new1 = extendArray(array2);
        printArray(array2new1);
        assert (Arrays.deepEquals(array2new1, new int[][]{{0, 1, 1, 0}, {0, 0, 0, 1}, {0, 0, 1, 0}, {1, 0, 0, 1}}));
        System.out.println();
        
        array2 = new int[][]{{0, 1, 1, 1, 1, 1}, {1, 1}, {1, 0, 0, 0}, {0, 1, 0, 1}, {0}, {1, 0, 1, 1, 0, 0, 1, 1}};
        int[][] array2new2 = extendArray(array2);
        printArray(array2new2);
        assert (Arrays.deepEquals(array2new2, new int[][]{{0, 0, 0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0}, {1, 0, 1, 1, 0, 0, 1, 1}}));
        System.out.println();
        
        array2 = new int[][]{{1, 3, 2}, {5, 1}, {6, 8, 5, 10}, {9, 4, 1, 9, 2}, {3}, {0, 11, 7, 5, 3, 2, 5}};
        int[][] array2new3 = extendArray(array2);
        printArray(array2new3);
        System.out.println();

        int[][] array3 = array2new1.clone();
        int[] array3new = reformatArray(array3);
        printArray(array3new);
        assert (Arrays.equals(array3new, new int[]{6, 1, 2, 9}));
        System.out.println();
        
        array3 = array2new2.clone();
        array3new = reformatArray(array3);
        printArray(array3new);
        assert (Arrays.equals(array3new, new int[]{31, 3, 8, 5, 0, 179}));
        System.out.println();
    }
}



