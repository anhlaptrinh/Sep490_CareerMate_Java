package com.fpt.careermate.common.util;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {
}
