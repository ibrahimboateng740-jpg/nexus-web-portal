package com.nexus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/social")
    public String social() {
        return "social";
    }

    @GetMapping("/privacy")
    public String privacy() {
        return "privacy";
    }
    
    @GetMapping("/news")
    public String news() {
        return "news";
    }
}
