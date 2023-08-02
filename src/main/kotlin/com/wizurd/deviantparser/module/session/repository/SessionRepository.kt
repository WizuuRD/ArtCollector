package com.wizurd.deviantparser.module.session.repository

import com.wizurd.deviantparser.model.Session
import org.springframework.data.jpa.repository.JpaRepository

interface SessionRepository: JpaRepository<Session, Long> {

    fun findFirstByOrderByExpiresInDesc(): Session?

}