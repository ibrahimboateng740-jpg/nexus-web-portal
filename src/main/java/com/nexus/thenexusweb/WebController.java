package com.nexus.thenexusweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    // ==========================================
    // 1. GOOGLE ADSENSE ENDPOINT (ads.txt Verification)
    // ==========================================
    @GetMapping(value = "/ads.txt", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String googleAdsenseVerification() {
        // Swap out pub-0000000000000000 with your real AdSense Publisher ID
        return "google.com, pub-0000000000000000, DIRECT, f08c47fec0942fa0";
    }

    // ==========================================
    // 2. INTERNAL PREMIUM "ADSS" DATA ENGINE
    // ==========================================
    @GetMapping(value = "/api/adss", produces = "application/json")
    @ResponseBody
    public List<Map<String, String>> getPremiumSponsors() {
        // These are your premium custom sales that bypass Google and pay you 100% directly
        return List.of(
            Map.of(
                "title", "Wired Logic Podcast - New Episode Live",
                "link", "/podcasts",
                "image", "https://via.placeholder.com/728x90/d81e06/ffffff?text=WIRED+LOGIC+PODCAST+HQ"
            ),
            Map.of(
                "title", "Premium Global Tech Nodes",
                "link", "/help",
                "image", "https://via.placeholder.com/728x90/111111/ffffff?text=SPONSOR+THE+NEXUS+WEB"
            )
        );
    }

    // ==========================================
    // 3. MAIN CORE LAYOUT NAVIGATION ROUTING
    // ==========================================
    @GetMapping("/")
    public String home(Principal principal, Model model) {
        if (principal != null) { model.addAttribute("username", principal.getName()); }
        return "index";
    }

    @GetMapping("/search")
    public String nexusSearch(@RequestParam(name = "q", required = false) String query, Model model, Principal principal) {
        if (principal != null) { model.addAttribute("username", principal.getName()); }
        if (query == null || query.trim().isEmpty()) { return "redirect:/"; }
        model.addAttribute("userQuery", query);
        return "search-results"; 
    }

    @GetMapping("/news")
    public String news(Principal principal, Model model) {
        if (principal != null) { model.addAttribute("username", principal.getName()); }
        return "news";
    }

    @GetMapping("/movies")
    public String movies(Principal principal, Model model) {
        if (principal != null) { model.addAttribute("username", principal.getName()); }
        return "movies";
    }

    @GetMapping("/music")
    public String music(Principal principal, Model model) {
        if (principal != null) { model.addAttribute("username", principal.getName()); }
        return "music";
    }

    @GetMapping("/weather")
    public String weather(Principal principal, Model model) {
        if (principal != null) { model.addAttribute("username", principal.getName()); }
        return "weather";
    }

    @GetMapping("/podcasts")
    public String podcasts(Principal principal, Model model) {
        if (principal != null) { model.addAttribute("username", principal.getName()); }
        return "podcasts";
    }

    @GetMapping("/help")
    public String help(Principal principal, Model model) {
        if (principal != null) { model.addAttribute("username", principal.getName()); }
        return "help";
    }

    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) return "redirect:/"; 
        return "login";
    }
}
