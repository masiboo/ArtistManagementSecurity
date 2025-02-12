package com.demo.security.model;

public record AuthRequest(
    String email,
    String password
) {}
