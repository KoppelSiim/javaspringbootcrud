package com.crud.app.service;

import com.crud.app.model.WebData;
import com.crud.app.repository.WebDataRepo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class WebDataServiceTest {
    @Mock
    private WebDataRepo webDataRepository;

    @InjectMocks
    private WebDataService webDataService;
    @Captor
    private ArgumentCaptor<WebData> webDataCaptor;

    @Test
    public void testInsertOptionsData() {

        String optionText = "Option Text";
        String optionValue = "Option Value";
        int indentation = 2;

        webDataService.insertOptionsData(optionText, optionValue, indentation);

        verify(webDataRepository).save(webDataCaptor.capture());
        WebData capturedWebData = webDataCaptor.getValue();

        assertEquals(optionText, capturedWebData.getOptionText());
        assertEquals(optionValue, capturedWebData.getOptionValue());
        assertEquals(indentation, capturedWebData.getIndentation());
    }
}
