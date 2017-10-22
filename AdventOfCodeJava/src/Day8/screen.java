package Day8;

import Methods.File;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jana on 11.12.2016.
 */
public class screen {
    private static final String OFF = "..";
    private static final String ON = "||";

    public static void main(String[] args) {
        List<String> input = File.readFile("screen_input.txt");
        String[][] screen = new String[6][50];
        System.out.println("empty screen");
        System.out.println("screen lenght: " + screen.length);
        for (String[] row: screen) {
            Arrays.fill(row, OFF);
            System.out.println(Arrays.toString(row));
        }
        int countOfLightingPixels = 0;

        for (String line : input) {
            screen = applyInstruction(line, screen);
        }

        System.out.println("");
        System.out.println("result");
        for (String[] row: screen) {
            for (String column : row) {
                if (column.equals(ON)) {
                    countOfLightingPixels++;
                }
            }
            System.out.println(Arrays.toString(row));
        }

        System.out.println("# of lighting pixels: " + countOfLightingPixels);

    }

    private static String[][] applyInstruction(String line, String[][] screen) {
        String[] orders = line.split(" ");
//        System.out.println(Arrays.toString(orders));

        // action of order 'rect'
        if (orders.length == 2 && orders[0].equals("rect")) {
            String[] pixelsToLight = orders[1].split("x");
            for (int row = 0; row < Integer.parseInt(pixelsToLight[1]); row++) {
                for (int column = 0; column < Integer.parseInt(pixelsToLight[0]); column++) {
                    screen[row][column] = ON;
                }
            }
        }
        // action for order 'rotate'
        else if (orders.length == 5 && orders[0].equals("rotate")) {
            int rotateWhat = Integer.parseInt(orders[2].substring(2));
            int rotateBy = Integer.parseInt(orders[4]);

            // action for 'rotate column'
            if (orders[1].equals("column")) {
                int numRows = screen.length;
                String[] oldColumn = new String[numRows];
                for(int row = 0; row < numRows; row++)
                {
                    oldColumn[row] = screen[row][rotateWhat];
                }
                String[] newColumn = rotate(oldColumn, rotateBy);
                for(int row = 0; row < numRows; row++)
                {
                    screen[row][rotateWhat] = newColumn[row];
                }
            }
            // action for 'rotate row'
            else if (orders[1].equals("row")){
                screen[rotateWhat] = rotate(screen[rotateWhat], rotateBy);
            }
        }
        return screen;
    }

    private static String[] rotate(String[] array, int rotateBy) {
        int size = array.length;
        String[] rotatedArray = new String[size];
        for (int i = 0; i < rotateBy; i++) {
            rotatedArray[rotateBy - 1 - i] = array[size - 1 - i];
        }
        for (int i = 0; i < size - rotateBy; i++) {
            rotatedArray[rotateBy + i] = array[i];
        }
        return rotatedArray;
    }
}
