package com.thenexus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    // 1. Home Page
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // 2. Social Hub (Infinite Feed)
    @GetMapping("/social")
    public String social() {
        return "social";
    }

    // 3. News Portal (API Feed)
    @GetMapping("/news")
    public String news() {
        return "news";
    }

    // 4. Login Page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 5. Help Page (Stops the Help Button Whitelabel)
    @GetMapping("/help")
    public String help() {
        return "help";
    }

    // 6. INTERNAL SEARCH (Stops the Google Redirect)
    @GetMapping("/search")
    public String nexusSearch(@RequestParam(name = "q", required = false) String query, Model model) {
        if (query == null || query.isEmpty()) {
            return "redirect:/"; 
        }
        model.addAttribute("userQuery", query);
        return "search-results";
    }
}
