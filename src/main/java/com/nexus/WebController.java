package com.thenexus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/podcasts")
    public String podcasts(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "podcasts";
    }

    @GetMapping("/social") public String social() { return "social"; }
    @GetMapping("/news") public String news() { return "news"; }
    @GetMapping("/login") public String login() { return "login"; }
    @GetMapping("/help") public String help() { return "help"; }
}
