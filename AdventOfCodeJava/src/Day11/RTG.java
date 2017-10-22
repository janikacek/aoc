package Day11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jana on 15.12.2016.
 */
public class RTG {
//    The first floor contains a thulium generator, a thulium-compatible microchip, a plutonium generator, and a strontium generator.
//    The second floor contains a plutonium-compatible microchip and a strontium-compatible microchip.
//    The third floor contains a promethium generator, a promethium-compatible microchip, a ruthenium generator, and a ruthenium-compatible microchip.
//    The fourth floor contains nothing relevant.

    private static final int CHIP_HIGH = 61;

    public static void main(String[] args) {
        String[] initial = {"F4 . .. .. .. .. .. .. .. .. .. ..",
                            "F3 . .. .. .. .. .. .. MG MM RG RM ",
                            "F2 . .. .. .. PM .. SM .. .. .. ..",
                            "F1 E TG TM PG .. SG .. .. .. .. .."};

        Map<String, List<String>> floors = new HashMap<>();
        List<String> f1 = new LinkedList<>();
        List<String> f2 = new LinkedList<>();
        List<String> f3 = new LinkedList<>();
        List<String> f4 = new LinkedList<>();
        f1.add("TG"); f1.add("TM"); f1.add("PG"); f1.add("SG");
        f2.add("PM"); f2.add("SM");
        f3.add("MG"); f3.add("MM"); f3.add("RG"); f3.add("RM");
        floors.put("f1", f1); floors.put("f2", f2); floors.put("f3", f3); floors.put("f4", f4);
        String elevator = "f1";
        int steps = 0;

        List<List<String>> possibleMoves = getPossibleMoves(elevator, floors);



    }

    private static List<List<String>> getPossibleMoves(String elevator, Map<String, List<String>> floors) {
        List<String> elements = floors.get(elevator);
        List<String> move = new LinkedList<>();
        List<List<String>> possibleMoves =  new LinkedList<>();
        String goToFloor;
        List<String> nextElements = new LinkedList<>();

        if (elevator.equals("f1")) {
            goToFloor = "f2";
            nextElements = floors.get(goToFloor);

        } else if (elevator.equals("f2")) {

        } else if (elevator.equals("f3")) {

        } else if (elevator.equals("f4")) {

        }

        return possibleMoves;
    }
}
