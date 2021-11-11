package com.deloitte.nextgen.framework.commons.utils;

/**
 * @author nishmehta
 * @since 19/07/2021 8:25 PM
 */
public class WordUtils {

    private WordUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String pluralize(String word) {

        String lastLetter = word.substring(word.length() - 1).toLowerCase();
        String lastTwoLetters = word.substring(word.length() - 2).toLowerCase();
        if (lastLetter.equals("s") || lastLetter.equals("x") || lastLetter.equals("z") || lastTwoLetters.equals("ch") || lastTwoLetters.equals("sh")) {
            return word + "es";
        } else if (lastLetter.equals("y") && !isVowel(word.charAt(word.length() - 2))) {
            return word.substring(0, word.length() - 1) + "ies";
        } else {
            return word + "s";
        }
    }

    public static boolean isVowel(char chr) {
        return chr == 'a' || chr == 'e' || chr == 'i' || chr == 'o' || chr == 'u';
    }
}
