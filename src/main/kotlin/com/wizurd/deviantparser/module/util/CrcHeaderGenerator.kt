package com.wizurd.deviantparser.module.util

import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class CrcHeaderGenerator {

    /**
     * Генерирует хедер с параметром [HttpHeaders.CONTENT_DISPOSITION] для скачивания файлов
     *
     * Имя файла - передаваемый параметр [fileName]
     */
    fun getAttachmentHeader(fileName: String): HttpHeaders {
        val header = HttpHeaders()
        header.add(HttpHeaders.CONTENT_DISPOSITION, encodeContentDisposition(fileName))
        header.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
        header.add(HttpHeaders.PRAGMA, "no-cache")
        header.add(HttpHeaders.EXPIRES, "0")
        header.add("Content-Type", "*/*; charset=utf-8")
        return header
    }

    /**
     * Возвращает кодированный параметр заголовка [HttpHeaders.CONTENT_DISPOSITION]
     */
    private fun encodeContentDisposition(fileName: String) =
        ContentDisposition.builder("attachment")
            .filename(fileName, StandardCharsets.UTF_8)
            .build()
            .toString()

}