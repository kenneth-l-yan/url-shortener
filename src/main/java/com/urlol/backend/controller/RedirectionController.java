package com.urlol.backend.controller;

import java.io.IOException;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.urlol.backend.model.Url;
import com.urlol.backend.service.UrlClickService;
import com.urlol.backend.service.UrlService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class RedirectionController {

    private final UrlService urlService;
    private final UrlClickService urlClickService;
    private HttpServletRequest request;

    // Constructor injection
    public RedirectionController(UrlService urlService, UrlClickService urlClickService, HttpServletRequest request) {
        this.urlService = urlService;
        this.urlClickService = urlClickService;
        this.request = request;
    }

    @GetMapping("/{shortId}")
    public void redirectToOriginalUrl(@PathVariable String shortId, HttpServletResponse response) throws IOException {
        
        Url url = urlService.getUrlById(shortId);
        response.sendRedirect(url.getOriginalUrl());

        urlClickService.logClick(shortId, request);
    }

}
