package com.example.caesar.repository;

import com.example.caesar.domain.Alphabet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface AlphabetRepository extends CrudRepository<Alphabet, Long> {
    @Query(value = "SELECT a.locale FROM Alphabet a")
    Collection<String> getLocales();
    @Query(value = "SELECT a FROM Alphabet a WHERE a.locale = :lang")
    Alphabet getAlphabet(@Param("lang") String lang);
}
