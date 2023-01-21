package net.purefunc.spring.boot3.practice.kotlin

import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.math.abs
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("kmember")
data class KMemberPo(

    @Id
    val id: Long?,
    var name: String,
    var email: String,
    var birth: Int,
    val createDate: Long,
) {

    fun toResponseDto(): KMemberResponseDto =
        KMemberResponseDto(
            name = name,
            email = email,
            chineseZodiac = when (abs((birth / 10000) - 2002) % 12) {
                0 -> KChineseZodiac.RAT
                1 -> KChineseZodiac.OX
                2 -> KChineseZodiac.TIGER
                3 -> KChineseZodiac.RABBIT
                4 -> KChineseZodiac.DRAGON
                5 -> KChineseZodiac.SNAKE
                6 -> KChineseZodiac.HORSE
                7 -> KChineseZodiac.GOAT
                8 -> KChineseZodiac.MONKEY
                9 -> KChineseZodiac.ROOSTER
                10 -> KChineseZodiac.DOG
                else -> KChineseZodiac.PIG
            },
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