package com.urlol.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlol.backend.model.Url;
import com.urlol.backend.model.UserRequestDTO;
import com.urlol.backend.service.UrlService;

@RestController
@RequestMapping("/shorten")
public class UrlController {

    private UrlService urlService;

    public UrlController(UrlService urlSerivce) {
        this.urlService = urlSerivce;
    }

    @GetMapping
    public ResponseEntity<List<Url>> getAllUrls() {
        List<Url> allUrls = urlService.getAllUrls();
        return ResponseEntity.status(HttpStatus.OK).body(allUrls);
    }

    @GetMapping("/{shortId}")
    public ResponseEntity<Url> getUrlById(@PathVariable String shortId) {
        Url url = urlService.getUrlById(shortId);
        return ResponseEntity.status(HttpStatus.OK).body(url);
    }

    @PostMapping
    public ResponseEntity<Url> createUrl(@RequestBody UserRequestDTO request) {
        Url createdUrl = urlService.insertUrl(request);
        return ResponseEntity.status(HttpStatus.OK).body(createdUrl);
    }


}
