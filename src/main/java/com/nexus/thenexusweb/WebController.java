package com.nexus.thenexusweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // 2. SEARCH ENGINE (/search)
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

    // 3. GLOBAL NEWS HUB (/news)
    @GetMapping("/news")
    public String news(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "news";
    }

    // 4. CINEMA HUB (/movies)
    @GetMapping("/movies")
    public String movies(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "movies";
    }

    // 5. GLOBAL BEATS AUDIO HUB (/music)
    @GetMapping("/music")
    public String music(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "music";
    }

    // 6. MET-OFFICE WEATHER HUB (/weather)
    @GetMapping("/weather")
    public String weather(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "weather";
    }

    // 7. PODCASTS HQ (/podcasts)
    @GetMapping("/podcasts")
    public String podcasts(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "podcasts";
    }

    // 8. SYSTEM HELP HUB (/help)
    @GetMapping("/help")
    public String help(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "help";
    }

    // 9. SECURITY INTERFACE (/login)
    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) return "redirect:/"; 
        return "login";
    }
}
