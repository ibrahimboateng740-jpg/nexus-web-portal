package com.thenexusweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @GetMapping("/search")
    public String executePortalSearch(@RequestParam(value = "q", required = false, defaultValue = "") String query, Model model) {
        
        List<Map<String, String>> mockDatabaseResults = new ArrayList<>();
        
        // Clean and process input query strings safely
        String cleanQuery = query.trim().toLowerCase();

        if (!cleanQuery.isEmpty()) {
            // MOCK DATABASE FILTER ENGINE: Add fallback match items
            if ("news".contains(cleanQuery) || "live".contains(cleanQuery) || "global".contains(cleanQuery)) {
                Map<String, String> item1 = new HashMap<>();
                item1.put("category", "NEWS WIRE");
                item1.put("title", "Global Stream Networks Deploy New High-Throughput Core Matrix");
                item1.put("description", "Engineers successfully rolled out network frameworks optimized to handle modern responsive web portal portals.");
                item1.put("url", "/news");
                item1.put("date", "2026-05-21");
                mockDatabaseResults.add(item1);
            }
            
            if ("podcast".contains(cleanQuery) || "wired".contains(cleanQuery) || "logic".contains(cleanQuery)) {
                Map<String, String> item2 = new HashMap<>();
                item2.put("category", "PODCAST");
                item2.put("title", "Wired Logic Podcast - Episode 1 Deployments");
                item2.put("description", "Listen to our baseline introductory launch episode discussing software lifecycles, cloud setups, and framework structures.");
                item2.put("url", "/podcast");
                item2.put("date", "2026-04-18");
                mockDatabaseResults.add(item2);
            }
        }

        // Add variables straight into the template workspace model
        model.addAttribute("query", query);
        model.addAttribute("searchResults", mockDatabaseResults);
        model.addAttribute("resultsCount", mockDatabaseResults.size());

        return "search"; // Opens up your sibling search.html view file immediately!
    }
}
