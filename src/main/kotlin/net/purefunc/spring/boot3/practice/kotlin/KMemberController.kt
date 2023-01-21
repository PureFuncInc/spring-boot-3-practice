package net.purefunc.spring.boot3.practice.kotlin

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.time.Duration
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kotlin/api/v1.0")
class KMemberController(
    private val reactiveStringRedisTemplate: ReactiveStringRedisTemplate,
    private val kMemberDao: KMemberDao,
) {

    private val objectMapper = jacksonObjectMapper()

    @PostMapping("/members:register")
    suspend fun register(
        @RequestBody kMemberRequestDto: KMemberRequestDto
    ): ResponseEntity<KMemberResponseDto> =
        kMemberDao
            .save(kMemberRequestDto.toPo())
            .also {
                reactiveStringRedisTemplate
                    .opsForValue()
                    .set(it.email, objectMapper.writeValueAsString(it), Duration.ofMinutes(5))
                    .awaitFirst()
            }
            .toResponseDto()
            .let { ResponseEntity.ok(it) }

    @GetMapping("/members")
    suspend fun get(
        @RequestParam email: String,
    ): ResponseEntity<KMemberResponseDto> =
        reactiveStringRedisTemplate
            .opsForValue()
            .get(email)
            .awaitFirstOrNull()
            ?.let {
                ResponseEntity.ok(
                    objectMapper
                        .readValue(it, KMemberPo::class.java)
                        .toResponseDto()
                )
            }
            ?: kMemberDao
                .findByEmail(email)
                ?.let { ResponseEntity.ok(it.toResponseDto()) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/members:agg")
    suspend fun getAgg(): List<KMemberVo> = kMemberDao.findAgg().toList()
}