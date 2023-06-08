# [Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview?hl=ko)
- 페이징이란 데이터를 가져올 때 한번에 모든 데이터를 가져오는 것이 아니라 일정한 덩어리로 나눠서 가져오는 것을 뜻한다.
- 서버-클라이언트 모델의 서버에서 페이징을 구현한 뒤, 클라이언트를 통해 사용자가 열람한 페이지의 정보를 보여주는 것이 될 수도 있다.
- 예시로, 카카오 api 를 사용해 어떤 키워드를 검색하게 되면 결과의 모든 데이터를 한 번에 가져오는 것이 아니라 페이지로 나누어 데이터를 가져올 수 있다.
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
