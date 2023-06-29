package com.crud.app;

import com.crud.app.model.FormInput;
import com.crud.app.repository.FormInputRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DatabaseIntegrationTest {

    @Autowired
    private FormInputRepository formInputRepository;
    private static final String[] VALID_SELECTED_OPTIONS = new String[]{"1", "2"};
    private static final String UPDATED_NAME = "Updated Name";
    private final FormInput formInput = new FormInput("Some Name", VALID_SELECTED_OPTIONS, true);
    @Test
    public void testSaveFormInput() {
        FormInput savedFormInput = formInputRepository.save(formInput);
        FormInput retrievedFormInput = formInputRepository.findById(savedFormInput.getId()).orElseThrow();
        Assertions.assertEquals("Some Name", retrievedFormInput.getName());
        Assertions.assertArrayEquals(VALID_SELECTED_OPTIONS, retrievedFormInput.getSelectedOptions());
        Assertions.assertTrue(retrievedFormInput.isAgreedToTerms());
    }

    @Test
    public void testEditFormInput() {
        FormInput savedFormInput = formInputRepository.save(formInput);
        Long formInputId = savedFormInput.getId();
        FormInput retrievedFormInput = formInputRepository.findById(formInputId).orElseThrow();
        retrievedFormInput.setName(UPDATED_NAME);
        retrievedFormInput.setSelectedOptions(new String[]{"3", "4"});
        formInputRepository.save(retrievedFormInput);
        FormInput retrievedEditedFormInput = formInputRepository.findById(formInputId).orElseThrow();
        Assertions.assertEquals(UPDATED_NAME, retrievedEditedFormInput.getName());
        Assertions.assertArrayEquals(new String[]{"3", "4"}, retrievedEditedFormInput.getSelectedOptions());
    }
}
