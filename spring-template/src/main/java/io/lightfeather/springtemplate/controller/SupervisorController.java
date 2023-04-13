package io.lightfeather.springtemplate.controller;

import io.lightfeather.springtemplate.model.Supervisor;
import io.lightfeather.springtemplate.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SupervisorController {
    private final SupervisorService supervisorService;

    @Autowired
    public SupervisorController(SupervisorService supervisorService) {
        this.supervisorService = supervisorService;
    }

    @GetMapping("/supervisors")
    public ResponseEntity<List<String>> getSupervisors() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Supervisor>> response = restTemplate.exchange(
                "https://o3m5qixdng.execute-api.us-east-1.amazonaws.com/api/managers",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Supervisor>>() {}
        );
        List<Supervisor> supervisors = response.getBody();

        List<String> formattedSupervisors = supervisorService.getFormattedSupervisors(supervisors);
        return new ResponseEntity<>(formattedSupervisors, HttpStatus.OK);
    }
}
