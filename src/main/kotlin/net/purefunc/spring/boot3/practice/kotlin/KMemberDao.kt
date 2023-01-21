package net.purefunc.spring.boot3.practice.kotlin

import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import reactor.core.publisher.Flux

interface KMemberDao : CoroutineCrudRepository<KMemberPo, Long> {

    suspend fun findByEmail(email: String): KMemberPo?

    @Query(value = """
        SELECT
            km.name,
            count(km.id)
        FROM
            kmember km
        GROUP BY
            km.name
    """
    )
    fun findAgg(): Flow<KMemberVo>
}