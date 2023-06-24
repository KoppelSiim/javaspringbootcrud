package com.crud.app;

import com.crud.app.model.WebData;
import com.crud.app.service.WebDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {
    private final ResourceLoader resourceLoader;
    private final WebDataService webDataService;
    @Autowired
    public MainController(ResourceLoader resourceLoader, WebDataService webDataService) {
        this.resourceLoader = resourceLoader;
        this.webDataService = webDataService;
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

}
