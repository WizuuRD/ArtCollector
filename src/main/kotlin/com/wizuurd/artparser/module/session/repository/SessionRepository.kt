package com.wizuurd.artparser.module.session.repository

import com.wizuurd.artparser.model.Session
import org.springframework.data.jpa.repository.JpaRepository

interface SessionRepository: JpaRepository<Session, Long> {

    fun findFirstByOrderByExpiresInDesc(): Session?

}