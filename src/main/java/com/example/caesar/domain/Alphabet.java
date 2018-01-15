package com.example.caesar.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Alphabet implements Serializable {


    private static final long serialVersionUID = 1292824767348769583L;
    @Id
    @SequenceGenerator(name = "alphabet_generator", sequenceName = "alphabet_generator", initialValue = 100)
    @GeneratedValue(generator = "alphabet_generator")
    private Long id;


    @Column(nullable = false)
    private String locale;

    @Column(nullable = false)
    private String uppercase;

    @Column(nullable = false)
    private String lowercase;

    public Long getId() {
        return id;
    }

    public String getLocale() {
        return locale;
    }

    public String getUppercase() {
        return uppercase;
    }

    public String getLowercase() {
        return lowercase;
    }
}

