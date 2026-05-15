package com.thenexus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

@Controller
public class WebController {

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        // If the user is logged in, we send their name to the page
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "index";
    }

    @GetMapping("/social")
    public String social() {
        return "social";
    }

    @GetMapping("/news")
    public String news() {
        return "news";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/help")
    public String help() {
        return "help";
    }

    @GetMapping("/search")
    public String nexusSearch(@RequestParam(name = "q", required = false) String query, Model model) {
        if (query == null || query.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("userQuery", query);
        return "search-results";
    }
}
