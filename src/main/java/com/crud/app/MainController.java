package com.crud.app;

import com.crud.app.model.FormInput;
import com.crud.app.model.WebData;
import com.crud.app.service.FormInputService;
import com.crud.app.service.WebDataService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class MainController {
    private final ResourceLoader resourceLoader;
    private final WebDataService webDataService;
    private final FormInputService formInputService;

    @Autowired
    public MainController(ResourceLoader resourceLoader, WebDataService webDataService, FormInputService formInputService) {
        this.resourceLoader = resourceLoader;
        this.webDataService = webDataService;
        this.formInputService = formInputService;
    }
    // serve my homepage
    @GetMapping(value = "/home", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public byte[] getApiPage(HttpSession httpSession) throws IOException {
        String sessionId = httpSession.getId();
        System.out.println(sessionId);
        Resource resource = new ClassPathResource("static/index.html");
        return Files.readAllBytes(resource.getFile().toPath());
    }
    @GetMapping(value = "/all")
    public List<WebData> getAllWebData() {
        return webDataService.getAllWebData();
    }

    // validate and save form data, save session data
    @PostMapping(value = "/submit")
    @ResponseBody
    public ResponseEntity<?> saveFormInput(@Valid @RequestBody FormInput formInput, HttpSession session) {

        String sessionId = session.getId();
        System.out.println(sessionId);
        // Save the session ID and form primary key as session attributes
        // Set the form primary key as a session attribute
        session.setAttribute("formInput",formInput);
        Long formPrimaryKey =  formInputService.insertFormInput(formInput.getName(), formInput.getSelectedOptions(), formInput.isAgreedToTerms());
        //System.out.println(formPrimaryKey);
        session.setAttribute("formPrimaryKey", formPrimaryKey);
        Long testdata = (Long) session.getAttribute("formPrimaryKey");
        System.out.println(testdata);
            return ResponseEntity.ok("Form is valid, form data saved. " + "Session id: " + sessionId);

    }
    @PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String editForm(@RequestBody FormInput newFormInput, HttpSession session) {
        // Use the session ID as the unique identifier
        String uniqueId = session.getId();

        // Retrieve the original stored form data from the session
        FormInput originalData = (FormInput) session.getAttribute("formInput");
        //Retrieve the formdata primary key
        Long formPrimaryKey = (Long) session.getAttribute("formPrimaryKey");
        if (originalData != null) {
            // Update the original form data with new data

            originalData.setName(newFormInput.getName());
            originalData.setSelectedOptions(newFormInput.getSelectedOptions());
            originalData.setAgreedToTerms(newFormInput.isAgreedToTerms());
            formInputService.updateFormData(originalData, formPrimaryKey);

            return "Form data updated successfully.";
        } else {
            return "Invalid session or form data not found.";
        }
    }


}