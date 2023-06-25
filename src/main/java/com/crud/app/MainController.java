package com.crud.app;

import com.crud.app.model.FormInput;
import com.crud.app.model.WebData;
import com.crud.app.service.FormInputService;
import com.crud.app.service.WebDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/static", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<Resource> serveStaticHTML() {
        Resource resource = resourceLoader.getResource("classpath:static/index.html");
        return ResponseEntity.ok().body(resource);
    }
    @GetMapping(value = "/all")
    public List<WebData> getAllWebData() {
        return webDataService.getAllWebData();
    }

   @PostMapping(value = "/submit")
   @ResponseBody
   public String saveFormInput(@RequestBody FormInput formInput){
    formInputService.insertFormInput(formInput.getName(), formInput.getSelectedOptions(),formInput.isAgreedToTerms());
       return "Form submitted successfully!";
   }


}
