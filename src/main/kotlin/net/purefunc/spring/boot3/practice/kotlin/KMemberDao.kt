package net.purefunc.spring.boot3.practice.kotlin

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface KMemberDao: CoroutineCrudRepository<KMemberPo, Long> {

    suspend fun findByEmail(email: String): KMemberPo?
}