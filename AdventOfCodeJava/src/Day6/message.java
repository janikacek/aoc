package Day6;

import Methods.File;

import java.util.*;

/**
 * Created by Jana on 08.12.2016.
 */
public class message {
    public static final int MESSAGE_LENGTH = 8;
    public static final char PLACEHOLDER = '?';

    public static void main(String[] args) {
        List<String> input = File.readFile("message_input.txt");
        char[] chars = new char[MESSAGE_LENGTH];
        Arrays.fill(chars, PLACEHOLDER);
        StringBuffer message1 = new StringBuffer(new String(chars));
        StringBuffer message2 = new StringBuffer(new String(chars));
        System.out.println("empty message: " + message1);
        System.out.println("empty message: " + message2);

        List<Map<String, Integer>> counts = new LinkedList<>();
        for (int i = 0; i < MESSAGE_LENGTH; i++) {
            counts.add(new TreeMap<>());
        }

        for (String line : input) {
            for (int i = 0; i < MESSAGE_LENGTH; i++) {
                String letter = line.charAt(i) + "";
                if (!counts.get(i).containsKey(letter)) {
                    counts.get(i).put(letter, 1);
                } else {
                    counts.get(i).put(letter, counts.get(i).get(letter) + 1);
                }
            }
        }

        for (int i = 0; i < MESSAGE_LENGTH; i++) {
            int max = 0;
            int min = Integer.MAX_VALUE;
            String letterWithMaxCount = "";
            String letterWithMinCount = "";
            for (Map.Entry<String, Integer> entry : counts.get(i).entrySet()) {
                String letter = entry.getKey();
                int count = entry.getValue();
                if (max < count) {
                    max = count;
                    letterWithMaxCount = letter;
                }
                if (min > count) {
                    min = count;
                    letterWithMinCount = letter;
                }
            }
            message1.replace(i, i+1, letterWithMaxCount);
            message2.replace(i, i+1, letterWithMinCount);
        }

        System.out.println("corrected mesage: " + message1);
        System.out.println("corrected mesage: " + message2);
    }

}
