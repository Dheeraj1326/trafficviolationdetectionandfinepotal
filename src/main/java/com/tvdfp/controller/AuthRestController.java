package com.tvdfp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tvdfp.model.Users;
import com.tvdfp.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private UserRepository userRepository;

    // ─── REGISTER ──────────────────────────────────────────────
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Users user) {
        Map<String, String> response = new HashMap<>();

        // Username already exists?
        if (userRepository.findByUsername(user.getUsername()) != null) {
            response.put("message", "Username already taken!");
            return ResponseEntity.badRequest().body(response);
        }

        // Email already exists?
        if (userRepository.findByEmail(user.getEmail()) != null) {
            response.put("message", "Email already registered!");
            return ResponseEntity.badRequest().body(response);
        }

        // Save user
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setStatus(1);
        userRepository.save(user);

        response.put("message", "Registration successful!");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {
        Map<String, String> response = new HashMap<>();

        String username = body.get("username"); // email aa raha hai front-end se
        String password = body.get("password");

        // ─── Pehle email se try karo, phir username se ─────────────
        Users user = userRepository.findByEmailAndPassword(username, password);
        if (user == null) {
            user = userRepository.findByUsernameAndPassword(username, password);
        }

        if (user == null) {
            response.put("message", "Invalid User ❌");
            return ResponseEntity.status(401).body(response);
        }

        if (user.getStatus() == 0) {
            response.put("message", "Account is disabled!");
            return ResponseEntity.status(403).body(response);
        }

        response.put("message", "Login successful!");
        response.put("name", user.getName());
        response.put("user_id", String.valueOf(user.getUser_id()));
        return ResponseEntity.ok(response);
    }
}
