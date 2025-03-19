package com.urlol.backend.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class UrlClick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ipAddress;
    private String continent;
    private String country;
    private String region;
    private String city;
    private String countryEmoji;
    private LocalDateTime timestamp;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "short_id", nullable = false)
    private Url url;
    
}
