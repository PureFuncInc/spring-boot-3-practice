package net.purefunc.spring.boot3.practice.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public record JMemberResponseDto(
        String name,
        String email,
        String createDate
) {
}
