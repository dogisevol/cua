package com.example.caesar.service;

import com.example.caesar.domain.Alphabet;
import com.example.caesar.repository.AlphabetRepository;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.BreakIterator;
import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component("questionService")
@Transactional
public class CaesarServiceImpl implements CaesarService {

    private final AlphabetRepository repository;

    public CaesarServiceImpl(AlphabetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Locale> getLocales() {
        return StreamSupport.stream(repository.getLocales().spliterator(), false)
                .map(Locale::new).collect(Collectors.toList());
    }

    @Override
    public String decode(String query, String lang) throws CaesarServiceExeption {
        return process(query, lang, false);
    }

    @Override
    public String encode(String query, String lang) throws CaesarServiceExeption {
        return process(query, lang, true);
    }


    private String process(String query, String lang, boolean encode) throws CaesarServiceExeption {
        if (lang == null) {
            lang = LocaleContextHolder.getLocale().getLanguage();
        }

        Alphabet alphabet = repository.getAlphabet(lang);
        if (alphabet == null) {
            lang = Locale.getDefault().getLanguage();
            alphabet = repository.getAlphabet(lang);
        }
        char[] upperCase = alphabet.getUppercase().toCharArray();
        char[] lowerCase = alphabet.getLowercase().toCharArray();
        StringBuilder result = new StringBuilder();
        if (alphabet != null) {
            BreakIterator bi = BreakIterator.getWordInstance(Locale.US);
            bi.setText(query);
            int firstIndex = bi.first();
            for (int lastIndex = bi.next();
                 lastIndex != BreakIterator.DONE;
                 firstIndex = lastIndex, lastIndex = bi.next()) {
                String str = query.substring(firstIndex, lastIndex);
                int end = str.length();
                for (int i = 0; i < end; i++) {
                    char ch = str.charAt(i);
                    if (Character.isAlphabetic(ch)) {
                        if (Character.isUpperCase(ch)) {
                            ch = getChar(encode, alphabet.getUppercase().indexOf(ch), upperCase, end, i, ch);
                        } else {
                            ch = getChar(encode, alphabet.getLowercase().indexOf(ch), lowerCase, end, i, ch);
                        }
                    }
                    result.append(ch);
                }
            }

        }
        return result.toString();
    }

    private char getChar(boolean encode, int index, char[] upperCase, int end, int i, char ch) throws CaesarServiceExeption {
        if (index < 0) {
            throw new CaesarServiceExeption("Wrong language");
        }
        index = encode ? index + end - i : index - end + i;
        if (index >= upperCase.length)
            index = index - upperCase.length;
        else if (index < 0) {
            index = upperCase.length + index;
        }
        ch = upperCase[index];
        return ch;
    }
}
