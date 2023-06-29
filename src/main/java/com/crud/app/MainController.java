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


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


@RestController
@RequestMapping("/api")
public class MainController {

    private final WebDataService webDataService;
    private final FormInputService formInputService;

    @Autowired
    public MainController(WebDataService webDataService, FormInputService formInputService) {

        this.webDataService = webDataService;
        this.formInputService = formInputService;
    }

    // Serve my api homepage
    @GetMapping(value = "/home", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public byte[] getApiPage(HttpSession httpSession) throws IOException {
        String sessionId = httpSession.getId();
        System.out.println(sessionId);
        Resource resource = new ClassPathResource("static/index.html");
        return Files.readAllBytes(resource.getFile().toPath());
    }

    // Get all the options data
    @GetMapping(value = "/all")
    public List<WebData> getAllWebData() {
        return webDataService.getAllWebData();
    }

    // Validate and save form data, save session data
    @PostMapping(value = "/submit")
    @ResponseBody
    public ResponseEntity<String> saveFormInput(@Valid @RequestBody FormInput formInput, HttpSession session, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {

            StringBuilder validationErrors = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                validationErrors.append(error.getDefaultMessage()).append(", ");
            }
            return ResponseEntity.badRequest().body("Form data validation failed: " + validationErrors.toString());
        }
        String sessionId = session.getId();
        session.setAttribute("formInput", formInput);
        Long formPrimaryKey = formInputService.insertFormInput(formInput.getName(), formInput.getSelectedOptions(), formInput.isAgreedToTerms());
        session.setAttribute("formPrimaryKey", formPrimaryKey);
        String user = formInput.getName();

        return ResponseEntity.ok("Welcome " + user + "<br>" + "Your form data has been saved!");

    }

    @PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> editForm(@Valid @RequestBody FormInput newFormInput, HttpSession session, BindingResult bindingResult) {

        if (session.getAttribute("formInput") != null && session.getAttribute("formPrimaryKey") != null) {

            if (bindingResult.hasErrors()) {

                StringBuilder validationErrors = new StringBuilder();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    validationErrors.append(error.getDefaultMessage()).append(", ");
                }
                return ResponseEntity.badRequest().body("Form data validation failed: " + validationErrors);
            }

            // Retrieve the original stored form data from the session
            FormInput originalData = (FormInput) session.getAttribute("formInput");
            //Retrieve the stored form data primary key
            Long formPrimaryKey = (Long) session.getAttribute("formPrimaryKey");
            String user = newFormInput.getName();
            originalData.setName(newFormInput.getName());
            originalData.setSelectedOptions(newFormInput.getSelectedOptions());
            originalData.setAgreedToTerms(newFormInput.isAgreedToTerms());
            formInputService.updateFormData(originalData, formPrimaryKey);
            return ResponseEntity.ok(user + "<br>" + "Your form data was successfully updated");

        } else {
            return ResponseEntity.badRequest().body("Invalid session");
        }
    }
}

