package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.FieldError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.Valid;

import java.util.List;

@RestController
@RequestMapping("/newsflash")
public class HelloWorldController {

    @GetMapping
    public NewsFlash getNewsFlash() {
        return new NewsFlash("News 1", "Company A", "Lorem ipsum 1");
    }

    @PostMapping
    public ResponseEntity<String> postNewsFlash(@Valid @RequestBody NewsFlash newsFlash, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            StringBuilder errorResponse = new StringBuilder("Validation errors: ");
            for (FieldError error : errors) {
                errorResponse.append(error.getField()).append(" ").append(error.getDefaultMessage()).append(", ");
            }
            return new ResponseEntity<>(errorResponse.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Received NewsFlash: " +
                "Title=" + newsFlash.getTitle() + ", " +
                "Company=" + newsFlash.getCompany() + ", " +
                "Text=" + newsFlash.getText(), HttpStatus.OK);
    }
}