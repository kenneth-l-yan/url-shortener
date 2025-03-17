package com.urlol.backend.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.urlol.backend.model.Url;
import com.urlol.backend.model.UrlClick;
import com.urlol.backend.repository.UrlClickRepository;
import com.urlol.backend.repository.UrlRepository;

import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Mono;

@Service
public class UrlClickService {
    private final UrlService urlService;
    private final UrlRepository urlRepository;
    private final UrlClickRepository urlClickRepository;
    private final WebClient webClient;

    private static final String CHECKP_IP_AMAZONAWS = "https://checkip.amazonaws.com";
    private static final String API_KEY = "6a5726dc1f874021a28acd4f28a39283";
    private static final String API_URL = "https://ipgeolocation.abstractapi.com/v1/?api_key=";

    // Constructor injection
    public UrlClickService(UrlService urlService, UrlRepository urlRepository, UrlClickRepository urlClickRepository, WebClient webClient) {
        this.urlService = urlService;
        this.urlRepository = urlRepository;
        this.urlClickRepository = urlClickRepository;
        this.webClient = webClient;
    }

    public void logClick(String shortId, HttpServletRequest request) {

        Url url = urlService.getUrlById(shortId);

        UrlClick newUrlClick = new UrlClick();
        String ipAddress = getIPAddress(request);
        String geolocationData = getGeolocation(ipAddress).block();

        newUrlClick.setUrl(url);
        newUrlClick.setTimestamp(LocalDateTime.now());
        newUrlClick.setIpAddress(ipAddress);
        // Set newUrlClick properties from geolocationData.
        
        url.setNumberOfClicks(url.getNumberOfClicks() + 1);
        url.getUrlClicks().add(newUrlClick);
        
        urlClickRepository.save(newUrlClick);
        urlRepository.save(url);

    }

    private String getIPAddress(HttpServletRequest request) {
        
        String ipAddress = request.getHeader("X-Forwarded-For");

        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        // Handle IPv6 loopback address
        if ("0:0:0:0:0:0:0:1".equals(ipAddress)) {
            ipAddress = webClient.get()
                            .uri(CHECKP_IP_AMAZONAWS)
                            .retrieve()
                            .bodyToMono(String.class)
                            .map(String::trim) // Remove extra spaces/newlines
                            .onErrorReturn("Unable to fetch public IP")
                            .block();
        }

        return ipAddress;
    }

    private Mono<String> getGeolocation(String ipAddress) {
        return webClient.get()
                .uri(API_URL + API_KEY + "&ip_address=" + ipAddress)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("Unable to fetch geolocation data");
    }

}
