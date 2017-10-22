package Day5;

import java.util.Arrays;

/**
 * Created by Jana on 08.12.2016.
 */
public class password {
    public static final int PASSWORD_LENGTH = 8;
    public static final char PLACEHOLDER = '?';
    public static final String DOOR_ID = "abbhdwsy";

    public static void main(String[] args) {
        char[] chars = new char[PASSWORD_LENGTH];
        Arrays.fill(chars, PLACEHOLDER);
        StringBuffer firstPassword = new StringBuffer(new String(chars));
        StringBuffer secondPassword = new StringBuffer(new String(chars));
//        System.out.println(firstPassword);
        int startIndex = 0;

        while(firstPassword.toString().contains(PLACEHOLDER+"") ||
                secondPassword.toString().contains(PLACEHOLDER+"")){
            boolean found = false;
            while (!found) {
                String md5hash = MD5(DOOR_ID + Integer.toString(startIndex));
                if (startsWith5Zeros(md5hash)){
                    firstPassword = putLettersOneByOne(firstPassword, md5hash.charAt(5), PLACEHOLDER);
                    secondPassword = putLettersOnIndex(secondPassword, md5hash.charAt(5), md5hash.charAt(6), PLACEHOLDER);
                    found = true;
                }
                startIndex++;
            }
        }

        System.out.println("1. password: " + firstPassword);
        System.out.println("2. password: " + secondPassword);
    }

    private static StringBuffer putLettersOnIndex(StringBuffer password, char i, char c, char placeholder) {
        StringBuffer result = password;
        int index = Character.getNumericValue(i);
            if (index < PASSWORD_LENGTH && password.charAt(index) == placeholder) {
                result.replace(index, index+1, c+"");;
            }
        return result;
    }

    private static StringBuffer putLettersOneByOne(final StringBuffer password, final char c, final char placeholder) {
        StringBuffer result = password;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == placeholder) {
                result.replace(i, i+1, c+"");
                break;
            }
        }
        return result;
    }


    private static boolean startsWith5Zeros(String md5hash) {
        return md5hash.startsWith("00000");
    }

    private static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
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
