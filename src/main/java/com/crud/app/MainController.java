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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


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
    // serve my homepage and save session data to db
    @GetMapping(value = "/home", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public byte[] getApiPage(HttpSession session) throws IOException {
        session.setAttribute("key", "initial-value");
        Resource resource = new ClassPathResource("static/index.html");
        return Files.readAllBytes(resource.getFile().toPath());
    }
    @GetMapping(value = "/all")
    public List<WebData> getAllWebData() {
        return webDataService.getAllWebData();
    }

    @PostMapping(value = "/submit")
    @ResponseBody
    public ResponseEntity<?> saveFormInput(@Valid @RequestBody FormInput formInput) {

        formInputService.insertFormInput(formInput.getName(), formInput.getSelectedOptions(), formInput.isAgreedToTerms());
        return ResponseEntity.ok("Form is valid");

    }

}