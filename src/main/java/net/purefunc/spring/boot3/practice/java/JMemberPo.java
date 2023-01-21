package net.purefunc.spring.boot3.practice.java;

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
        Integer birth,
        Long createDate
) {

    JMemberResponseDto toResponseDto() {
        return new JMemberResponseDto(
                name,
                email,
                switch (((birth / 10000) + 8) % 12) {
                    case 0 -> JChineseZodiac.RAT;
                    case 1 -> JChineseZodiac.OX;
                    case 2 -> JChineseZodiac.TIGER;
                    case 3 -> JChineseZodiac.RABBIT;
                    case 4 -> JChineseZodiac.DRAGON;
                    case 5 -> JChineseZodiac.SNAKE;
                    case 6 -> JChineseZodiac.HORSE;
                    case 7 -> JChineseZodiac.GOAT;
                    case 8 -> JChineseZodiac.MONKEY;
                    case 9 -> JChineseZodiac.ROOSTER;
                    case 10 -> JChineseZodiac.DOG;
                    default -> JChineseZodiac.PIG;
                },
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
