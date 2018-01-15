package com.example.caesar.controller;

import com.example.caesar.service.CaesarService;
import com.example.caesar.service.CaesarServiceExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Locale;

@Controller
public class CaesarController {
    @Autowired
    private CaesarService caesarService;

    @RequestMapping(value = "/locales", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Locale> getLocales() {
        return caesarService.getLocales();
    }

    @RequestMapping(value = "/encode", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String encode(@RequestParam String query, @RequestParam(required = false) String locale, HttpServletResponse response) {
        try {
            return caesarService.encode(query, locale);
        } catch (CaesarServiceExeption caesarServiceExeption) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return caesarServiceExeption.getLocalizedMessage();
        }
    }

    @RequestMapping(value = "/decode", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String decode(@RequestParam String query, @RequestParam(required = false) String locale, HttpServletResponse response) {
        try {
            return caesarService.decode(query, locale);
        } catch (CaesarServiceExeption caesarServiceExeption) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return caesarServiceExeption.getLocalizedMessage();
        }
    }
}
