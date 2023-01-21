package net.purefunc.spring.boot3.practice.kotlin

import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("kmember")
data class KMemberPo(

    @Id
    val id: Long?,
    var name: String,
    var email: String,
    val createDate: Long,
) {

    fun toResponseDto(): KMemberResponseDto =
        KMemberResponseDto(
            name = name,
            email = email,
            createDate = DateTimeFormatter
                .ISO_ZONED_DATE_TIME
                .format(
                    OffsetDateTime.ofInstant(
                        Instant.ofEpochMilli(createDate),
                        ZoneOffset.ofHours(8)
                    )
                ),
        )
}