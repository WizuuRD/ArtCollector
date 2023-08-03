package com.wizurd.deviantparser.module.picture.mapper

import com.wizurd.deviantparser.client.deviant.DeviantApi
import com.wizurd.deviantparser.client.deviant.dto.DeviantResultsDTO
import com.wizurd.deviantparser.model.Picture
import org.springframework.stereotype.Component
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.net.URL

@Component
class PictureMapper(
    private val deviantApi: DeviantApi
) {

    fun fromGalleryDTOToPicture(token: String, dto: List<DeviantResultsDTO>): List<Picture> {
        return dto
            .filter { it.isDownloadable ?: false }
            .map { result ->
                val id = result.deviationId ?: throw NoSuchElementException()
                val content = deviantApi.getContentPyPicture(token, id)
                Picture(
                    urlPost = result.url.orEmpty(),
                    title = result.title.orEmpty(),
                    content = downloadFile(content.src),
                    fileName = content.fileName,
                )
            }
    }

    private fun downloadFile(urlString: String): ByteArray {
        try {
            val url = URL(urlString)
            val connection = url.openConnection()

            val byteArrayOutputStream = ByteArrayOutputStream()
            BufferedInputStream(connection.getInputStream()).use { inputStream ->
                val dataBuffer = ByteArray(1024)
                var bytesRead: Int
                while (inputStream.read(dataBuffer, 0, 1024).also { bytesRead = it } != -1) {
                    byteArrayOutputStream.write(dataBuffer, 0, bytesRead)
                }
            }

            return byteArrayOutputStream.toByteArray()

        } catch (e: Exception) {
            println("Error by downloading $urlString: ${e.message}")
        }
        return byteArrayOf()
    }
}