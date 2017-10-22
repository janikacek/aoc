package Day10;

import Methods.File;

import java.util.*;

/**
 * Created by Jana on 14.12.2016.
 */
public class robots {
    private static final int CHIP_LOW = 17;
    private static final int CHIP_HIGH = 61;

    public static void main(String[] args) throws Exception{
        List<String> input = File.readFile("robots_input.txt");
        Map<Integer, int[]> chipsByBots = new TreeMap<>();
        Map<Integer, String[]> instructionsByBots = new TreeMap<>();
        Map<Integer, Integer> outputs = new TreeMap<>();

        for (String line : input) {
            if (line.startsWith("value")) {
                String[] words = line.split(" ");
                int chip = Integer.parseInt(words[1]);
                int bot = Integer.parseInt(words[5]);
                saveChipToBot(chipsByBots, bot, chip);
            } else if (line.startsWith("bot")) {
                parseInstructions(instructionsByBots, line);
            }
        }

        int fromBot = findBotWith2Chips(chipsByBots);;
        int[] chipsForBot;
        int lowChip;
        int highChip;

        int searchedBot = -1;
        int searchedBotLowChip = -1;
        int searchedBotHighChip = -1;

        do {

            chipsForBot = chipsByBots.get(fromBot);
            lowChip = chipsForBot[0];
            highChip = chipsForBot[1];

            if (lowChip == CHIP_LOW && highChip == CHIP_HIGH) {
                searchedBot = fromBot;
                searchedBotLowChip = lowChip;
                searchedBotHighChip = highChip;
            }

            String[] instructionForBot = instructionsByBots.get(fromBot);

            for (int i = 0; i < instructionForBot.length; i++){
                if (instructionForBot[i].startsWith("bot")) {
                    int toBot = Integer.parseInt(instructionForBot[i].substring(3));
                    saveChipToBot(chipsByBots, toBot, chipsForBot[i]);
                } else if (instructionForBot[i].startsWith("output")){
                    int toOutput = Integer.parseInt(instructionForBot[i].substring(6));
                    saveToOutput(outputs, toOutput, chipsForBot[i]);

                }
            }
            clearBot(chipsByBots, fromBot);

            System.out.println("bot: " + fromBot + ", lower chip: " + lowChip + ", higher chip: " + highChip);
            System.out.println("instruction: " + Arrays.toString(instructionForBot));

            fromBot = findBotWith2Chips(chipsByBots);
        } while (fromBot > -1);

        System.out.println("bot: " + searchedBot + ", lower chip: " + searchedBotLowChip + ", higher chip: " + searchedBotHighChip);
        System.out.println("out0, 1, 2: " + outputs.get(0) * outputs.get(1) * outputs.get(2));

//        for (Map.Entry<Integer, String[]> entry : instructionsByBots.entrySet()) {
//            System.out.print(entry.getKey());
//            System.out.print(" : ");
//            System.out.println(Arrays.toString(entry.getValue()));
//        }

//        for (Map.Entry<Integer, int[]> entry : chipsByBots.entrySet()) {
//            System.out.print(entry.getKey());
//            System.out.print(" : ");
//            System.out.println(Arrays.toString(entry.getValue()));
//        }

    }

    private static void saveToOutput(Map<Integer, Integer> map, int output, int chip) {
        if (!map.containsKey(output)) {
            map.put(output, chip);
        } else {
            map.put(output, map.get(output) + chip);
        }
    }

    private static void clearBot(Map<Integer, int[]> chipsByBots, int fromBot) {
        int[] empty = new int[2];
        chipsByBots.put(fromBot, empty);
    }

    private static int findBotWith2Chips(Map<Integer, int[]> chipsByBots) {
        for (Map.Entry<Integer, int[]> entry : chipsByBots.entrySet()) {
            if (entry.getValue()[0] > 0 && entry.getValue()[1] > 0) {
                return entry.getKey();
            }
        }
        return -1;
    }

    // bot number (key) has two chips (value), first number is lower, second number higher
    private static void saveChipToBot(Map<Integer, int[]> map, int bot, int chip) {
        if (!map.containsKey(bot)) {
            int[] chips = new int[2];
            chips[0] = chip;
            map.put(bot, chips);
        } else {
            int[] chips = map.get(bot);
            if (chips[0] < chip) {
                chips[1] = chip;
            } else {
                int tmp = chips[0];
                chips[0] = chip;
                chips[1] = tmp;
            }
            map.put(bot, chips);
        }
    }

    // bot number (key) pass lower chip to value[0], higher chip to value[1]
    private static void parseInstructions(Map<Integer, String[]> map, String line) throws Exception{
        String[] words = line.split(" ");
        int bot = Integer.parseInt(words[1]);
        String low = words[5] + words[6];
        String high = words[10] + words[11];

        if( map.containsKey(bot)) {
            throw new Exception();
        }

        String[] to = {low, high};
        map.put(bot, to);
    }
}
