# [Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview?hl=ko)
- 페이징이란 데이터를 가져올 때 한번에 모든 데이터를 가져오는 것이 아니라 일정한 덩어리로 나눠서 가져오는 것을 뜻한다.
- 서버-클라이언트 모델의 서버에서 페이징을 구현한 뒤, 클라이언트를 통해 사용자가 열람한 페이지의 정보를 보여주는 것이 될 수도 있다.
- 예시로, 카카오 api 를 사용해 어떤 키워드를 검색하게 되면 결과의 모든 데이터를 한 번에 가져오는 것이 아니라 페이지로 나누어 데이터를 가져올 수 있다.
  - 스크롤이 끝까지 가는 경우, recyclerView adapter가 자동으로 다음 페이지를 로드하고 표시한다.
- 이러한 페이징 방식을 사용하면 앱에서 네트워크 대역폭과 시스템 리소스를 더 효율적으로 사용하기에 성능, 메모리, 비용 측면에서 효율적이다.

## Paging3의 장점
1. 앱이 페이징 데이터로 작업하는 동안 시스템 리소스를 효율적으로 사용할 수 있다.
2. 요청 중복 제거 기능이 기본으로 제공되어 네트워크 대역폭과 시스템 리소스를 효율적으로 사용할 수 있다.
2. 사용자가 로드된 데이터의 끝까지 스크롤 할 때 구성 가능한 RecyclerView 어댑터가 자공으로 데이터를 요청한다.
3. Kotlin 코루틴 및 Flow 뿐만 아니라 LiveData 및 RxJava 를 최고 수준으로 지원한다.
4. 새로고침 및 재시도 기능을 포함하여 오류 처리를 기본으로 지원한다.

## Paging3의 구조
- 라이브러리의 구성요소는 앱의 세가지 레이어에서 작동한다.
![image](https://github.com/hy0417sage/TIL/assets/97173983/3ee87651-b619-4ddf-82de-ab5684ddcebf)

1. Repository 저장소 계층
- PagingSource
  - Repository 계층의 기본이 되는 구성요소는 PagingSource이다.
  - 각 PagingSource 객체는 네트워크 소스 및 로컬 데이터베이스를 포함하여 전체 데이터로부터 부분적으로 데이터를 로드할 수 있다.
- RemoteMediator
  - RemoteMediator는 네트워크로 부터 받은 데이터를 로컬 DB를 통해 캐시하는 경우 페이징 하는데 함께 사용힐 수 있다.

- ViewModel 계층
  - pager는 PagingSource 및 PagingConfig 를 기반으로 반응형 스트림에서 사용되는 PagingData 인스턴스를 구성하기 위한 공용 API를 제공하는데 이 때 ViewModel 계층을 UI에 연결하는 구성요소는 PagingData 이다.
  - PagingData 개체는 페이지가 매겨진 데이터의 스냅샷을 위한 컨테이너로, PagingSource 개체를 퀴리하고 결과를 저장한다.
  
- UI 계층
  - UI 계층의 기본 Paging 라이브러리 구성 요소는 PagingDataAdapter로 페이지가 매겨진 데이터를 처리한다.
  - 만약 PagingDataAdapter가 아닌 RecyclerView.Adapter 등을 확장하는 커스텀 어댑터를 구현하려면 AsyncPagingDataDiffer를 사용할 수 있다.


## PagingSource 구현
- paging에서 PagingSoruce는 데이터 소스와 이 소스에서 데이터를 검색하는 방법을 정의한다.
- PagingSoruce 객체는 네트워크 소스 및 로컬 데이터베이스를 포함한 단일 소스에서 데이터를 로드할 수 있다.
~~~kotlin
class SearchItemPagingSource(
    private val pagingData: ApiPagingModel,
) : PagingSource<Int, SearchItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchItem> {
        pagingData.exception?.run {
            return LoadResult.Error(this)
        }
        val page = params.key ?: SEARCH_STARTING_PAGE_INDEX
        return try {
            val chunkedList = fetchImagesByPage(
                data = pagingData,
                page = page
            )
            LoadResult.Page(
                data = chunkedList,
                prevKey = if (page == SEARCH_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == (pagingData.searchItemList.size / PAGE_SIZE) + 1) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun fetchImagesByPage(data: ApiPagingModel, page: Int): List<SearchItem> {
        val chunkedList = data.searchItemList.chunked(PAGE_SIZE)
        return chunkedList[page - 1]
    }
}
~~~
- getRefreshKey()
  - 데이터의 업데이트 또는 새로고침을 할때 현재 리스트를 대체할 새로운 데이터를 로그할 때 사용이되는 함수입니다.   - 즉, 데이터를 새로고침할때 적절한 key 값을 반환해준다.
- load(params: LoadParams)
  - 실제 데이터를 로드하는 함수입니다. 데이터는 로컬 또는 remote에서 받아오는데 이 예제에서는 아까 설정했던 카카오 검색 API를 사용하였다. 여기서 withContext를 사용하여 IO 디스패처 코루틴에서 실행해 비동기로 데이터를 불러온다.
  - load에서 nextKey는 해당 다음 페이지를 로드할때 사용하는데 카카오 API에서 is_end 변수가 페이지 끝을 의미하기때문에 만약 is_end가 true라면 null값을 nextKey로 반환하여 더이상 페이지를 불러오지 않도록 설정합니다.
  - 이렇게 data, prevKey 그리고 nextKey를 LoadResult.Page()를 통해 리턴해준다.
  - paging 도중 네트워크 오류와 같이 오류가 발생할 수 있기 때문에 LoadResult.Error()를 통해 오류도 반환해준다.
    - LoadResult.Page : 로드에 성공한 경우, 데이터와 이전 다음 페이지 Key가 포함된다.
    - LoadResult.Error : 오류가 발생한 경우



### 참고
- [구글 공식](https://developer.android.com/topic/libraries/architecture/paging/v3-overview?hl=ko)
- [Paging 3, MVVM, Coroutine, Hilt, Flow를 사용해 RecyclerView 구현하기](https://velog.io/@eoqkrskfk94/Paging-3-MVVM-Coroutine-Hilt-Flow%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%B4-RecyclerView-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0)
