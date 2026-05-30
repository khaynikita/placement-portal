package com.nikita.placementportal.dto.application;

import lombok.Data;

@Data
public class UpdateApplicationStatusDTO {

    private String applicationId;
    private String status;

}