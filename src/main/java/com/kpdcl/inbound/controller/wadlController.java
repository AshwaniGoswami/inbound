package com.kpdcl.inbound.controller;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class wadlController {

    @GetMapping(value = "/api/hierarchy/application.wadl", produces = "application/xml")
    public ResponseEntity<Resource> getWadl() {
        Resource resource = new ClassPathResource("api_hierarchy.wadl");
        return ResponseEntity.ok(resource);
    }
}

