package com.wizuurd.artparser.module.session.service

import com.wizuurd.artparser.client.deviant.dto.TokenDTO
import com.wizuurd.artparser.model.Session
import com.wizuurd.artparser.model.dictionary.EGrantType
import com.wizuurd.artparser.module.session.repository.SessionRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SessionService(
    @Value("\${app.deviant.code}")
    private val code: String,
    private val sessionRepository: SessionRepository,
    private val deviantApi: com.wizuurd.artparser.client.deviant.DeviantApi
) {

    fun checkSessionForUpdate(grantType: EGrantType): String {
        return if (grantType == EGrantType.REFRESH_TOKEN) {
            updateSession(grantType)
        } else {
            val lastToken = sessionRepository.findFirstByOrderByExpiresInDesc()
            if (lastToken == null) {
                createSession(grantType, deviantApi.getTokenByAuthorization())
            } else {
                if (lastToken.expiresIn <= LocalDateTime.now()) {
                    updateSession(EGrantType.REFRESH_TOKEN, lastToken)
                } else {
                    lastToken.accessToken
                }
            }
        }
    }

    fun updateSession(grantType: EGrantType, refreshToken: Session? = null): String {
        return when (grantType) {
            EGrantType.AUTHORIZATION_CODE -> {
                createSession(grantType, deviantApi.getTokenByAuthorization())
            }
            EGrantType.CLIENT_CREDENTIALS -> {
                createSession(grantType, deviantApi.getTokenByCredentials())
            }
            EGrantType.REFRESH_TOKEN -> {
                updateSession(refreshToken!!, deviantApi.getTokenByRefresh(refreshToken.refreshToken))
            }
        }
    }


    private fun createSession(grantType: EGrantType, session: TokenDTO): String {
        return sessionRepository.save(
            Session(
                grantType = grantType,
                expiresIn = LocalDateTime.now().plusSeconds(session.expiresIn.toLong()),
                accessToken = session.accessToken,
                refreshToken = session.refreshToken.orEmpty(),
                refreshTokenExpiresIn = if (session.refreshToken == null) null
                else LocalDateTime.now().plusMonths(3),
                code = code
            )
        ).accessToken
    }

    private fun updateSession(refreshToken: Session, session: TokenDTO): String {
        return sessionRepository.save(
            refreshToken.also {
                it.expiresIn = LocalDateTime.now().plusSeconds(session.expiresIn.toLong())
                it.accessToken = session.accessToken
            }
        ).accessToken
    }
}