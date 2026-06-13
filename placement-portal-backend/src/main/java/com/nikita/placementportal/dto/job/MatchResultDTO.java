package com.nikita.placementportal.dto.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchResultDTO {
    private int score;
    private List<String> matchedSkills;
    private List<String> missingSkills;
    private boolean cgpaMet;
    private boolean projectRelevant;
}
