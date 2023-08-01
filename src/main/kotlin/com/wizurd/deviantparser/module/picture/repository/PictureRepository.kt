package com.wizurd.deviantparser.module.picture.repository

import com.wizurd.deviantparser.model.Picture
import org.springframework.data.jpa.repository.JpaRepository

interface PictureRepository: JpaRepository<Picture, Long> {
}