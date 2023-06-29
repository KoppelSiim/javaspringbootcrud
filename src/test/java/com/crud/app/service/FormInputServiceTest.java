package com.crud.app.service;

import com.crud.app.exception.FormInputException;
import com.crud.app.model.FormInput;
import com.crud.app.repository.FormInputRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@ExtendWith(MockitoExtension.class)
public class FormInputServiceTest {
    @Mock
    private FormInputRepository formInputRepository;
    @InjectMocks
    private FormInputService formInputService;
    @Captor
    private ArgumentCaptor<FormInput> formInputArgumentCaptor;

    @Test
    public void testFormInputWithValidData() {
        String name = "Test Name";
        String[] selectedOptions = {"1", "18", "19"};
        boolean agreedToTerms = true;
        formInputService.insertFormInput(name, selectedOptions, agreedToTerms);
        verify(formInputRepository).save(formInputArgumentCaptor.capture());
        FormInput formInput = formInputArgumentCaptor.getValue();
        assertEquals(name, formInput.getName());
        assertEquals(selectedOptions, formInput.getSelectedOptions());
        assertEquals(agreedToTerms, formInput.isAgreedToTerms());
    }

    @ParameterizedTest
    @CsvSource({
            "null, 1,18,19, true",
            "Valid Name, , true, false",
            "Valid Name, 1,18,19, false"
    })
    public void testSaveFormInput_WithFaultyData(String name, String selectedOptions, boolean agreedToTerms) {
        String[] options = selectedOptions != null && !selectedOptions.isEmpty() ? selectedOptions.split(",") : new String[0];
        assertThrows(FormInputException.class, () -> formInputService.insertFormInput(name, options, agreedToTerms));
    }
}
