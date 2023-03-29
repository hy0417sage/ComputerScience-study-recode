# 리사이클러뷰의 내부 동작

## ListView가 아닌 RecyclerView 를 사용하는 이유
- 스크롤시 버벅임
- 기본 애니메이션 지원이 없음
- 수직 스크롤만 가능

## 리사이클러 뷰란?
- 스크롤 리스트를 만들 수 있는 UI 컴포넌트 이다.
- **Viewholder 패턴**을 사용하여 수평/수직/그리드 또는 staggerd한 그리드 방식으로 **어댑터 기반의 뷰를 렌더링** 하는데 사용되는 새로운 ViewGroup이다.

## RecyclerView의 4가지 주요 컴포넌트
1. RecyclerView.**Adapter** 
    - 앱의 데이터셋에서 RecyclerView에 표시되는 아이템 뷰에 바인딩을 제공한다. 
    - 어댑터는 RecyclerView의 각 아이템 뷰의 위치를 데이터 소스의 특정 위치에 연결하는 방법을 알고 있다.
2. RecyclerView.**LayoutManager**
    - RecyclerView내에 아이템을 배치합니다. 미리 정의된 여러 가지 레이아웃 매니저 중 하나를 사용하거나 커스텀 레이아웃 매니저를 구현하여 사용할 수 있습니다. 
    - 리니어 또는 그리드 레이아웃 매니저를 기본으로 사용할 수 있습니다.
3. RecyclerView.**ItemAnimator**
    - RecyclerView에는 기본 애니메이션이 함께 제공되며, 이를 오버라이드하고 필요에 따라 변경할 수 있습니다. 
    - 기본적으로 RecyclerView는 DefaultItemAnimator를 사용합니다.
4. RecyclerView.**ViewHolder** 
    - RecyclerView와 함께 의무적으로 사용해야하며, 화면에 그리고 싶은 개별적인 아이템의 UI를 그릴 수 있도록 도와줍니다.

## ViewHolder란?
- RecyclerView 내에 위치한 아이템 뷰와 메타데이터를 설명한다.
- 즉, **각 View를 보관하는 객체**로, 각 구성 요소를 저장하여 반복적으로 조회하지 않고도 즉시 엑세스 할 수 있다보면 됨

## ViewHolder Pattern이란?
- 각 뷰 객체를 뷰 홀더에 보관함으로써 findViewById()와 같은 반복적 호출 메서드를 줄여 효과적으로 속도 개선을 할 수 있는 패턴이다.
- 나오게 된 이유 : 데이터가 적을 경우, 빠르게 뷰의 구성 요소들을 findViewById()를 통해 조회해 적용시킬 수 있다. 하지만, 데이터가 많아질 경우 계속 findViewById()를 호출하다 보면 많은 비용을 요구(아래 설명 참고)하게 되어, 결국 호출을 이기지 못해 느려질 것이다.
이를 위해 나온게 뷰홀터 패턴

## 리사이클러뷰 필수 오버이딩 요소
- onCreateViewHolder = ViewHolder 객체를 생성
- onBindViewHolder = ViewHolder 에 data 를 넣는 작업 수행
- getItemCount = data의 갯수를 반환 해준다

## 리사이클러뷰에서 멀티뷰 구현 과정
- 멀티뷰를 구현하면, 다양한 뷰 타입을 가진 아이템을 효율적으로 표시할 수 있다.
1. **`뷰 타입 상수 정의`**하기 : 각 아이템 뷰마다 고유한 뷰 타입 상수(Int)를 정의한다. 
2. `**getItemViewType**` 메소드에서 각 아이템 뷰에 대한 뷰 타입을 반환한다. 이때 뷰 타입 상수를 반환한다.
3. `**onCreateViewHolder**` 메소드에서 뷰 홀더 객체 생성한다. 이때, 뷰 홀더에는 아이템 뷰가 포함 됨
4. **`onBindViewHolder`** 메소드에서 데이터를 뷰 홀더에 바인딩한다. 아이템 뷰에 텍스트, 이미지 등의 데이터를 표시한다.
5. `**getItemCount**` 아이템 개수를 반환. 모든 뷰타입에 대한 아이템 개수의 합을 반환

## DiffUtil과 ListAdapter
- `[DiffUtil](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil)은` 서로 다른 아이템인지를 체크하여 달라진 아이템만 갱신을 도와주는 Util이다.
- `[ListAdapter](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter)`는 Recyclerview 어댑터를 만들때 상속하여, 초기화할때 DiffUtil 콜백 객체를 받도록 하면  `[currentList](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter#getCurrentList%28%29)`로 현재 데이터를 불러올 수 있고, `[submitList](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter#submitList%28java.util.List%3CT%3E%29)`로 데이터를 갱신할 수 있음

## Equals와 hashCode, DiffUtil
만약 두개의 dataclass의 값이 동일하다고 보자 그럼 이 둘의 동일성의 결과는 어떻게 나오는가? → True가 된다. 이유는 data class는 equals와 hashcode를 정의하고 있기 때문이다.
→ 만약 class와 dataclass 의 값이 동일하다 동일성의 결과는? → false → class에서는 ==과 ===가 정의되어있지 않기 때문이다.