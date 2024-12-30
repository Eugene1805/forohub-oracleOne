package com.example.api.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record TopicData(
        @NotBlank
        String topic,
        @NotBlank
        String title,
        @NotBlank
        String content
) {
}