package com.crud.app.service;

import com.crud.app.exception.FormInputException;
import com.crud.app.model.FormInput;
import com.crud.app.repository.FormInputRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class FormInputService {

    private final FormInputRepository formInputRepository;

    @Autowired
    public FormInputService(FormInputRepository formInputRepository) {
        this.formInputRepository = formInputRepository;
    }

    public Long insertFormInput(String name, String[] selectedOptions, boolean agreedToTerms) {

        if (name == null || name.isBlank()) {
            throw new FormInputException("Name cannot be blank");
        }

        if (selectedOptions == null || selectedOptions.length == 0) {
            throw new FormInputException("Select at least one option");
        }

        if (!agreedToTerms) {
            throw new FormInputException("Must agree to terms");
        }
        FormInput formInput = new FormInput();
        formInput.setName(name);
        formInput.setSelectedOptions(selectedOptions);
        formInput.setAgreedToTerms(agreedToTerms);
        formInputRepository.save(formInput);
        return formInput.getId();

    }

    public void updateFormData(FormInput formInput, Long formPrimaryKey) {
        // Retrieve the existing form input from the database based on its ID
        FormInput existingFormInput = formInputRepository.findById(formPrimaryKey).orElseThrow(() -> new EntityNotFoundException("Form not found"));

        // Update the properties of the existing form input with the modified values
        existingFormInput.setName(formInput.getName());
        existingFormInput.setSelectedOptions(formInput.getSelectedOptions());
        existingFormInput.setAgreedToTerms(formInput.isAgreedToTerms());
        formInputRepository.save(existingFormInput);
    }
}
