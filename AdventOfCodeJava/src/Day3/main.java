package Day3;

import Methods.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jana on 03.12.2016.
 */
public class main {
    public static void main(String[] args) {
        String txtFile = "triangle_input.txt";
        List<String> input = File.readFile(txtFile);

        // solution for rows
        // --------------------------------------------------------------
        int numberAllTriangles = input.size();
        int numberPossibleTriangles = 0;

        for (String line : input) {
            int[] triangle = convertStringToArrayOfInt(line, "\\s+");
            //System.out.println(Arrays.toString(triangle));
//            data.add(arr);
            if (isTriangle(triangle)) {
                numberPossibleTriangles++;
            }
        }

        System.out.println("Triangles by row:");
        System.out.println("# of all triangles: " + numberAllTriangles);
        System.out.println("# of possible triangles: " + numberPossibleTriangles);

        // solution for columns
        // --------------------------------------------------------------
        int numberAllTriangles2 = input.size();
        int numberPossibleTriangles2 = 0;

        for(int i = 0; i < numberAllTriangles2; i = i+3) {
            int[] triangle1 = new int[3];
            int[] triangle2 = new int[3];
            int[] triangle3 = new int[3];

            for (int j = 0; j < 3; j++) {
                int[] row = convertStringToArrayOfInt(input.get(i+j), "\\s+");
                triangle1[j] = row[0];
                triangle2[j] = row[1];
                triangle3[j] = row[2];
            }

            if (isTriangle(triangle1)) numberPossibleTriangles2++;
            if (isTriangle(triangle2)) numberPossibleTriangles2++;
            if (isTriangle(triangle3)) numberPossibleTriangles2++;
        }

        System.out.println("Triangles by column:");
        System.out.println("# of all triangles: " + numberAllTriangles2);
        System.out.println("# of possible triangles: " + numberPossibleTriangles2);
    }

    public static boolean isTriangle(int[] triangle) {
        return triangle[0] + triangle[1] > triangle[2]
                && triangle[1] + triangle[2] > triangle[0]
                && triangle[2] + triangle[0] > triangle[1];
    }

    public static int[] convertStringToArrayOfInt(String line, String splitter) {
        line = line.trim();
        String[] strings = line.split(splitter);
        int[] integers = new int[3];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = Integer.parseInt(strings[i].trim());
        }

        return integers;
    }
}

