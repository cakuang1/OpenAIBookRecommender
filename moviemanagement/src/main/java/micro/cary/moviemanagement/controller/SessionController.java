package micro.cary.moviemanagement.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import micro.cary.moviemanagement.service.SessionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


// SESSION CONTROLLER

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    private final SessionService sessionService;


    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }
  


    // STORES INITAL SESSION
    @PostMapping("/{sessionid}/store")
    public ResponseEntity<Void> storeSessionData(
            @PathVariable String sessionid,
            @RequestBody Map<String, String> sessionData,
            @RequestParam int sessionDurationInSeconds) {
        sessionService.storeSessionData(sessionid, sessionData, sessionDurationInSeconds);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{sessionid}")
    public ResponseEntity<Map<String, String>> getSessionData(@PathVariable String sessionid) {
        Map<String, String> sessionData = sessionService.getSessionData(sessionid);
        if (!sessionData.isEmpty()) {
            return ResponseEntity.ok(sessionData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/{sessionid}/addkey")
    public ResponseEntity<Void> addKeyToSessionData(
            @PathVariable String sessionid,
            @RequestParam String key,
            @RequestParam String value) {
        sessionService.addKeyToSessionData(sessionid, key, value);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{sessionid}/deletekey")
    public ResponseEntity<Void> deleteKeyFromSessionData(
            @PathVariable String sessionid,
            @RequestParam String key) {
        sessionService.deleteKeyFromSessionData(sessionid, key);
        return ResponseEntity.ok().build();
    }

    


    @GetMapping("/")
    public String distributeId(HttpServletRequest request, HttpServletResponse response) {
        // Get the user's session ID from the cookie or generate a new one
        String sessionId = getOrCreateSessionId(request, response);

        // Store the session ID in the user's browser as a cookie
        setSessionIdCookie(response, sessionId);

        // Do any other landing page logic
        sessionService.storeSessionData(sessionId, new HashMap<>(), 1800);
        return "landing_page"; // Return the landing page view
    }

    private String getOrCreateSessionId(HttpServletRequest request, HttpServletResponse response) {
        // Check if the user already has a session ID cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("sessionId")) {
                    return cookie.getValue(); // Return the existing session ID
                }
            }
        }

        // If no session ID cookie found, generate a new session ID
        String sessionId = generateSessionId();

        return sessionId;
    }

    private void setSessionIdCookie(HttpServletResponse response, String sessionId) {
        Cookie cookie = new Cookie("sessionId", sessionId);
        cookie.setMaxAge(-1); // Cookie expires when browser session ends
        cookie.setPath("/");
        cookie.setHttpOnly(true);   

        response.addCookie(cookie);
    }

    private String generateSessionId() {
        // Generate a unique session ID using your preferred method
        // For example, you can use UUID.randomUUID() or any custom logic
        return UUID.randomUUID().toString();
    }
}








