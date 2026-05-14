package com.thenexus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
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

    // This handles the NexusSearch request
    @GetMapping("/search")
    public String nexusSearch(@RequestParam(name = "q", required = false) String query, Model model) {
        if (query == null || query.isEmpty()) {
            return "redirect:/"; // Go home if search is empty
        }
        model.addAttribute("userQuery", query);
        return "search-results";
    }
}
