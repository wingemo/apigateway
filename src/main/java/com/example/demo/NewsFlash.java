package com.example.demo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class NewsFlash {
    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private final String title;

    @NotBlank(message = "Company cannot be blank")
    @Size(max = 255, message = "Company cannot exceed 255 characters")
    private final String company;

    @NotBlank(message = "Text cannot be blank")
    @Size(max = 1000, message = "Text cannot exceed 1000 characters")
    private final String text;

    public NewsFlash(String title, String company, String text) {
        this.title = title;
        this.company = company;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getText() {
        return text;
    }
}
