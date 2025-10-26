package com.fpt.careermate.services;

import com.fpt.careermate.repository.CourseRepo;
import com.fpt.careermate.util.ApiClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.http.*;

import java.util.Map;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CoachImp  {
    String BASE_URL = "http://localhost:8000/api/v1/coach/";

    ApiClient apiClient;
    CourseRepo courseRepo;

    public String generateCourse(String topic) {
        String url = BASE_URL + "generate-course/";

        Map<String, String> body = Map.of("topic", topic);

        return apiClient.post(url, apiClient.getToken(), body);
    }

}