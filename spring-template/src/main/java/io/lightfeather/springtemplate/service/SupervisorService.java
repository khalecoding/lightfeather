package io.lightfeather.springtemplate.service;

import io.lightfeather.springtemplate.model.Supervisor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupervisorService {

    public List<String> getFormattedSupervisors(List<Supervisor> supervisors) {

        // filter out numeric jurisdictions
        List<Supervisor> filteredSupervisors = supervisors.stream()
                .filter(supervisor -> !supervisor.getJurisdiction().matches("\\d+"))
                .collect(Collectors.toList());

        // sorting in asc order by jurisdiction, then lastName, then firstName
        List<String> formatSupervisors = filteredSupervisors.stream()
                .map(supervisor -> supervisor.getJurisdiction() + " - " + supervisor.getLastName() + ", " + supervisor.getFirstName())
                .sorted()
                .collect(Collectors.toList());

        return formatSupervisors;
    }
}
