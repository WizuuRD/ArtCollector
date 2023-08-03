package com.wizuurd.artparser.module.util

import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class CrcHeaderGenerator {

    fun getAttachmentHeader(fileName: String): HttpHeaders {
        val header = HttpHeaders()
        header.add(HttpHeaders.CONTENT_DISPOSITION, encodeContentDisposition(fileName))
        header.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
        header.add(HttpHeaders.PRAGMA, "no-cache")
        header.add(HttpHeaders.EXPIRES, "0")
        header.add("Content-Type", "*/*; charset=utf-8")
        return header
    }

    private fun encodeContentDisposition(fileName: String) =
        ContentDisposition.builder("attachment")
            .filename(fileName, StandardCharsets.UTF_8)
            .build()
            .toString()

}