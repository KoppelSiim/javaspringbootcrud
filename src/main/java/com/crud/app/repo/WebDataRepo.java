package com.crud.app.repo;

import com.crud.app.model.WebData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WebDataRepo extends CrudRepository<WebData, Long> {
}
