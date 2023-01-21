package net.purefunc.spring.boot3.practice.java;

import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/java/api/v1.0")
public class JMemberController {

    private final JsonUtil jsonUtil;
    private final ReactiveStringRedisTemplate reactiveStringRedisTemplate;
    private final JMemberDao jMemberDao;

    public JMemberController(JsonUtil jsonUtil, ReactiveStringRedisTemplate reactiveStringRedisTemplate, JMemberDao jMemberDao) {
        this.jsonUtil = jsonUtil;
        this.reactiveStringRedisTemplate = reactiveStringRedisTemplate;
        this.jMemberDao = jMemberDao;
    }

    @PostMapping("/members:register")
    public Mono<ResponseEntity<JMemberResponseDto>> register(@RequestBody JMemberRequestDto jMemberRequestDto) {
        return jMemberDao
                .save(jMemberRequestDto.toPo())
                .zipWhen(v ->
                        reactiveStringRedisTemplate
                                .opsForValue()
                                .set(v.email(), jsonUtil.write(v), Duration.ofMinutes(5))
                )
                .map(v -> v.getT1().toResponseDto())
                .map(ResponseEntity::ok);
    }

    @GetMapping("/members")
    Mono<ResponseEntity<JMemberResponseDto>> get(@RequestParam String email) {
        return reactiveStringRedisTemplate
                .opsForValue()
                .get(email)
                .switchIfEmpty(jMemberDao.findByEmail(email).map(jsonUtil::write))
                .map(v -> ResponseEntity.ok(jsonUtil.read(v, JMemberPo.class).toResponseDto()));
    }

    @GetMapping("/members:agg")
    Flux<JMemberVo> getAgg() {
        return jMemberDao.findAgg();
    }
}
