package com.crud.app;

import com.crud.app.model.FormInput;
import com.crud.app.repository.FormInputRepository;
import com.crud.app.service.WebDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DatabaseIntegrationTest {

    @Autowired
    private FormInputRepository formInputRepository;
    @Autowired
    private WebDataService webDataService;
    private static final String[] VALID_SELECTED_OPTIONS = new String[]{"1", "2"};

    @Test
    public void testSaveFormInput() {
        FormInput formInput = new FormInput("Some Name", VALID_SELECTED_OPTIONS, true);
        FormInput savedFormInput = formInputRepository.save(formInput);
        FormInput retrievedFormInput = formInputRepository.findById(savedFormInput.getId()).orElse(null);
        Assertions.assertNotNull(retrievedFormInput);
        Assertions.assertEquals("Some Name", retrievedFormInput.getName());
        Assertions.assertArrayEquals(VALID_SELECTED_OPTIONS, retrievedFormInput.getSelectedOptions());
        Assertions.assertTrue(retrievedFormInput.isAgreedToTerms());
    }

    @Test
    public void testEditFormInput() {
        FormInput formInput = new FormInput("Initial Name", VALID_SELECTED_OPTIONS, true);
        FormInput savedFormInput = formInputRepository.save(formInput);
        Long formInputId = savedFormInput.getId();
        FormInput retrievedFormInput = formInputRepository.findById(formInputId).orElse(null);
        Assertions.assertNotNull(retrievedFormInput);
        retrievedFormInput.setName("Updated Name");
        retrievedFormInput.setSelectedOptions(new String[]{"3", "4"});
        FormInput editedFormInput = formInputRepository.save(retrievedFormInput);
        FormInput retrievedEditedFormInput = formInputRepository.findById(formInputId).orElse(null);
        Assertions.assertNotNull(retrievedEditedFormInput);
        // Assert the changes made to the FormInput
        Assertions.assertEquals("Updated Name", retrievedEditedFormInput.getName());
        Assertions.assertArrayEquals(new String[]{"3", "4"}, retrievedEditedFormInput.getSelectedOptions());
    }
}
