package com.wizuurd.artparser.web

import com.wizuurd.artparser.client.vk.VkClient
import com.wizuurd.artparser.model.dictionary.EGrantType
import com.wizuurd.artparser.module.picture.mapper.PictureMapper
import com.wizuurd.artparser.module.picture.service.PictureService
import com.wizuurd.artparser.module.session.service.SessionService
import com.wizuurd.artparser.module.util.CrcHeaderGenerator
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
    private val deviantApi: com.wizuurd.artparser.client.deviant.DeviantApi,
    private val pictureService: PictureService,
    private val pictureMapper: PictureMapper,
    private val crcHeader: CrcHeaderGenerator,
    private val sessionService: SessionService,
    private val vkClient: VkClient
) {

    @PostMapping("/auth")
    fun authorize(
        @RequestParam grantType: EGrantType,
    ) = sessionService.checkSessionForUpdate(grantType)

    @PostMapping("/pictures/parse")
    fun parsePictures(author: String) {
        val token = sessionService.checkSessionForUpdate(EGrantType.AUTHORIZATION_CODE)
        val pictures = pictureService.isExistIn(deviantApi.getPictures(token, author).results)
        pictureService.saveAll(pictureMapper.fromGalleryDTOToPicture(token, pictures))
    }

    @GetMapping("/pictures/parse")
    fun getPictures(author: String): List<String> {
        val token = sessionService.checkSessionForUpdate(EGrantType.AUTHORIZATION_CODE)
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

    @PostMapping("/vk/start")
    fun startVk() {
        vkClient.startPolling()
    }
}