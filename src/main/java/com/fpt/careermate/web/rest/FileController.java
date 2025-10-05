package com.fpt.careermate.web.rest;

import com.cloudinary.Cloudinary;
import com.fpt.careermate.services.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class FileController {

    @Value("${cloudinary.cloud-name:demo}")
    String cloudName;

    @Value("${cloudinary.api-key:demo}")
    String apiKey;

    @Value("${cloudinary.api-secret:demo}")
    String apiSecret;

    // ADMIN ONLY - Image Upload to Cloudinary
    @PostMapping("/upload/image")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Map<String, Object>> uploadImage(@RequestParam("image") MultipartFile file) {
        log.info("Uploading image to Cloudinary: {}", file.getOriginalFilename());

        // Validate file type
        if (!isImageFile(file)) {
            return ApiResponse.<Map<String, Object>>builder()
                    .code(1004)
                    .message("Only image files are allowed")
                    .build();
        }

        try {
            // Initialize Cloudinary
            Map<String, String> config = new HashMap<>();
            config.put("cloud_name", cloudName);
            config.put("api_key", apiKey);
            config.put("api_secret", apiSecret);
            config.put("secure", "true");

            Cloudinary cloudinary = new Cloudinary(config);

            // Upload parameters
            Map<String, Object> params = new HashMap<>();
            params.put("folder", "careermate/blogs");
            params.put("resource_type", "image");

            // Upload to Cloudinary
            @SuppressWarnings("unchecked")
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), params);

            // Prepare response
            Map<String, Object> result = new HashMap<>();
            result.put("imageUrl", uploadResult.get("secure_url"));
            result.put("publicId", uploadResult.get("public_id"));
            result.put("fileSize", file.getSize());
            result.put("originalName", file.getOriginalFilename());
            result.put("width", uploadResult.get("width"));
            result.put("height", uploadResult.get("height"));

            log.info("Image uploaded successfully: {}", uploadResult.get("public_id"));

            return ApiResponse.<Map<String, Object>>builder()
                    .code(1000)
                    .message("Image uploaded successfully to Cloudinary")
                    .result(result)
                    .build();

        } catch (Exception e) {
            log.error("Cloudinary upload failed: {}", e.getMessage());
            return ApiResponse.<Map<String, Object>>builder()
                    .code(1005)
                    .message("Upload failed: " + e.getMessage())
                    .build();
        }
    }

    @DeleteMapping("/images/{publicId:.+}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<String> deleteImage(@PathVariable String publicId) {
        try {
            // Decode URL-encoded public_id
            String decodedPublicId = URLDecoder.decode(publicId, StandardCharsets.UTF_8);
            log.info("Deleting Cloudinary image: {}", decodedPublicId);

            // Initialize Cloudinary with cleaner syntax
            Cloudinary cloudinary = new Cloudinary(Map.of(
                    "cloud_name", cloudName,
                    "api_key", apiKey,
                    "api_secret", apiSecret,
                    "secure", "true"));

            // Remove file extension if present (.jpg, .png, etc.) - Cloudinary needs
            // publicId without extension
            String cleanPublicId = decodedPublicId.replaceFirst("\\.[^.]+$", "");

            // If publicId doesn't contain folder path, prepend the default folder
            // This is a fallback for legacy support, but frontend should send full path
            if (!cleanPublicId.contains("/")) {
                cleanPublicId = "careermate/blogs/" + cleanPublicId;
                log.info("Prepending default folder path to publicId: {}", cleanPublicId);
            }

            // Delete from Cloudinary
            @SuppressWarnings("unchecked")
            Map<String, Object> result = cloudinary.uploader().destroy(cleanPublicId, Map.of());

            String resultStatus = (String) result.get("result");
            log.info("Cloudinary delete result for {}: {}", cleanPublicId, resultStatus);

            if ("ok".equals(resultStatus)) {
                return ApiResponse.<String>builder()
                        .code(1000)
                        .message("Image deleted successfully")
                        .result("Deleted: " + cleanPublicId)
                        .build();
            } else {
                log.warn("Cloudinary delete returned status: {} for publicId: {}", resultStatus, cleanPublicId);
                return ApiResponse.<String>builder()
                        .code(1006)
                        .message("Image not found or already deleted")
                        .result("Status: " + resultStatus)
                        .build();
            }

        } catch (Exception e) {
            log.error("Cloudinary delete failed", e);
            return ApiResponse.<String>builder()
                    .code(1005)
                    .message("Delete failed: " + e.getMessage())
                    .build();
        }
    }

    // Alternative delete endpoint using request body instead of path variable
    @DeleteMapping("/images")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<String> deleteImageByBody(@RequestBody Map<String, String> request) {
        try {
            String publicId = request.get("publicId");
            if (publicId == null || publicId.trim().isEmpty()) {
                return ApiResponse.<String>builder()
                        .code(1004)
                        .message("Public ID is required")
                        .build();
            }

            log.info("Deleting Cloudinary image via body: {}", publicId);

            // Initialize Cloudinary with cleaner syntax
            Cloudinary cloudinary = new Cloudinary(Map.of(
                    "cloud_name", cloudName,
                    "api_key", apiKey,
                    "api_secret", apiSecret,
                    "secure", "true"));

            // Remove file extension if present (.jpg, .png, etc.) - Cloudinary needs
            // publicId without extension
            String cleanPublicId = publicId.replaceFirst("\\.[^.]+$", "");

            // If publicId doesn't contain folder path, prepend the default folder
            // This is a fallback for legacy support, but frontend should send full path
            if (!cleanPublicId.contains("/")) {
                cleanPublicId = "careermate/blogs/" + cleanPublicId;
                log.info("Prepending default folder path to publicId: {}", cleanPublicId);
            }

            // Delete from Cloudinary
            @SuppressWarnings("unchecked")
            Map<String, Object> result = cloudinary.uploader().destroy(cleanPublicId, Map.of());

            String resultStatus = (String) result.get("result");
            log.info("Cloudinary delete result for {}: {}", cleanPublicId, resultStatus);

            if ("ok".equals(resultStatus)) {
                return ApiResponse.<String>builder()
                        .code(1000)
                        .message("Image deleted successfully")
                        .result("Deleted: " + cleanPublicId)
                        .build();
            } else {
                log.warn("Cloudinary delete returned status: {} for publicId: {}", resultStatus, cleanPublicId);
                return ApiResponse.<String>builder()
                        .code(1006)
                        .message("Image not found or already deleted")
                        .result("Status: " + resultStatus)
                        .build();
            }

        } catch (Exception e) {
            log.error("Cloudinary delete failed", e);
            return ApiResponse.<String>builder()
                    .code(1005)
                    .message("Delete failed: " + e.getMessage())
                    .build();
        }
    }

    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }
}