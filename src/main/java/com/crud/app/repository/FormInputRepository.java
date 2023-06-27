package com.crud.app.repository;

import org.springframework.data.repository.CrudRepository;
import com.crud.app.model.FormInput;

public interface FormInputRepository extends CrudRepository<FormInput, Long> {
}
