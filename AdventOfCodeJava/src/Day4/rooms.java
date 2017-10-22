package Day4;

import Methods.File;

import java.util.*;

/**
 * Created by Jana on 04.12.2016.
 */
public class rooms {
    private static final int NUM_LETTERS = 26;

    public static void main(String[] args) {
        List<String> input = File.readFile("rooms_input.txt");
        int sumOfIds = 0;

        for (String line : input) {
            int id = getId(line);
            String checksum = getChecksum(line);
            String lettersWithDashes = line.substring(0, line.lastIndexOf("-"));
            String letters = lettersWithDashes.replace("-", "");

            if (isRoom(letters, checksum)) {
                sumOfIds += id;
                String roomName = decryptRoomName(lettersWithDashes, id);
                System.out.println(id);
                System.out.println(roomName);
            }
        }
        System.out.println("sum of room ids: " + sumOfIds);
    }

    private static String decryptRoomName(String lettersWithDashes, int id) {
        String roomName = "";
        for (int i = 0; i < lettersWithDashes.length(); i++) {
            char c = lettersWithDashes.charAt(i);
            char newc;
            if (c == '-') {
                newc = ' ';
            } else {
                newc = (char) ((c + id - 'a') % NUM_LETTERS + 'a');
            }
            roomName = roomName + newc;
        }
        return roomName;
    }

    private static boolean isRoom(String letters, String checksum) {
        return createChecksum(letters).equals(checksum);
    }

    private static String createChecksum(String letters) {
        String checksum = "";
        Map<String, Integer> mapKeysLetters = new TreeMap<>();

        // map with letters as keys, values are counts of letters
        for (int i = 0; i < letters.length(); i++) {
            String letter = letters.charAt(i) + "";
            if (!mapKeysLetters.containsKey(letter)) {
                mapKeysLetters.put(letter, 1);
            } else {
                mapKeysLetters.put(letter, mapKeysLetters.get(letter) + 1);
            }
        }
//        System.out.println(mapKeysLetters.toString());

        // map with counts of letters as keys, values are letters
        Map<Integer, String> mapValuesLetters = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : mapKeysLetters.entrySet()) {
            String letter = entry.getKey();
            int number = entry.getValue();
            if (!mapValuesLetters.containsKey(number)) {
                mapValuesLetters.put(number, letter);
            } else {
                mapValuesLetters.put(number, concatAlphabetically(mapValuesLetters.get(number), letter));
            }
        }
//        System.out.println(mapValuesLetters.toString());

        int size = mapValuesLetters.size();

        while (checksum.length() < 5) {
            Set<Integer> keys = mapValuesLetters.keySet();
            int maxKey = Collections.max(keys);
            checksum = checksum + mapValuesLetters.get(maxKey);
            keys.remove(maxKey);
        }
        checksum = checksum.substring(0, 5);
//        System.out.println(letters);
//        System.out.println(checksum);
        return checksum;
    }

    private static String concatAlphabetically(String a, String b) {
        int compare = a.compareTo(b);
        if (compare <= 0) {
            return a.concat(b);
        } else {
            return b.concat(a);
        }
    }



    private static int getId(String in) {
        int startOfId = in.lastIndexOf("-") + 1;
        String id = in.substring(startOfId, in.length() - 7);
//        System.out.println("id: " + id);
        return Integer.parseInt(id);
    }

    private static String getChecksum(String in) {
        int startOfChecksum = in.indexOf("[") + 1;
        String checksum = in.substring(startOfChecksum, in.length() - 1);
//        System.out.println("checksum: " + checksum);
        return checksum;
    }

}
