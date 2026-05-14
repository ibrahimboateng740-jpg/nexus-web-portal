package com.nexus; // Make sure this matches your actual package name

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        return "index"; // Looks for index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Looks for login.html
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup"; // Looks for signup.html
    }

    @GetMapping("/search")
    public String search() {
        return "search"; // Looks for search.html
    }

    @GetMapping("/social")
    public String social() {
        return "social"; // Looks for social.html - THIS FIXES THE SOCIAL ERROR
    }

    @GetMapping("/news")
    public String news() {
        return "news"; // Looks for news.html
    }
    
    @GetMapping("/weather")
    public String weather() {
        return "weather"; // Looks for weather.html
    }
}
