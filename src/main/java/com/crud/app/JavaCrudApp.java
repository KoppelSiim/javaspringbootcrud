package com.crud.app;

import com.crud.app.service.WebDataService;
import com.crud.app.webscrape.WebsiteScraper.OptionData;
import com.crud.app.webscrape.WebsiteScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.jsoup.nodes.Document;


import java.io.IOException;
import java.net.URL;
import java.util.List;

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
    public void run(ApplicationArguments args) {

        WebsiteScraper scraper = new WebsiteScraper();
        List<OptionData> optionDataList = null;

        try {
            URL url = new URL("https://www.helmes.com/wp-content/uploads/2023/06/index.html");
            Document document = scraper.getDocumentFromURL(url);
            optionDataList= scraper.extractOptionData(document);

        } catch (IOException e) {
            e.printStackTrace();

        }
        if (optionDataList == null) {
            throw new NullPointerException("optionDataList is null");
        }
        for (OptionData optionData : optionDataList) {
            webDataService.insertOptionsData(optionData.optionText(),optionData.optionValue(),optionData.nbspCount());
        }


    }

}
