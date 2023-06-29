package com.crud.app;

import com.crud.app.exception.FormInputException;
import com.crud.app.model.FormInput;
import com.crud.app.repository.FormInputRepository;
import com.crud.app.service.FormInputService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FormInputServiceTest {
    @Mock
    private FormInputRepository formInputRepository;

    @InjectMocks
    private FormInputService formInputService;
    @Captor
    private ArgumentCaptor<FormInput> formInputArgumentCaptor;

    // Is the form input data object correctly built and saved to database?
    @Test
    public void testFormInputData() {
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

    // Is an exception thrown when entering faulty data?
    @Test
    public void testSaveFormInput_WithFaultyData() {
        String emptyName = "";
        String validName = "Valid Name";
        String[] noSelectedOptions = {}; // No options selected
        String[] selectedOptions = {"1", "18","19"};
        boolean notAgreedToTerms = false;
        boolean agreedToTerms = true;
        assertThrows(FormInputException.class, () -> formInputService.insertFormInput(emptyName, selectedOptions, agreedToTerms));
        assertThrows(FormInputException.class, () -> formInputService.insertFormInput(validName,noSelectedOptions, agreedToTerms));
        assertThrows(FormInputException.class, () -> formInputService.insertFormInput(validName,selectedOptions, notAgreedToTerms));


    }

}
