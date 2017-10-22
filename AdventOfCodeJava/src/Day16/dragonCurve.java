package Day16;

/**
 * Created by Jana on 17.12.2016.
 */
public class dragonCurve {
    private static final String INPUT = "00111101111101000";
    private static final int LENGTH = 35651584;

    public static void main(String[] args) {
        String data = getDragonCurve(INPUT, LENGTH);
        System.out.println(data);
        String checksum = getChecksum(data, LENGTH);
        System.out.println(checksum);

    }

    private static String getChecksum(String data, int length) {
        String cut = data.substring(0, length);
        StringBuffer checksum = new StringBuffer(cut);

        while (checksum.length() % 2 == 0) {
            checksum.delete(0, checksum.length());
            for (int i = 0; i < cut.length() - 1; i = i + 2) {
//                System.out.println(i);
                if (cut.charAt(i) == cut.charAt(i + 1))
                    checksum.append('1');
                else
                    checksum.append('0');
            }
            cut = checksum.toString();
        }
        return checksum.toString();
    }

    private static String getDragonCurve(String input, int length) {
        String newString = input;

        while (newString.length() < length) {
            String a = newString;
            StringBuffer b = new StringBuffer(a);
            b.reverse();
            String c = b.toString();
            c = c.replace('1', '2');
            c = c.replace('0', '1');
            c = c.replace('2', '0');
            newString = a + "0" + c;
        }

        return newString;
    }
}
