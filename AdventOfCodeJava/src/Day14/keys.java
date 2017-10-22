package Day14;


import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Jana on 17.12.2016.
 */
public class keys {
    public static final String SALT = "zpqevtbw";

    public static void main(String[] args) {
        Map<Integer, String> triplets = new TreeMap<>();
        Map<Integer, String> fivelets = new TreeMap<>();

        int numOfKeys = 0;
        int index = 0;

        while (index < 50000) {
            String MD5hash = MD5(SALT + Integer.toString(index));
            for (int i = 0; i < 2016; i++) {
                MD5hash = MD5(MD5hash);
            }
            String trippletChar = getTripplet(MD5hash);
            String fiveletChar = getFivelet(MD5hash);

            if (trippletChar != null) {
                triplets.put(index, trippletChar);
            }

            if (fiveletChar != null) {
                fivelets.put(index, fiveletChar);
            }

            index++;
        }

        for (Map.Entry<Integer,String> triplet : triplets.entrySet()) {
            for (Map.Entry<Integer,String> fivelet : fivelets.entrySet()) {
                if (fivelet.getKey() <= triplet.getKey() + 1000 && fivelet.getKey() > triplet.getKey() &&
                        triplet.getValue().equals(fivelet.getValue()) ) {
                    numOfKeys++;
//                    fivelets.remove(fivelet.getKey());
                    if (numOfKeys == 64) {
                        System.out.println(triplet.getKey());
                    }
                    break;
                }

            }
        }

        System.out.println(numOfKeys);




//        System.out.println(getTripplet("35lkjlgdkfgjd"));
//        System.out.println(getTripplet("dfk34555skdjghdf777h"));
//        System.out.println(getTripplet("dfe98trtttdfd"));

    }

    private static String getFivelet(String md5hash) {
//        // first
//        char ch = md5hash.charAt(0);
//        if (ch == md5hash.charAt(1) && ch == md5hash.charAt(2) && ch == md5hash.charAt(3) &&
//                ch == md5hash.charAt(4) && ch != md5hash.charAt(5)) {
//            return ch + "";
//        }
//        // in the middle
//        for (int i = 1; i < md5hash.length() - 5; i++) {
//            ch = md5hash.charAt(i);
//            if (ch != md5hash.charAt(i - 1) && ch == md5hash.charAt(i + 1) && ch == md5hash.charAt(i + 2) &&
//                    ch == md5hash.charAt(i + 3) && ch == md5hash.charAt(i + 4) && ch != md5hash.charAt(i + 5))
//                return ch + "";
//        }
//        // last
//        ch = md5hash.charAt(md5hash.length() - 5);
//        if (ch != md5hash.charAt(md5hash.length() - 6) && ch == md5hash.charAt(md5hash.length() - 4) &&
//                ch == md5hash.charAt(md5hash.length() - 3) && ch == md5hash.charAt(md5hash.length() - 2) &&
//                ch == md5hash.charAt(md5hash.length() - 1))  {
//            return ch + "";
//        }
//        return null;


        char ch;
        for (int i = 0; i < md5hash.length() - 4; i++) {
            ch = md5hash.charAt(i);
            if (ch == md5hash.charAt(i + 1) && ch == md5hash.charAt(i + 2) &&
                    ch == md5hash.charAt(i + 3) && ch == md5hash.charAt(i + 4))
                return ch + "";
        }
        return null;
    }

    private static String getTripplet(String md5hash) {
//        // first
//        char ch = md5hash.charAt(0);
//        if (ch == md5hash.charAt(1) && ch == md5hash.charAt(2) && ch != md5hash.charAt(3)) {
//            return ch + "";
//        }
//        // in the middle
//        for (int i = 1; i < md5hash.length() - 3; i++) {
//            ch = md5hash.charAt(i);
//            if (ch != md5hash.charAt(i - 1) && ch == md5hash.charAt(i + 1) &&
//                    ch == md5hash.charAt(i + 2) && ch != md5hash.charAt(i + 3))
//                return ch + "";
//        }
//        // last
//        ch = md5hash.charAt(md5hash.length() - 3);
//        if (ch != md5hash.charAt(md5hash.length() - 4) && ch == md5hash.charAt(md5hash.length() - 2) &&
//                ch == md5hash.charAt(md5hash.length() - 1))  {
//            return ch + "";
//        }
//        return null;

        char ch;
        for (int i = 0; i < md5hash.length() - 2; i++) {
            ch = md5hash.charAt(i);
            if ( ch == md5hash.charAt(i + 1) && ch == md5hash.charAt(i + 2))
                return ch + "";
        }
        return null;

    }

    private static String MD5(String str) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }


}
