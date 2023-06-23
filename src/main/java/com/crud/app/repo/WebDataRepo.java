package com.crud.app.repo;

import com.crud.app.model.WebData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebDataRepo extends JpaRepository<WebData, Long> {
}
