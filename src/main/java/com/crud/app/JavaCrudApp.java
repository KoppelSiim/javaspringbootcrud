package com.crud.app;

import com.crud.app.service.WebDataService;
import com.crud.app.webscrape.WebsiteScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.jsoup.nodes.Document;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

@SpringBootApplication
public class JavaCrudApp  implements ApplicationRunner {
    private final WebDataService webDataService;
    @Autowired
    public JavaCrudApp(WebDataService webDataService) {
        this.webDataService = webDataService;
    }
    public static void main(String[] args) {

        SpringApplication.run(JavaCrudApp.class, args);
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

        WebsiteScraper scraper = new WebsiteScraper();

        Map<String, Integer> optionTextsWithData = null;
        try {
            URL url = new URL("https://www.helmes.com/wp-content/uploads/2023/06/index.html");
            Document document = scraper.getDocumentFromURL(url);
            optionTextsWithData = scraper.extractOptionTextsData(document);

        } catch (IOException e) {
            e.printStackTrace();

        }
        for (Map.Entry<String, Integer> entry : optionTextsWithData.entrySet()) {
            String optionText = entry.getKey();
            int identation= entry.getValue();
            
        }

    }
}
