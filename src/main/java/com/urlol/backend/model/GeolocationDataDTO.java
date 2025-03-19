package com.urlol.backend.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Tells Jackson to ignore any extra fields.
public class GeolocationDataDTO {

    @JsonProperty("ip_address")
    private String ipAddress;
    private String city;
    private String region;
    private String country;
    private String continent;
    private LocalDateTime timestamp;
    private Flag flag;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Flag {
        private String emoji;
    }



}
