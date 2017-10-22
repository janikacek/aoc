package Day9;

import Methods.File;

import java.util.List;

/**
 * Created by Jana on 11.12.2016.
 */
public class compressed_file {
    private static final char OPENING_SIGN = '('; // start of marker
    private static final char CLOSING_SIGN = ')'; // end of marker
    private static final char SEPARATOR = 'x'; // separator in marker

    public static void main(String[] args) {
        List<String> input = File.readFile("compressed_input.txt");
        StringBuffer compressedString = new StringBuffer(input.get(0).replace(" ", ""));
        System.out.println("compressed string: " + compressedString);
        StringBuffer decompressedString = new StringBuffer();
        boolean skip = false;
        int numOfSignsToRepeate = 0;
        int howManyTimes = 0;

        int iterator = 0;
        while (iterator < compressedString.length()) {
            char signFromText = compressedString.charAt(iterator);

            // inside of marker
            if (signFromText == OPENING_SIGN && !skip) {
                // iterate to SEPARATOR and get how many signs behind the marker should be repeated
                skip = true;
                iterator++;
                StringBuffer toRepeate = new StringBuffer();
                char signFromMarker = compressedString.charAt(iterator);
                while (signFromMarker != SEPARATOR) {
                    toRepeate.append(signFromMarker);
                    iterator++;
                    signFromMarker = compressedString.charAt(iterator);
                }
                numOfSignsToRepeate = Integer.parseInt(toRepeate.toString());

                // iterate to end of marker (CLOSING_SIGN) and get how many times the signs should be repeated
                iterator++;
                StringBuffer repetition = new StringBuffer();
                signFromMarker = compressedString.charAt(iterator);
                while (signFromMarker != CLOSING_SIGN) {
                    repetition.append(signFromMarker);
                    iterator++;
                    signFromMarker = compressedString.charAt(iterator);
                }
                howManyTimes = Integer.parseInt(repetition.toString());
                iterator++;
            }
            // behind the marker - repetition
            else if (skip) {
                StringBuffer signsToRepeate = new StringBuffer();
                for (int i = 0; i < numOfSignsToRepeate; i++) {
                    signFromText = compressedString.charAt(iterator++);
                    signsToRepeate.append(signFromText);
                }
                for (int i = 0; i < howManyTimes; i++) {
                    decompressedString.append(signsToRepeate);
                }
                skip = false;
            }
            // outside of marker - without repetition
            else {
                decompressedString.append(signFromText);
                iterator++;
            }
        }
        System.out.println("decompressed string: " + decompressedString);
        System.out.println("length of decompressed string: " + decompressedString.length());
    }
}
