package com.wizurd.deviantparser.web

import com.wizurd.deviantparser.client.deviant.DeviantApi
import com.wizurd.deviantparser.model.dictionary.EGrantType
import com.wizurd.deviantparser.model.dictionary.EGrantType.*
import com.wizurd.deviantparser.module.picture.mapper.PictureMapper
import com.wizurd.deviantparser.module.picture.service.PictureService
import com.wizurd.deviantparser.module.session.service.SessionService
import com.wizurd.deviantparser.module.util.CrcHeaderGenerator
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.optionals.getOrNull

@OptIn(ExperimentalStdlibApi::class)
@RestController
@RequestMapping("api/testing/")
@Tag(name = "Testing work of service")
class RestController(
    private val deviantApi: DeviantApi,
    private val pictureService: PictureService,
    private val pictureMapper: PictureMapper,
    private val crcHeader: CrcHeaderGenerator,
    private val sessionService: SessionService
) {

    @PostMapping("/auth")
    fun authorize(
        @RequestParam grantType: EGrantType,
    ) = sessionService.checkSessionForUpdate(grantType)

    @PostMapping("/pictures/parse")
    fun parsePictures(author: String) {
        val token = sessionService.checkSessionForUpdate(AUTHORIZATION_CODE)
        pictureService.saveAll(pictureMapper.fromGalleryDTOToPicture(token, deviantApi.getPictures(token, author)))
    }

    @GetMapping("/pictures/parse")
    fun getPictures(author: String): List<String> {
        val token = sessionService.checkSessionForUpdate(AUTHORIZATION_CODE)
        val pictures = deviantApi.getPictures(token, author)

        return pictures.results.map { "${it.title.orEmpty()} - ${it.isDownloadable}, ${it.isMature}" }
    }

    @GetMapping("/pictures/{id}/download")
    fun downloadPicture(@PathVariable id: Long): ResponseEntity<ByteArrayResource> {
        val picture = pictureService.findById(id).getOrNull()

        return ResponseEntity.ok()
            .headers(crcHeader.getAttachmentHeader(picture?.fileName ?: "unknown"))
            .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
            .body(ByteArrayResource(picture?.content ?: byteArrayOf()))
    }
}