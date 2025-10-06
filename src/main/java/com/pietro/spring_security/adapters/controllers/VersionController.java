package com.pietro.spring_security.adapters.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/version")
public class VersionController {

    @GetMapping()
    public ResponseEntity<String> versionMethod() {
        return ResponseEntity.ok("Up.");
    }

}
