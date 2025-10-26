package com.fpt.careermate.services.recruiter_services.service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewRecruiterResponse {

    int id;
    String companyName;
    Float rating;

}
