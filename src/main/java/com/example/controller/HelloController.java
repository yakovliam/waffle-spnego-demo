package com.example.controller;

import java.security.Principal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/protected")
  public ResponseEntity<String> _protected(Principal principal) {
    if (principal == null || principal.getName() == null) {
      return ResponseEntity.ok("We got through to /protected, but the `principal` is null.");
    }

    return ResponseEntity.ok("Hello, " + principal.getName());
  }

  @GetMapping("/unprotected")
  public ResponseEntity<String> unprotected() {
    return ResponseEntity.ok("unprotected");
  }
}