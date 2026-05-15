package com.thenexus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

@Controller
public class WebController {

    // 1. HOME HUB
    @GetMapping("/")
    public String home(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "index";
    }

    // 2. SEARCH ENGINE MAPPING (Fixes the 404 when searching)
    @GetMapping("/search")
    public String nexusSearch(@RequestParam(name = "q", required = false) String query, Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        // If the query is empty, just go home
        if (query == null || query.trim().isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("userQuery", query);
        return "search-results"; // Ensure search-results.html exists in templates!
    }

    // 3. NEWS FEED
    @GetMapping("/news")
    public String news(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "news";
    }

    // 4. SOCIAL HUB
    @GetMapping("/social")
    public String social(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "social";
    }

    // 5. PODCAST HQ
    @GetMapping("/podcasts")
    public String podcasts(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "podcasts";
    }

    // 6. SYSTEM HELP
    @GetMapping("/help")
    public String help(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "help";
    }

    // 7. LOGIN
    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) return "redirect:/"; 
        return "login";
    }
}
