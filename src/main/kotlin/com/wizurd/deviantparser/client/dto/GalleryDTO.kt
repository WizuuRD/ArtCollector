package com.wizurd.deviantparser.client.dto

import com.google.gson.annotations.SerializedName

data class GalleryDTO (

  @SerializedName("has_more"    ) var hasMore    : Boolean?           = null,
  @SerializedName("next_offset" ) var nextOffset : String?            = null,
  @SerializedName("results"     ) var results    : ArrayList<Results> = arrayListOf()

)

data class Author (

  @SerializedName("userid"        ) var userid       : String?  = null,
  @SerializedName("username"      ) var username     : String?  = null,
  @SerializedName("usericon"      ) var usericon     : String?  = null,
  @SerializedName("type"          ) var type         : String?  = null,
  @SerializedName("is_subscribed" ) var isSubscribed : Boolean? = null

)

data class Body (

  @SerializedName("type"     ) var type     : String? = null,
  @SerializedName("features" ) var features : String? = null

)

data class Content (

  @SerializedName("src"          ) var src          : String?  = null,
  @SerializedName("height"       ) var height       : Int?     = null,
  @SerializedName("width"        ) var width        : Int?     = null,
  @SerializedName("transparency" ) var transparency : Boolean? = null,
  @SerializedName("filesize"     ) var filesize     : Int?     = null

)

data class Preview (

  @SerializedName("src"          ) var src          : String?  = null,
  @SerializedName("height"       ) var height       : Int?     = null,
  @SerializedName("width"        ) var width        : Int?     = null,
  @SerializedName("transparency" ) var transparency : Boolean? = null

)

data class Results (

  @SerializedName("deviationid"       ) var deviationid      : String?           = null,
  @SerializedName("printid"           ) var printid          : String?           = null,
  @SerializedName("url"               ) var url              : String?           = null,
  @SerializedName("title"             ) var title            : String?           = null,
  @SerializedName("category"          ) var category         : String?           = null,
  @SerializedName("category_path"     ) var categoryPath     : String?           = null,
  @SerializedName("is_favourited"     ) var isFavourited     : Boolean?          = null,
  @SerializedName("is_deleted"        ) var isDeleted        : Boolean?          = null,
  @SerializedName("is_published"      ) var isPublished      : Boolean?          = null,
  @SerializedName("is_blocked"        ) var isBlocked        : Boolean?          = null,
  @SerializedName("author"            ) var author           : Author?           = Author(),
  @SerializedName("stats"             ) var stats            : Stats?            = Stats(),
  @SerializedName("published_time"    ) var publishedTime    : String?           = null,
  @SerializedName("allows_comments"   ) var allowsComments   : Boolean?          = null,
  @SerializedName("preview"           ) var preview          : Preview?          = Preview(),
  @SerializedName("content"           ) var content          : Content?          = Content(),
  @SerializedName("thumbs"            ) var thumbs           : ArrayList<Thumbs> = arrayListOf(),
  @SerializedName("is_mature"         ) var isMature         : Boolean?          = null,
  @SerializedName("is_downloadable"   ) var isDownloadable   : Boolean?          = null,
  @SerializedName("download_filesize" ) var downloadFilesize : Int?              = null

)

data class Stats (

  @SerializedName("comments"   ) var comments   : Int? = null,
  @SerializedName("favourites" ) var favourites : Int? = null

)

data class TextContent (

  @SerializedName("excerpt" ) var excerpt : String? = null,
  @SerializedName("body"    ) var body    : Body?   = Body()

)

data class Thumbs (

  @SerializedName("src"          ) var src          : String?  = null,
  @SerializedName("height"       ) var height       : Int?     = null,
  @SerializedName("width"        ) var width        : Int?     = null,
  @SerializedName("transparency" ) var transparency : Boolean? = null

)