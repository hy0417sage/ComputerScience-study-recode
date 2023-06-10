# Mapper

#### API
- 이미지 - https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-image
- 동영상 - https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-video

---


## SearchService
~~~kotlin
interface SearchService {
    @GET("/v2/search/image")
    suspend fun searchImages(
        @Query("sort") sort : String,
        @Query("query") query : String,
        @Query("page") page : Int,
        @Query("size") size : Int,
    ): BaseResponse<ApiImagesResponse>

    @GET("/v2/search/vclip")
    suspend fun searchVclips(
        @Query("sort") sort : String,
        @Query("query") query : String,
        @Query("page") page : Int,
        @Query("size") size : Int,
    ): BaseResponse<ApiVclipsResponse>
}
~~~



### BaseResponse
~~~kotlin
@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    @field:Json(name = "meta") val meta: Meta,
    @field:Json(name = "documents") val documents: List<T> //BaseResponse<ApiImageResponse>, BaseResponse<ApiVclipResponse>
) {
    @JsonClass(generateAdapter = true)
    data class Meta(
        @field:Json(name = "total_count") val total_count: Int = 0, // 검색된 문서 수
        @field:Json(name = "pageable_count") val pageable_count: Int = 0, // total_count 중 노출 가능 문서 수
        @field:Json(name = "is_end") val is_end: Boolean = true, // 현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음
    )
}
~~~

### ApiImageResponse
~~~kotlin
@JsonClass(generateAdapter = true)
data class ApiImagesResponse(
    @field:Json(name = "collection") val collection: String?, //컬렉션
    @field:Json(name = "thumbnail_url") val thumbnail_url: String?, //미리보기 이미지 URL
    @field:Json(name = "image_url") val image_url: String?, //이미지 URL
    @field:Json(name = "width") val width: Int?, //이미지의 가로 길이
    @field:Json(name = "height") val height: Int?, //이미지의 세로 길이
    @field:Json(name = "display_sitename") val display_sitename: String?, //출처
    @field:Json(name = "doc_url") val doc_url: String?, //문서 URL
    @field:Json(name = "datetime") val datetime: String, //문서 작성시간, ISO 8601 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
)
~~~

### ApiVclipResponse
~~~kotlin
@JsonClass(generateAdapter = true)
data class ApiVclipsResponse(
    @field:Json(name = "title") val title: String?, //동영상 제목
    @field:Json(name = "url") val url: String?, //동영상 링크
    @field:Json(name = "datetime") val datetime: String?, //동영상 등록일, ISO 8601 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
    @field:Json(name = "play_time") val play_time: Int?, //동영상 재생시간, 초 단위
    @field:Json(name = "thumbnail") val thumbnail: String?, //동영상 미리보기 URL
    @field:Json(name = "author") val author: String?, //동영상 업로더
)
~~~

---

## ApiMapper
~~~kotlin
class ApiMapper {

    fun imageToSearchItem(apiEntity: ApiImagesResponse?): SearchItem {
        return SearchItem(
            imageUrl = apiEntity?.thumbnail_url.orEmpty(),
            datetime = apiEntity?.datetime.orEmpty(),
        )
    }

    fun vclipToSearchItem(apiEntity: ApiVclipsResponse?): SearchItem {
        return SearchItem(
            imageUrl = apiEntity?.thumbnail.orEmpty(),
            datetime = apiEntity?.datetime.orEmpty(),
        )
    }
}
~~~

### SearchItem - 매핑 클래스
~~~kotlin
data class SearchItem(
    val imageUrl : String,
    val datetime : String,
)
~~~

---

# Repository
~~~
class SearchRepositoryImpl @Inject constructor(
    private val service: SearchService,
    private val apiMapper: ApiMapper,
): SearchRepository { 
  //페이징 처리
}
~~~
