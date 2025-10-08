package com.fpt.careermate.web.rest;

import com.fpt.careermate.services.SkillImp;
import com.fpt.careermate.services.dto.request.AccountCreationRequest;
import com.fpt.careermate.services.dto.response.AccountResponse;
import com.fpt.careermate.services.dto.response.ApiResponse;
import com.fpt.careermate.services.dto.response.SkillResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/skill")
@Tag(name = "Skill", description = "Manage skill")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SkillController {

    SkillImp skillImp;

    @PostMapping
    @Operation(summary = "Create skill")
    ApiResponse<String> createUser(
            @RequestParam
            @NotBlank
            String name
    ) {
        skillImp.createSkill(name);
        return ApiResponse.<String>builder()
                .code(200)
                .message("success")
                .build();
    }

    @GetMapping
    @Operation(summary = "Get skill list")
    ApiResponse<List<SkillResponse>> getSkillList() {
        return ApiResponse.<List<SkillResponse>>builder()
                .result(skillImp.getAllSkill())
                .code(200)
                .message("success")
                .build();
    }

}
