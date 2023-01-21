package net.purefunc.spring.boot3.practice.java;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JMemberDao extends ReactiveCrudRepository<JMemberPo, Long> {

    Mono<JMemberPo> findByEmail(String email);

    @Query(value = """
            SELECT
                jm.name,
                count(jm.id)
            FROM
                jmember jm
            GROUP BY
                jm.name
            """
    )
    Flux<JMemberVo> findAgg();
}
