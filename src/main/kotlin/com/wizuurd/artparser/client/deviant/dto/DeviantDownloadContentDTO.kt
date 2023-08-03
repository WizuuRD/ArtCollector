package com.wizuurd.artparser.client.deviant.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class DeviantDownloadContentDTO(

    @JsonProperty("src"      ) var src      : String = "",
    @JsonProperty("filename" ) var fileName : String = "",
    @JsonProperty("width"    ) var width    : Int? = null,
    @JsonProperty("height"   ) var height   : Int? = null,
    @JsonProperty("filesize" ) var fileSize : Int? = null

)
