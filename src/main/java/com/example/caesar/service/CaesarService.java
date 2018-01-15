package com.example.caesar.service;

import com.example.caesar.domain.Alphabet;

import java.util.Collection;
import java.util.Locale;

public interface CaesarService {

    Collection<Locale> getLocales();

    String decode(String query, String locale) throws CaesarServiceExeption;

    String encode(String query, String locale) throws CaesarServiceExeption;
}
