package com.crud.app.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class WebData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String optionText;
    private String optionValue;
    private int indentation;

    public WebData() {

    }

    public WebData(String optionText, String optionValue, int indentation) {
        this.optionText = optionText;
        this.optionValue = optionValue;
        this.indentation = indentation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public void setIndentation(int indentation) {
        this.indentation = indentation;
    }

    public Long getId() {
        return id;
    }

    public String getOptionText() {
        return optionText;
    }

    public String getOptionValue(){
        return optionValue;
    }

    public int getIndentation() {
        return indentation;
    }

}
