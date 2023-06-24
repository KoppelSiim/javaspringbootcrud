package com.crud.app.service;

import com.crud.app.model.FormInput;
import com.crud.app.repo.FormInputRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FormInputService {

    private final FormInputRepository formInputRepository;

    @Autowired
    public FormInputService(FormInputRepository formInputRepository){
        this.formInputRepository = formInputRepository;
    }

    public void insertFormInput(String name, String[] selectedOptions, boolean agreedToTerms){
        FormInput formInput = new FormInput();
        formInput.setName(name);
        formInput.setSelectedOptions(selectedOptions);
        formInput.setAgreedToTerms(agreedToTerms);
        formInputRepository.save(formInput);
    }
}
