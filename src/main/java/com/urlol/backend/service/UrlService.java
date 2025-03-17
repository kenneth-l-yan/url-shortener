package com.urlol.backend.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.urlol.backend.Exceptions.UrlNotFoundException;
import com.urlol.backend.model.Url;
import com.urlol.backend.model.UserRequestDTO;
import com.urlol.backend.repository.UrlRepository;

@Service
public class UrlService {
    
    private UrlRepository urlRepository;

    public UrlService (UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }

    public Url getUrlById(String shortId) {
        return urlRepository.findById(shortId).orElseThrow(() -> new UrlNotFoundException(shortId));
    }

    public Url insertUrl(UserRequestDTO request) {
        String originalUrl = request.getRequestedUrl();
        String shortId;
        do {
            shortId = generateShortId();
        } while (urlRepository.existsById(shortId));

        Url newUrl = new Url(shortId, originalUrl, 0, LocalDateTime.now(), LocalDateTime.now().plusWeeks(2), new ArrayList<>());

        return urlRepository.save(newUrl);
    }
    
    private String generateShortId() {
        String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder shortId = new StringBuilder();

        int length = 6;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(validChars.length());
            shortId.append(validChars.charAt(index));
        }
        return shortId.toString();
    }
}
