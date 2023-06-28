package com.crud.app;

import com.crud.app.model.WebData;
import com.crud.app.repository.WebDataRepo;
import com.crud.app.service.WebDataService;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class WebDataServiceTest {
    @Mock
    private WebDataRepo webDataRepository;

    @InjectMocks
    private WebDataService webDataService;

    @Captor
    private ArgumentCaptor<WebData> webDataCaptor;
    // does the method correctly builds and saves the WebData model?
    @Test
    public void testInsertOptionsData() {
        MockitoAnnotations.openMocks(this);

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
