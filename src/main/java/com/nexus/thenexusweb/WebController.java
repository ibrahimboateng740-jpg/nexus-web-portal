package com.thenexus.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    // 1. The Public Landing Page (Unprotected)
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 2. The Custom Login Page (Unprotected)
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 3. The Custom Signup Page (Unprotected)
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    // 4. RESTORED: Your original secure home screen with your sidebar applications
    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            model.addAttribute("name", principal.getAttribute("name"));
            model.addAttribute("email", principal.getAttribute("email"));
            model.addAttribute("picture", principal.getAttribute("picture"));
        }
        // This targets your original file containing your apps sidebar layout
        return "home"; 
    }
}
