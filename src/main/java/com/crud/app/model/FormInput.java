package com.crud.app.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
public class FormInput implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Select at least one option")
    private String[] selectedOptions;
    @Column(nullable = false)
    @AssertTrue(message = "Must agree to terms")
    private boolean agreedToTerms;


    public FormInput() {

    }

    public FormInput(String name, String[] selectedOptions, boolean agreedToTerms) {
        this.name = name;
        this.selectedOptions = selectedOptions;
        this.agreedToTerms = agreedToTerms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(String[] selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public boolean isAgreedToTerms() {
        return agreedToTerms;
    }

    public void setAgreedToTerms(boolean agreedToTerms) {
        this.agreedToTerms = agreedToTerms;
    }
}
