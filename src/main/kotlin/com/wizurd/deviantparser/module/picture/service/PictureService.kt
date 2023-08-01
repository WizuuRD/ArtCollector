package com.wizurd.deviantparser.module.picture.service

import com.wizurd.deviantparser.model.Picture
import com.wizurd.deviantparser.module.picture.repository.PictureRepository
import org.springframework.stereotype.Service

@Service
class PictureService(
    private var repo: PictureRepository
) {
    fun saveAll(pictures: List<Picture>): List<Picture> = repo.saveAll(pictures)

    fun findById(id: Long) = repo.findById(id)
}