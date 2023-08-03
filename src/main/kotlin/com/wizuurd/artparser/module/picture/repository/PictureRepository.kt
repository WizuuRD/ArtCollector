package com.wizuurd.artparser.module.picture.repository

import com.wizuurd.artparser.model.Picture
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PictureRepository: JpaRepository<Picture, Long> {


    @Query("select p.urlPost from Picture p where p.urlPost in :urls")
    fun findAllUrlsByUrls(urls: List<String>): List<String>

}