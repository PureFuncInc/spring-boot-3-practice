package net.purefunc.spring.boot3.practice.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Table("jmember")
public record JMemberPo(
        @Id
        Long id,
        String name,
        String email,
        Long createDate
) {

    JMemberResponseDto toResponseDto() {
        return new JMemberResponseDto(
                name,
                email,
                DateTimeFormatter.ISO_ZONED_DATE_TIME
                        .format(
                                OffsetDateTime.ofInstant(
                                        Instant.ofEpochMilli(createDate),
                                        ZoneOffset.ofHours(8)
                                )
                        )
        );
    }
}
