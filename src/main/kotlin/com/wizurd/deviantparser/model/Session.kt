package com.wizurd.deviantparser.model

import com.wizurd.deviantparser.model.dictionary.EGrantType
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "t_session")
class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0L,

    @Column(name = "grant_type")
    @Enumerated(EnumType.STRING)
    val grantType: EGrantType = EGrantType.CLIENT_CREDENTIALS,

    @Column(name = "expires_in")
    var expiresIn: LocalDateTime,

    @Column(name = "access_token")
    var accessToken: String,

    @Column(name = "refresh_token")
    val refreshToken: String,

    @Column(name = "refresh_token_expires_in")
    val refreshTokenExpiresIn: LocalDateTime?,

    @Column(name = "code")
    val code: String? = null
)