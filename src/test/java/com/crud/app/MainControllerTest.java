package com.crud.app;


import com.crud.app.service.FormInputService;
import com.crud.app.service.WebDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainController.class)
public class MainControllerTest{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WebDataService webDataService;

    @MockBean
    private FormInputService formInputService;

    // is the html page served correctly?
    @Test
    void testGetApiPage() throws Exception {
        //load the content of html file
        String htmlContent = new String(Files.readAllBytes(ResourceUtils.getFile("classpath:static/index.html").toPath()));
        mockMvc.perform(get("/api/home")
                        .session(new MockHttpSession()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(content().string(htmlContent));
    }

}