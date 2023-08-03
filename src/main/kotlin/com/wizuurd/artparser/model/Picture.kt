package com.wizuurd.artparser.model

import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "t_picture")
class Picture (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0L,

    @Column(name = "url")
    val urlPost: String = "",

    @Column(name = "title")
    val title: String = "",

    @Column(name = "content")
    @Lob @Basic(fetch = FetchType.LAZY)
    @Type(type = "org.hibernate.type.BinaryType")
    val content: ByteArray,

    @Column(name = "file_name")
    val fileName: String = ""
)