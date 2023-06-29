package com.crud.app;

import com.crud.app.model.FormInput;
import com.crud.app.service.FormInputService;
import com.crud.app.service.WebDataService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(MainController.class)
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WebDataService webDataService;
    @MockBean
    private FormInputService formInputService;
    @Autowired
    private ObjectMapper objectMapper;
    private static final String[] VALID_SELECTED_OPTIONS = new String[]{"1", "2"};

    @Test
    void testGetApiPage() throws Exception {
        String htmlContent = new String(Files.readAllBytes(ResourceUtils.getFile("classpath:static/index.html").toPath()));
        mockMvc.perform(get("/api/home").session(new MockHttpSession())).andExpect(status().isOk()).andExpect(content().contentType(MediaType.TEXT_HTML)).andExpect(content().string(htmlContent));
    }

    @Test
    public void testSaveFormInput_WithValidData() throws Exception {
        FormInput formInput = new FormInput("Test Name", VALID_SELECTED_OPTIONS, true);
        mockMvc.perform(post("/api/submit").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(formInput))).andExpect(status().isOk());
    }

    @Test
    public void testSaveFormInput_WithInvalidData() throws Exception {
        FormInput formInput = new FormInput("", VALID_SELECTED_OPTIONS, true);
        String jsonPayload = objectMapper.writeValueAsString(formInput);
        mockMvc.perform(post("/api/submit").contentType(MediaType.APPLICATION_JSON).content(jsonPayload)).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateFormInput_WithValidData() throws Exception {
        MockHttpSession session = new MockHttpSession();
        FormInput originalData = new FormInput("Original name", VALID_SELECTED_OPTIONS, true);
        Long formPrimaryKey = 1L;
        session.setAttribute("formInput", originalData);
        session.setAttribute("formPrimaryKey", formPrimaryKey);
        FormInput updatedData = new FormInput("Updated name", VALID_SELECTED_OPTIONS, true);
        String jsonPayload = objectMapper.writeValueAsString(updatedData);
        mockMvc.perform(put("/api/edit").contentType(MediaType.APPLICATION_JSON).content(jsonPayload).session(session)).andExpect(status().isOk());
    }

    @Test
    public void testUpdateFormInput_WithInValidData() throws Exception {
        MockHttpSession session = new MockHttpSession();
        FormInput originalData = new FormInput("Original name", VALID_SELECTED_OPTIONS, true);
        Long formPrimaryKey = 1L;
        session.setAttribute("formInput", originalData);
        session.setAttribute("formPrimaryKey", formPrimaryKey);
        FormInput updatedData = new FormInput("", VALID_SELECTED_OPTIONS, true);
        String jsonPayload = objectMapper.writeValueAsString(updatedData);
        mockMvc.perform(put("/api/edit").contentType(MediaType.APPLICATION_JSON).content(jsonPayload).session(session)).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateFormInput_WithInValidSession() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("formInput", null);
        session.setAttribute("formPrimaryKey", null);
        FormInput updatedData = new FormInput("Updated Name", VALID_SELECTED_OPTIONS, true);
        String jsonPayload = objectMapper.writeValueAsString(updatedData);
        mockMvc.perform(put("/api/edit").contentType(MediaType.APPLICATION_JSON).content(jsonPayload).session(session)).andExpect(status().isBadRequest());
    }
}
