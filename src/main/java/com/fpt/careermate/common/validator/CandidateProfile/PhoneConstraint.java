package com.fpt.careermate.common.validator.CandidateProfile;

import jakarta.validation.Payload;

public @interface PhoneConstraint {
    String message() default "Invalid password";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
