package com.crud.app.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class WebData implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String optionText;
    private int identation;

    public WebData(){

    }
    public WebData(String optionText, int identation){
        this.optionText = optionText;
        this.identation = identation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public void setIdentation(int identation) {
        this.identation = identation;
    }

    public Long getId() {
        return id;
    }

    public String getOptionText() {
        return optionText;
    }

    public int getIdentation() {
        return identation;
    }

}
