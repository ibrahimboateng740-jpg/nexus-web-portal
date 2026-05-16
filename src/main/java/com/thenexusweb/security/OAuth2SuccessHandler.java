package com.thenexusweb.security;

import com.thenexusweb.model.UserProfile;
import com.thenexusweb.repository.UserProfileRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserProfileRepository profileRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
            throws IOException, ServletException {
        
        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
        String userEmail = oauthUser.getAttribute("email");
        String fullName = oauthUser.getAttribute("name");
        String googlePictureUrl = oauthUser.getAttribute("picture");

        // Locate or provision a persistent user configuration row based on their Google account identity
        UserProfile profile = profileRepository.findByUsername(userEmail).orElseGet(() -> {
            UserProfile newProfile = new UserProfile();
            newProfile.setUsername(userEmail);
            // OAuth accounts do not use local passwords; assign a randomized token variant for structural safety
            newProfile.setPassword("OAUTH_FEDERATED_" + UUID.randomUUID().toString().substring(0, 8));
            newProfile.setDarkMode(false);
            newProfile.setAvatarUrl(googlePictureUrl);
            return profileRepository.save(newProfile);
        });

        // Inject identity attributes directly into our active layout scope
        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", profile);

        // Instantly return the user to our homepage layout engine post authentication
        response.sendRedirect("/");
    }
}
