package Day15;

import Methods.File;

import java.util.*;

/**
 * Created by Jana on 17.12.2016.
 */
public class discs {
    public static void main(String[] args) {
        List<String> input = File.readFile("discs_input.txt");
        Map<Integer, List<Integer>> discs = new HashMap<>();

        for (String line : input) {
            String[] words = line.split(" ");
//            System.out.println(Arrays.toString(words));
            List<Integer> numbers = new LinkedList<>();
            numbers.add(Integer.parseInt(words[3]));
            numbers.add(Integer.parseInt(words[11].substring(0, words[11].length() -1)));
            discs.put(Integer.parseInt(words[1].substring(1)), numbers);
        }

        System.out.println(discs);

        Map<Integer, Integer> go = new HashMap<>();
        //for (Map.Entry<>)

        go.put(1, 16);
        go.put(2, 1);
        go.put(3, 16);
        go.put(4, 9);
        go.put(5, 2);
        go.put(6, 4);
        go.put(7, 4);

        Map<Integer, Integer> now = new HashMap<>();

        for (int t = 0; t < 17*3*19*13*7*5*11*2; t++) {
            for (Map.Entry<Integer, List<Integer>> disc : discs.entrySet()) {
                now.put(disc.getKey(), getPositionAtTime(t, disc.getValue()));
            }
//            System.out.println(now);
            if (now.equals(go)) {
                System.out.println(t);
                break;
            }
        }
    }

    private static int getPositionAtTime(int t, List<Integer> value) {

        int numOfPositions = value.get(0);
        int startPosition = value.get(1);
        int position = (t + startPosition) % numOfPositions;

        return position;
    }
}
