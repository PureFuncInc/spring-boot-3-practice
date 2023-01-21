package net.purefunc.spring.boot3.practice.java;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface JMemberDao extends ReactiveCrudRepository<JMemberPo, Long> {

    Mono<JMemberPo> findByEmail(String email);
}
