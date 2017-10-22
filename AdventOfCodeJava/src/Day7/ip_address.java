package Day7;

import Methods.File;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jana on 09.12.2016.
 */
public class ip_address {
    private static final String OPENING_SIGN = "[";
    private static final String CLOSING_SIGN = "]";

    public static void main(String[] args) {
        List<String> input = File.readFile("ipaddress_input.txt");
        int sumOfAddressesTLS = 0; // ip addresses supporting TLS
        int sumOfAddressesSSL = 0; // ip addresses supporting SSL

        for (String line : input) {
//            System.out.println(line);
            if (supportsTLS(line)) {
//                System.out.println("TLS");
                sumOfAddressesTLS++;
            }
            if (supportsSSL(line)) {
//                System.out.println("TLS");
                sumOfAddressesSSL++;
            }
        }
        System.out.println("sum of ip addresses supporting TLS: " + sumOfAddressesTLS);
        System.out.println("sum of ip addresses supporting SSL: " + sumOfAddressesSSL);
    }

    private static boolean supportsSSL(String line) {
        List<List<String>> list = getStringsOutsideAndInside(OPENING_SIGN,CLOSING_SIGN, line);
        List<String> outside = list.get(0);
        List<String> inside = list.get(1);
        List<String> abasAll = new LinkedList<>();
        List<String> babsAll = new LinkedList<>();

        for (String str : outside) {
            abasAll.addAll(getABAs(str));
        }
        for (String str : inside) {
            babsAll.addAll(getABAs(str));
        }
        for (String aba : abasAll) {
            for (String bab : babsAll) {
                if (abaCorrespondsToBab(aba, bab)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean abaCorrespondsToBab(String aba, String bab) {
        return (aba.charAt(0) == bab.charAt(1) && aba.charAt(1) == bab.charAt(0));
    }

    private static List<String> getABAs(String str) {
        List<String> abas = new LinkedList<>();
        for (int i = 0; i <= str.length() - 3; i++) {
            if (isABA(str.substring(i,i + 3))) {
                abas.add(str.substring(i,i + 3));
            }
        }
       return abas;
    }

    private static boolean isABA(String str) {
        if (str.length() != 3) return false;
        String first = str.charAt(0) + "";
        String second = str.charAt(1) + "";
        String third = str.charAt(2) + "";
        return (!first.equals(second) && first.equals(third));
    }

    private static boolean supportsTLS(String line) {
        List<List<String>> list = getStringsOutsideAndInside(OPENING_SIGN,CLOSING_SIGN, line);
        List<String> outside = list.get(0);
        List<String> inside = list.get(1);
        boolean abbaOutside = false;
        boolean abbaInside = false;

        for (String str : outside) {
            if (hasABBA(str)) {
                abbaOutside = true;
                break;
            }
        }
        for (String str : inside) {
            if (hasABBA(str)) {
                abbaInside = true;
                break;
            }
        }

        return abbaOutside && !abbaInside;
    }

    private static boolean hasABBA(final String str) {
        int lenght = str.length();
        if(lenght < 4) return false;
        for (int i = 0; i <= lenght - 4; i++) {
            if (isABBA(str.substring(i,i + 4))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isABBA(final String str) {
        if (str.length() != 4) return false;
        String first = str.charAt(0) + "";
        String second = str.charAt(1) + "";
        String third = str.charAt(2) + "";
        String fourth = str.charAt(3) + "";
        return (!first.equals(second) && second.equals(third) && first.equals(fourth));
    }

    private static List<List<String>> getStringsOutsideAndInside(String openingSign, String closingSign, String line) {
        List<List<String>> list = new LinkedList<>();
        List<String> stringsOutside = new LinkedList<>();
        List<String> stringsInside = new LinkedList<>();
        StringBuffer sbOut = new StringBuffer();
        StringBuffer sbIn = new StringBuffer();
        boolean outside = true;

        for (int i = 0; i < line.length(); i++) {
            String sign = line.charAt(i) + "";
//            System.out.println(sign);
            if (sign.equals(openingSign)) {
                outside = false;
                if (sbOut.length() != 0) {
                    stringsOutside.add(sbOut.toString());
                    sbOut.delete(0, sbOut.length());
                }
            } else if (sign.equals(closingSign)) {
                outside = true;
                if (sbIn.length() != 0) {
                    stringsInside.add(sbIn.toString());
                    sbIn.delete(0, sbIn.length());
                }
            } else {
//                System.out.println(outside);
                if (outside) {
                    sbOut.append(sign);
                } else {
                    sbIn.append(sign);
                }
            }
        }
        if (sbOut.length() != 0 && outside) {
            stringsOutside.add(sbOut.toString());
        }
//        System.out.println("out: " + stringsOutside);
//        System.out.println("in: " + stringsInside);
        list.add(stringsOutside);
        list.add(stringsInside);
        return list;
    }
}
