package com.nikita.placementportal.controller;

import com.nikita.placementportal.dto.application.ApplicationResponseDTO;
import com.nikita.placementportal.dto.application.UpdateApplicationStatusDTO;
import com.nikita.placementportal.entity.User;
import com.nikita.placementportal.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApplicationApiController {

    private final ApplicationService applicationService;

    @GetMapping("/student/applications")
    public List<ApplicationResponseDTO> getStudentApplications(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return applicationService.getMyApplications(user.getId());
    }

    @PatchMapping("/student/applications/{id}/withdraw")
    public ApplicationResponseDTO withdrawApplication(
            @PathVariable String id,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();

        return applicationService.withdraw(id, user.getId());
    }

    @GetMapping("/recruiter/applicants")
    public List<ApplicationResponseDTO> getRecruiterApplicants() {
        return applicationService.getAllApplications();
    }

    @PatchMapping("/recruiter/applications/{id}/status")
    public ApplicationResponseDTO updateApplicationStatus(
            @PathVariable String id,
            @RequestBody UpdateApplicationStatusDTO request
    ) {
        return applicationService.updateStatus(id, request.getStatus());
    }
}
