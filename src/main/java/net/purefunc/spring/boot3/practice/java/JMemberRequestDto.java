package net.purefunc.spring.boot3.practice.java;

import java.time.Instant;

public record JMemberRequestDto(
        String name,
        String email,
        Integer birth
) {

    public JMemberPo toPo() {
        return new JMemberPo(
                null,
                name,
                email,
                birth,
                Instant.now().toEpochMilli()
        );
    }
}
