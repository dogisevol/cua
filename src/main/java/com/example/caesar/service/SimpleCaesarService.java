package com.example.caesar.service;

import java.text.BreakIterator;
import java.util.Locale;

public class SimpleCaesarService  {

    private String process(String query, String lang, boolean encode) throws CaesarServiceExeption {
        BreakIterator bi = BreakIterator.getWordInstance(Locale.US);
        bi.setText(query);
        int start = bi.first();
        StringBuilder result = new StringBuilder();
        for (int end = bi.next();
             end != BreakIterator.DONE;
             start = end, end = bi.next()) {
            getWord(query.substring(start, end), result, encode);
        }
        return result.toString();
    }

    private String getWord(String str, StringBuilder result, boolean encode) {
        int end = str.length();
        for (int i = 0; i < end; i++) {
            char ch = str.charAt(i);
            if (Character.isAlphabetic(ch)) {
                char startChar = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = encode ? (char) ((int) ch + (end - i)) : (char) ((int) ch - (end - i));
                if (encode && (ch - startChar) / 26 > 0) {
                    ch = (char) ((int) ch - 26);
                } else if (!encode && (ch - startChar) < 0) {
                    ch = (char) ((int) ch + 26);
                }
            }
            result.append(ch);
        }
        return str;
    }
}
