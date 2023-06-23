package com.crud.app;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class MainController {
    private final ResourceLoader resourceLoader;

    public MainController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    @GetMapping(value = "/static", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<Resource> serveStaticHTML() {
        Resource resource = resourceLoader.getResource("classpath:static/index.html");
        return ResponseEntity.ok().body(resource);
    }
    @PostMapping("/values")
    @ResponseBody
    public String values(@RequestBody List<String> optionValues) {
        // Process the option values and return a response
        System.out.println("Received option values: " + optionValues.toString());
        // Perform any necessary operations with the received option values

        return "AJAX POST request received! Option values: " + optionValues.toString();
    }

}
