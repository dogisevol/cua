package com.example.caesar;

import com.example.caesar.service.CaesarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class CaesarCypherApp {
    @Autowired
    private CaesarService caesarService;


    public static void main(String[] args) {
        SpringApplication.run(CaesarCypherApp.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new AcceptHeaderLocaleResolver() {
            @Override
            public Locale resolveLocale(HttpServletRequest request) {
                if (StringUtils.isEmpty(request.getHeader("Accept-Language"))) {
                    return Locale.getDefault();
                }
                List<Locale.LanguageRange> list = Locale.LanguageRange.parse(request.getHeader("Accept-Language"));
                Locale locale = Locale.lookup(list, caesarService.getLocales());
                return locale;
            }
        };
    }
}
