package com.thenexus.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    // Public Base Gateway Route
    @GetMapping("/")
    public String index() {
        return "index"; // Maps to index.html (Your login access screen)
    }

    // Protected Dashboard Destination Route
    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            // Safely pass the logged-in user's name down into your UI template layout
            model.addAttribute("name", principal.getAttribute("name"));
            model.addAttribute("email", principal.getAttribute("email"));
        }
        return "home"; // Maps to home.html (Your official dashboard page)
    }
}
