package com.crud.app.service;

import com.crud.app.model.FormInput;
import com.crud.app.repository.FormInputRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FormInputService {

    private final FormInputRepository formInputRepository;

    @Autowired
    public FormInputService(FormInputRepository formInputRepository){
        this.formInputRepository = formInputRepository;
    }

    public Long insertFormInput(String name, String[] selectedOptions, boolean agreedToTerms){
        FormInput formInput = new FormInput();
        formInput.setName(name);
        formInput.setSelectedOptions(selectedOptions);
        formInput.setAgreedToTerms(agreedToTerms);
        formInputRepository.save(formInput);
        return formInput.getId();
    }

    public void updateFormData(FormInput formInput, Long formPrimaryKey){
        // Retrieve the existing form input from the database based on its ID
        FormInput existingFormInput = formInputRepository.findById(formPrimaryKey)
                .orElseThrow(() -> new EntityNotFoundException("Form not found"));

        // Update the properties of the existing form input with the modified values
        existingFormInput.setName(formInput.getName());
        existingFormInput.setSelectedOptions(formInput.getSelectedOptions());
        existingFormInput.setAgreedToTerms(formInput.isAgreedToTerms());
        // Save the updated form input back to the database
        formInputRepository.save(existingFormInput);
    }
}
