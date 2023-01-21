package net.purefunc.spring.boot3.practice.kotlin

data class KMemberResponseDto(

    val name: String,
    val email: String,
    val chineseZodiac: KChineseZodiac,
    val createDate: String,
)