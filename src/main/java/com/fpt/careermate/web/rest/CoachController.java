package com.fpt.careermate.web.rest;

import com.fpt.careermate.services.CoachImp;
import com.fpt.careermate.services.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/coach")
@Tag(name = "Coach", description = "Generate course")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CoachController {

    CoachImp coachImp;

    @PostMapping("/course/generation")
    public ApiResponse<String> createRecruiter(@RequestParam String topic) {
        return ApiResponse.<String>builder()
                .result(coachImp.generateCourse(topic))
                .code(200)
                .message("success")
                .build();
    }

}
