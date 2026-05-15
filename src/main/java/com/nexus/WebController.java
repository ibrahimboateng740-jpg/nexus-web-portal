package com.thenexus.controller; // Ensure this matches your project structure

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class WebController {

    // 1. HOME HUB (/)
    @GetMapping("/")
    public String home(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "index";
    }

    // 2. NEWS FEED (/news)
    @GetMapping("/news")
    public String news(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "news";
    }

    // 3. SOCIAL HUB (/social)
    @GetMapping("/social")
    public String social(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "social";
    }

    // 4. PODCAST HQ (/podcasts)
    @GetMapping("/podcasts")
    public String podcasts(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "podcasts";
    }

    // 5. SYSTEM HELP (/help)
    @GetMapping("/help")
    public String help(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "help";
    }

    // 6. LOGIN / DASHBOARD (/login)
    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        if (principal != null) {
            return "dashboard"; // If already logged in, go to dashboard
        }
        return "login"; // Otherwise, show login page
    }
}
