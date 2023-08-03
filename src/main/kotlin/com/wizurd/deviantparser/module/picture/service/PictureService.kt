package com.wizurd.deviantparser.module.picture.service

import com.wizurd.deviantparser.client.deviant.dto.DeviantResultsDTO
import com.wizurd.deviantparser.model.Picture
import com.wizurd.deviantparser.module.picture.repository.PictureRepository
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