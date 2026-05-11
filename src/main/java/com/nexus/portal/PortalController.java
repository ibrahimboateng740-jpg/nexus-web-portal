package com.nexus.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortalController {

    // This tells the app to listen for the main website address (the "root" path)
    @GetMapping("/")
    public String home() {
        // This looks for "index.html" in src/main/resources/templates/
        return "index"; 
    }
}
