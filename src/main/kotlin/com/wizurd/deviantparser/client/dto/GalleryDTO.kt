package com.wizurd.deviantparser.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GalleryDTO (

  @JsonProperty("has_more"    ) var hasMore    : Boolean? = null,
  @JsonProperty("next_offset" ) var nextOffset : String? = null,
  @JsonProperty("results"     ) var results    : ArrayList<DeviantResultsDTO> = arrayListOf()

)

data class DeviantAuthorDTO (

  @JsonProperty("userid"        ) var userid       : String? = null,
  @JsonProperty("username"      ) var username     : String? = null,
  @JsonProperty("usericon"      ) var userIcon     : String? = null,
  @JsonProperty("type"          ) var type         : String? = null,
  @JsonProperty("is_subscribed" ) var isSubscribed : Boolean? = null

)

data class DeviantContentDTO (

  @JsonProperty("src"          ) var src          : String? = null,
  @JsonProperty("height"       ) var height       : Int? = null,
  @JsonProperty("width"        ) var width        : Int? = null,
  @JsonProperty("transparency" ) var transparency : Boolean? = null,
  @JsonProperty("filesize"     ) var fileSize     : Int? = null

)

data class DeviantPreviewDTO (

  @JsonProperty("src"          ) var src          : String? = null,
  @JsonProperty("height"       ) var height       : Int? = null,
  @JsonProperty("width"        ) var width        : Int? = null,
  @JsonProperty("transparency" ) var transparency : Boolean? = null

)

data class DeviantResultsDTO (

  @JsonProperty("deviationid"       ) var deviationId      : String? = null,
  @JsonProperty("printid"           ) var printId          : String? = null,
  @JsonProperty("url"               ) var url              : String? = null,
  @JsonProperty("title"             ) var title            : String? = null,
  @JsonProperty("category"          ) var category         : String? = null,
  @JsonProperty("category_path"     ) var categoryPath     : String? = null,
  @JsonProperty("is_favourited"     ) var isFavourited     : Boolean? = null,
  @JsonProperty("is_deleted"        ) var isDeleted        : Boolean? = null,
  @JsonProperty("is_published"      ) var isPublished      : Boolean? = null,
  @JsonProperty("is_blocked"        ) var isBlocked        : Boolean? = null,
  @JsonProperty("author"            ) var author           : DeviantAuthorDTO? = null,
  @JsonProperty("stats"             ) var stats            : DeviantStatsDTO? = null,
  @JsonProperty("published_time"    ) var publishedTime    : String? = null,
  @JsonProperty("allows_comments"   ) var allowsComments   : Boolean? = null,
  @JsonProperty("preview"           ) var preview          : DeviantPreviewDTO? = null,
  @JsonProperty("content"           ) var content          : DeviantContentDTO? = null,
  @JsonProperty("thumbs"            ) var thumbs           : ArrayList<DeviantThumbsDTO> = arrayListOf(),
  @JsonProperty("is_mature"         ) var isMature         : Boolean? = null,
  @JsonProperty("is_downloadable"   ) var isDownloadable   : Boolean? = null,
  @JsonProperty("download_filesize" ) var downloadFileSize : Int? = null

)

data class DeviantStatsDTO (

  @JsonProperty("comments"   ) var comments   : Int? = null,
  @JsonProperty("favourites" ) var favourites : Int? = null

)

data class DeviantThumbsDTO (

  @JsonProperty("src"          ) var src          : String? = null,
  @JsonProperty("height"       ) var height       : Int? = null,
  @JsonProperty("width"        ) var width        : Int? = null,
  @JsonProperty("transparency" ) var transparency : Boolean? = null

)