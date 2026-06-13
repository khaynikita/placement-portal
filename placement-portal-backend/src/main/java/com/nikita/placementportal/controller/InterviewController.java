package com.nikita.placementportal.controller;

import com.nikita.placementportal.dto.interview.InterviewRequestDTO;
import com.nikita.placementportal.entity.Interview;
import com.nikita.placementportal.entity.User;
import com.nikita.placementportal.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InterviewController {
    private final InterviewService interviewService;

    @PostMapping("/recruiter/interviews")
    public Interview schedule(@RequestBody InterviewRequestDTO request, Authentication authentication) {
        User recruiter = (User) authentication.getPrincipal();
        return interviewService.scheduleInterview(request, recruiter.getId());
    }

    @GetMapping("/student/interviews/upcoming")
    public List<Interview> upcoming(Authentication authentication) {
        User student = (User) authentication.getPrincipal();
        return interviewService.upcoming(student.getId());
    }

    @DeleteMapping("/recruiter/interviews/{id}")
    public void cancel(@PathVariable String id) {
        interviewService.cancel(id);
    }

    @PatchMapping("/recruiter/interviews/{id}/complete")
    public Interview complete(@PathVariable String id) {
        return interviewService.complete(id);
    }
}
