package com.nikita.placementportal.controller;

import com.nikita.placementportal.dto.application.ApplyJobDTO;
import com.nikita.placementportal.dto.application.ApplicationResponseDTO;
import com.nikita.placementportal.entity.Application;
import com.nikita.placementportal.entity.User;
import com.nikita.placementportal.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
    public List<ApplicationResponseDTO> getMyApplications(
            @PathVariable String studentId
    ) {
        return applicationService.getMyApplications(studentId);
    }

    @GetMapping("/my")
    public List<ApplicationResponseDTO> getAuthenticatedUserApplications(
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();

        return applicationService.getMyApplications(user.getId());
    }
}
