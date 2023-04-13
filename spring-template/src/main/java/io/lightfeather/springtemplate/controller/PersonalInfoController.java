package io.lightfeather.springtemplate.controller;

import io.lightfeather.springtemplate.model.PersonalInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PersonalInfoController {

    @PostMapping("/submit")
    public ResponseEntity<Void> printPersonalInfo(@RequestBody PersonalInfo personalInfo) {
        if (personalInfo.getFirstName().isEmpty() || personalInfo.getLastName().isEmpty()
        || personalInfo.getSupervisor().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        System.out.println("Submitted Personal Information");
        System.out.println(personalInfo);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
