package com.kusitms.wannafly.query.dto;

import com.kusitms.wannafly.command.applicationform.domain.ApplicationForm;

import java.util.List;

public record ApplicationFormResponse(
        String recruiter,
        Integer year,
        String semester,
        List<ApplicationItemResponse> applicationItems
) {
    public static ApplicationFormResponse from(ApplicationForm applicationForm) {
        return new ApplicationFormResponse(
                applicationForm.getRecruiter().getValue(),
                applicationForm.getYear().getValue(),
                applicationForm.getSemester().name().toLowerCase(),
                applicationForm.getApplicationItems().getValues()
                        .stream()
                        .map(ApplicationItemResponse::from)
                        .toList()
        );
    }
}
