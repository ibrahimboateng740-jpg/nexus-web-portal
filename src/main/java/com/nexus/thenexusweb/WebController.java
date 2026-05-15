package com.nexus.thenexusweb; // UPDATED TO MATCH THE FOLDER

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

@Controller
public class WebController {

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "index";
    }

    @GetMapping("/search")
    public String nexusSearch(@RequestParam(name = "q", required = false) String query, Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        if (query == null || query.trim().isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("userQuery", query);
        return "search-results"; 
    }

    @GetMapping("/news")
    public String news(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "news";
    }

    @GetMapping("/social")
    public String social(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "social";
    }

    @GetMapping("/podcasts")
    public String podcasts(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "podcasts";
    }

    @GetMapping("/help")
    public String help(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "help";
    }

    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) return "redirect:/"; 
        return "login";
    }
}
