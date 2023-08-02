package com.wizurd.deviantparser.module.session.service

import com.wizurd.deviantparser.client.DeviantApi
import com.wizurd.deviantparser.client.dto.TokenDTO
import com.wizurd.deviantparser.model.Session
import com.wizurd.deviantparser.model.dictionary.EGrantType
import com.wizurd.deviantparser.module.session.repository.SessionRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SessionService(
    @Value("\${app.deviant.code}")
    private val code: String,
    private val sessionRepository: SessionRepository,
    private val deviantApi: DeviantApi
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