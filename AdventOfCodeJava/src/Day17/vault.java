package Day17;

import java.util.Arrays;

/**
 * Created by Jana on 18.12.2016.
 */
public class vault {
    private static final String PASSCODE = "ihgpwlah";
    private static final int NUM_OF_KEYS = 4;
    private static final char OPEN = 'O';
    private static final char CLOSED = 'C';
    private static final char[] ALL_CLOSED = {'C', 'C', 'C', 'C'};
    private static final char[] DOOR_OPEN = {'b', 'c', 'd', 'e', 'f'};
    private static final char UP = 'U';
    private static final char DOWN = 'D';
    private static final char LEFT = 'L';
    private static final char RIGHT = 'R';
    private static final int[] START = {0, 0};
    private static final int[] END = {3, 3};

    public static void main(String[] args) {
        // 4 rows, 4 columns, 4 sides of room (either wall or door) in order: up, down, left, right
        // 'w' for wall, 'd' for door
        final char[][][] grid = new char[4][4][4];

        grid[0][0][0] = 'w';
        grid[0][0][1] = 'd';
        grid[0][0][2] = 'w';
        grid[0][0][3] = 'd';
        grid[0][1][0] = 'w';
        grid[0][1][1] = 'd';
        grid[0][1][2] = 'd';
        grid[0][1][3] = 'd';
        grid[0][2][0] = 'w';
        grid[0][2][1] = 'd';
        grid[0][2][2] = 'd';
        grid[0][2][3] = 'd';
        grid[0][3][0] = 'w';
        grid[0][3][1] = 'd';
        grid[0][3][2] = 'd';
        grid[0][3][3] = 'w';

        grid[1][0][0] = 'd';
        grid[1][0][1] = 'd';
        grid[1][0][2] = 'w';
        grid[1][0][3] = 'd';
        grid[1][1][0] = 'd';
        grid[1][1][1] = 'd';
        grid[1][1][2] = 'd';
        grid[1][1][3] = 'd';
        grid[1][2][0] = 'd';
        grid[1][2][1] = 'd';
        grid[1][2][2] = 'd';
        grid[1][2][3] = 'd';
        grid[1][3][0] = 'd';
        grid[1][3][1] = 'd';
        grid[1][3][2] = 'd';
        grid[1][3][3] = 'w';

        grid[2][0][0] = 'd';
        grid[2][0][1] = 'd';
        grid[2][0][2] = 'w';
        grid[2][0][3] = 'd';
        grid[2][1][0] = 'd';
        grid[2][1][1] = 'd';
        grid[2][1][2] = 'd';
        grid[2][1][3] = 'd';
        grid[2][2][0] = 'd';
        grid[2][2][1] = 'd';
        grid[2][2][2] = 'd';
        grid[2][2][3] = 'd';
        grid[2][3][0] = 'd';
        grid[2][3][1] = 'd';
        grid[2][3][2] = 'd';
        grid[2][3][3] = 'w';

        grid[3][0][0] = 'd';
        grid[3][0][1] = 'w';
        grid[3][0][2] = 'w';
        grid[3][0][3] = 'd';
        grid[3][1][0] = 'd';
        grid[3][1][1] = 'w';
        grid[3][1][2] = 'd';
        grid[3][1][3] = 'd';
        grid[3][2][0] = 'd';
        grid[3][2][1] = 'w';
        grid[3][2][2] = 'd';
        grid[3][2][3] = 'd';
        grid[3][3][0] = 'd';
        grid[3][3][1] = 'w';
        grid[3][3][2] = 'd';
        grid[3][3][3] = 'w';

        char[] currentRoom = grid[0][0];
        int[] currentPosition = START;
        StringBuffer path = new StringBuffer();

        // keys in order: up, down, left, right
        char[] keys = new char[NUM_OF_KEYS];
        // moves in order: up, down, left, right
        char[] moves = new char[NUM_OF_KEYS];

//        while (currentPosition != END) {
        for (int i = 0; i < 10; i++) {
            System.out.println("current position: " + Arrays.toString(currentPosition));
            keys = getKeysFromPath(PASSCODE + path);
            System.out.println("keys: " + Arrays.toString(keys));
            moves = getMoves(currentRoom, keys);
            System.out.println("moves: " + Arrays.toString(moves));
            if (moves == ALL_CLOSED) {

            }

            currentPosition = moveToNextRoom(path, currentPosition, moves);
            System.out.println("new position: " + Arrays.toString(currentPosition));
            currentRoom = grid[currentPosition[0]][currentPosition[1]];
            System.out.println("new room: " + Arrays.toString(currentRoom));
            System.out.println("----------------------------------------------------");
        }

        System.out.println("path: " + path.toString());
    }

    private static int[] moveToNextRoom(StringBuffer path, int[] currentPosition, char[] moves) {

        for (int i = 0; i < moves.length; i++) {
            if (moves[i] == OPEN) {
                if (i == 0) {
                    path.append(UP);
                    currentPosition[0] = currentPosition[0] - 1;
                    currentPosition[1] = currentPosition[1];
                } else if (i == 1) {
                    path.append(DOWN);
                    currentPosition[0] = currentPosition[0] + 1;
                    currentPosition[1] = currentPosition[1];
                } else if (i == 2) {
                    path.append(LEFT);
                    currentPosition[0] = currentPosition[0];
                    currentPosition[1] = currentPosition[1] - 1;
                } else if (i == 3) {
                    path.append(RIGHT);
                    currentPosition[0] = currentPosition[0];
                    currentPosition[1] = currentPosition[1] + 1;
                }
            }
        }
        return currentPosition;
    }

    private static char[] getMoves(char[] currentRoom, char[] keys) {
        char[] moves = new char[NUM_OF_KEYS];
        String openDoor = new String(DOOR_OPEN);
        for (int i = 0; i < NUM_OF_KEYS; i++) {
            if (openDoor.contains(keys[i] + "") && currentRoom[i] == 'd') {
                moves[i] = OPEN;
            } else {
                moves[i] = CLOSED;
            }
        }
        return moves;
    }

    private static char[] getKeysFromPath(String s) {
        String hash = MD5(s);
        char[] keys = new char[NUM_OF_KEYS];
        for (int i = 0; i < NUM_OF_KEYS; i++) {
            keys[i] = hash.charAt(i);
        }
        return keys;
    }

    private static String MD5(String str) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
