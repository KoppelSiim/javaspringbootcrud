package com.crud.app.repository;

import com.crud.app.model.WebData;
import org.springframework.data.repository.CrudRepository;

public interface WebDataRepo extends CrudRepository<WebData, Long> {
}
