package com.thenexusweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DictionaryController {

    @GetMapping("/dictionary")
    public String showDictionaryPage() {
        // Points directly to our template file: resources/templates/dictionary.html
        return "dictionary";
    }
}
