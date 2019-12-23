/*
    Aufgabe 2) Zweidimensionale Arrays und Rekursion - Sudoku
*/

import java.util.Arrays;

public class Aufgabe2 {
    
    private static final int sSize = 9; //sudoku field size ==> 9x9
    private static final int subSize = 3; //sudoku subfield size ==> 3x3
    
    private static int[][] readArrayFromFile(String filename) {
        int[][] returnArray = new int[sSize][sSize];
        In fileReader = new In(filename);
        for (int i =0;i<returnArray.length;i++){
            String[] line = fileReader.readLine().split(";");
            for (int j = 0; j<line.length;j++){
                returnArray[i][j] = Integer.parseInt(line[j]);
            }
        }
        return returnArray;
    }
    
    private static boolean solveSudoku(int[][] array, int idx) {
        if (idx > (sSize * sSize - 1)) {
            return true;
        } else {
            if (array[idx / sSize][idx % sSize] == 0) {
                for (int num = 1; num <= sSize; num++) {
                    if (!isNumUsedInRow(array, num, idx / sSize) && !isNumUsedInCol(array, num, idx % sSize) && !isNumUsedInBox(array, num, idx / sSize - ((idx / sSize) % subSize), idx % sSize - ((idx % sSize) % subSize))) {
                        array[idx / sSize][idx % sSize] = num;
                        if (solveSudoku(array, idx + 1)) {
                            return true;
                        } else {
                            array[idx / sSize][idx % sSize] = 0;
                        }
                    }
                }
            } else {
                return solveSudoku(array, idx + 1);
            }
        }
        return false;
    }
    
    private static boolean isNumUsedInBox(int[][] array, int num, int row, int col) {
        boolean found = false;
        for (int i = row; i < row + subSize; i++) {
            for (int j = col; i < col + subSize; i++) {
                if (array[i][j] == num) found = true;
            }
        }
        return found;
    }
    
    private static boolean isNumUsedInRow(int[][] array, int num, int row) {
        boolean found = false;
        for (int i = 0; i < array.length; i++) {
            if (array[row][i] == num) found = true;
        }
        return found;
    }
    
    private static boolean isNumUsedInCol(int[][] array, int num, int col) {
        boolean found = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i][col] == num) found = true;
        }
        return found;
    }
    
    private static boolean isValidSudokuSolution(int[][] array) {
        boolean error = false;
        for (int i = 0; i<array.length;i++){
            for (int j = 0; j<array.length;j++){
                for (int l = 1;l<=9;l++){
                    error = isNumUsedInCol(array,l,j);
                    error = isNumUsedInRow(array,l,i);
                    if ((i+1)%subSize==0&&(j+1)%subSize==0&&(i+1)!=sSize&&(j+1)!=sSize){
                        error=isNumUsedInBox(array,l,i,j);
                    }
                }
            }
        }
        return error;
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
    
    public static void main(String[] args) {
        
        String filename = "sudoku0.csv"; //sudoku0.csv - sudoku7.csv available!
        System.out.println("Reading " + filename + " ...");
        int[][] sudokuField = readArrayFromFile(filename);
        printArray(sudokuField);
        solveSudoku(sudokuField, 0);
        
        System.out.println("Sudoku solved");
        System.out.println("Valid solution: " + isValidSudokuSolution(sudokuField));
        printArray(sudokuField);
    }
}


