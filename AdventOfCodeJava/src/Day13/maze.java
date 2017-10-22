package Day13;

import java.util.Arrays;

/**
 * Created by Jana on 16.12.2016.
 */
public class maze {
    public static final int DESIGNERS_NUMBER = 1350;
    public static final int LAST_X = 100;
    public static final int LAST_Y = 100;

    public static void main(String[] args) {
        String[][] maze = new String[LAST_Y][LAST_X];

        maze[0][0] = " ";
        for (int col = 1; col < LAST_X; col++) {
            maze[0][col] = col - 1 + "";
        }

        for (int row = 1; row < LAST_X; row++) {
            maze[row][0] = row - 1 + "";
        }

        for (int row = 1; row < LAST_Y; row++) {
            for (int col = 1; col < LAST_X; col++) {
                int y = row - 1;
                int x = col - 1;
                int formula = x * x + 3 * x + 2 * x * y + y + y * y;
                int withDisNum = formula + DESIGNERS_NUMBER;
                String binary = Integer.toBinaryString(withDisNum);
                int numOfOnes = 0;
                for (int i = 0; i < binary.length(); i++) {
                    if (binary.charAt(i) == '1') numOfOnes++;
                }
                if (numOfOnes % 2 == 0) {
                    if (x > 9) {
                        maze[row][col] = ". ";
                    } else {
                        maze[row][col] = ".";
                    }
                } else {
                    if (x > 9) {
                        maze[row][col] = "# ";
                    } else {
                        maze[row][col] = "#";
                    }
                }

            }
        }


        for (String[] row : maze) {
            System.out.println(Arrays.toString(row));
        }

    }
}
