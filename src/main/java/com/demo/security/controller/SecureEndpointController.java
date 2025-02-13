package com.demo.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class SecureEndpointController {

    @GetMapping("/hello")
    public ResponseEntity<String> secureHello() {
        return ResponseEntity.ok("Hello from HTTPS secured endpoint! This endpoint is accessible via HTTPS.");
    }
}
