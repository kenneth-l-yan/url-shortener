package com.urlol.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urlol.backend.model.UrlClick;

@Repository
public interface UrlClickRepository extends JpaRepository<UrlClick, Long>{
}
