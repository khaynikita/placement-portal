package com.nikita.placementportal.controller;

import com.nikita.placementportal.dto.application.ApplyJobDTO;
import com.nikita.placementportal.entity.Application;
import com.nikita.placementportal.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public Application applyToJob(
            @RequestBody ApplyJobDTO request
    ) {
        return applicationService.applyToJob(request);
    }

    @GetMapping("/student/{studentId}")
    public List<Application> getMyApplications(
            @PathVariable String studentId
    ) {
        return applicationService.getMyApplications(studentId);
    }
}