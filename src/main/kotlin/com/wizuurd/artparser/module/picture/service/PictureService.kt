package com.wizuurd.artparser.module.picture.service

import com.wizuurd.artparser.client.deviant.dto.DeviantResultsDTO
import com.wizuurd.artparser.model.Picture
import com.wizuurd.artparser.module.picture.repository.PictureRepository
import org.springframework.stereotype.Service

@Service
class PictureService(
    private var repo: PictureRepository
) {
    fun saveAll(pictures: List<Picture>): List<Picture> {
        val urls = repo.findAllUrlsByUrls(pictures.map { it.urlPost })
        return repo.saveAll(pictures.filter { !urls.contains(it.urlPost) })
    }

    fun isExistIn(pictures: List<DeviantResultsDTO>): List<DeviantResultsDTO> {
        val urls = repo.findAllUrlsByUrls(pictures.filter { it.url != null }.map { it.url!! })
        return pictures.filter { !urls.contains(it.url) }
    }

    fun findById(id: Long) = repo.findById(id)
}