package Day12;

import Methods.File;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jana on 15.12.2016.
 */
public class assembler {

    public static void main(String[] args){
        List<String> input = File.readFile("assembler_input.txt");
        String command = "";
        String e1 = "";
        String e2 = "";

        // registers
        Map<String, Integer> registers = new HashMap<>();
        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 1);
        registers.put("d", 0);

        int iterator = 0;
        int end = input.size();
        while (iterator < end) {
            String line = input.get(iterator);
            String[] elements = line.split(" ");
            command = elements[0];
            e1 = elements[1];
            if (elements.length == 3)
                e2 = elements[2];
            else
                e2 = "";

            if (command.equals("cpy")) {
                copy(registers, e1, e2);
                iterator++;
            } else if (command.equals("inc")) {
                registers.put(e1, registers.get(e1) + 1);
                iterator++;
            } else if (command.equals("dec")) {
                registers.put(e1, registers.get(e1) - 1);
                iterator++;
            } else if (command.equals("jnz")) {
                 iterator += jump(registers, e1, e2);
            }
        }

        System.out.println("register a " + registers.get("a"));
        System.out.println("register b " + registers.get("b"));
        System.out.println("register c " + registers.get("c"));
        System.out.println("register d " + registers.get("d"));
    }

    private static int jump(Map<String, Integer> registers, String e1, String e2) {
        int check = 0;
        if (e1.equals("a") || e1.equals("b") || e1.equals("c") || e1.equals("d")) {
            check = registers.get(e1);
        } else {
            check = Integer.parseInt(e1);
        }
        if (check == 0) {
            return 1;
        } else {
            int jumpBy = Integer.parseInt(e2);
            return jumpBy;
        }
    }

    private static void copy(Map<String, Integer> registers, String e1, String e2) {
        if (e1.equals("a") || e1.equals("b") || e1.equals("c") || e1.equals("d")) {
            registers.put(e2, registers.get(e1));
        } else {
            int value = Integer.parseInt(e1);
            registers.put(e2, value);
        }
    }
}
