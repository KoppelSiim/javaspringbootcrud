package com.crud.app.service;

import com.crud.app.model.WebData;
import com.crud.app.repository.WebDataRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class WebDataService {
    private final WebDataRepo webDataRepository;

    @Autowired
    public WebDataService(WebDataRepo webDataRepository){
        this.webDataRepository = webDataRepository;
    }

    public void insertOptionsData(String optionText, String optionValue, int indentation){
        WebData webData = new WebData();
        webData.setOptionText(optionText);
        webData.setOptionValue(optionValue);
        webData.setIndentation(indentation);
        webDataRepository.save(webData);
    }
    public List<WebData> getAllWebData() {
        return (List<WebData>) webDataRepository.findAll();
    }
}
