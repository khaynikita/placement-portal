package com.nikita.placementportal.dto.job;

import com.nikita.placementportal.entity.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentJobDTO {
    private Job job;
    private MatchResultDTO match;
}
