package com.urlol.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urlol.backend.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, String>{
}
