package com.thenexusweb.controller;

import com.thenexusweb.model.UserProfile;
import com.thenexusweb.repository.UserProfileRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileApiController {

    @Autowired
    private UserProfileRepository profileRepository;

    // 1. Sync Theme Customizations (Dark Mode Toggle)
    @PostMapping("/theme")
    public ResponseEntity<String> updateThemePreference(@RequestParam boolean darkMode, HttpSession session) {
        UserProfile currentSessionUser = (UserProfile) session.getAttribute("loggedInUser");
        if (currentSessionUser == null) return ResponseEntity.status(401).body("Unauthorized node access.");

        currentSessionUser.setDarkMode(darkMode);
        profileRepository.save(currentSessionUser); // Commit values directly to our database data block
        return ResponseEntity.ok("Theme parameter configuration recorded.");
    }

    // 2. Clear & Update Password Matrix Rules Safely
    @PostMapping("/password")
    public ResponseEntity<String> updatePasskeyMatrix(@RequestParam String oldPassword, @RequestParam String newPassword, HttpSession session) {
        UserProfile currentSessionUser = (UserProfile) session.getAttribute("loggedInUser");
        if (currentSessionUser == null) return ResponseEntity.status(401).body("Session invalid.");

        if (!currentSessionUser.getPassword().equals(oldPassword)) {
            return ResponseEntity.status(400).body("Existing verification credentials do not balance.");
        }

        currentSessionUser.setPassword(newPassword);
        profileRepository.save(currentSessionUser);
        return ResponseEntity.ok("Passkey update committed successfully.");
    }

    // 3. Complete Image Stream Vector Upload Processor
    @PostMapping("/avatar")
    public ResponseEntity<String> uploadProfileAvatar(@RequestParam("avatarFile") MultipartFile file, HttpSession session) {
        UserProfile currentSessionUser = (UserProfile) session.getAttribute("loggedInUser");
        if (currentSessionUser == null || file.isEmpty()) return ResponseEntity.badRequest().body("Fault injection rejected.");

        try {
            String trackingFilename = currentSessionUser.getUsername() + "_" + file.getOriginalFilename();
            Path assetStorageDestination = Paths.get("src/main/resources/static/uploads/").resolve(trackingFilename);
            
            Files.createDirectories(assetStorageDestination.getParent());
            Files.copy(file.getInputStream(), assetStorageDestination, StandardCopyOption.REPLACE_EXISTING);

            currentSessionUser.setAvatarUrl("/uploads/" + trackingFilename);
            profileRepository.save(currentSessionUser);
            
            return ResponseEntity.ok("Image file successfully locked to database profile.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Internal disk storage parsing exception encountered.");
        }
    }
}
