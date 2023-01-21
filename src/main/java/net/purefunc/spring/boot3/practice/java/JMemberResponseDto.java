package net.purefunc.spring.boot3.practice.java;

public record JMemberResponseDto(
        String name,
        String email,
        JChineseZodiac chineseZodiac,
        String createDate
) {
}
