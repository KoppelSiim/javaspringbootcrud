package com.crud.app.service;

import com.crud.app.model.WebData;
import com.crud.app.repo.WebDataRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class WebDataService {
    private final WebDataRepo webdatarepository;

    @Autowired
    public WebDataService(WebDataRepo webdatarepository){
        this.webdatarepository = webdatarepository;
    }

    public void insertOptionsData(String optiontext, int identation){
        WebData webdata = new WebData();
        webdata.setOptionText(optiontext);
        webdata.setIdentation(identation);
        webdatarepository.save(webdata);
    }
}
