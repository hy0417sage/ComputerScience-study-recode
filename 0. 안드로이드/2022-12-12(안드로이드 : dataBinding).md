# dataBinding 정의
- UI 요소와 데이터를 프로그램적 방식으로 연결하지 않고, **선언적 형식으로** 결합(연결된 특정 두 데이터 혹은 소스를 일치)할 수 있게 도와주는 라이브러리를 말한다.
    - 'XML 데이터 바인딩'과 'UI 데이터 바인딩'에서와 같이 **서로 다른 언어를 사용하는** 두 개의 데이터를 연결 지어준다!
    ~~~
    //선언적(declarative) 레이아웃 작성
    <TextView
        android:text="@{viewmodel.userName}" />
    ~~~
- bind란 단어의 사전적 의미는 '묶다', '감다'이다. 그럼 data binding을 '데이터 묶기'로 해석한다면 어디엔가 데이터를 묶어주는 것이라고 생각하면 될 듯!!



## databinding 을 사용하는 이유
1. LiveData와 같이 observe 변수와 함께 사용하는 경우, 데이터의 변경이 일어날 때 자연스럽게 연결된 UI도 업데이트시켜 UI와 데이터의 바인딩이 보장된다.
2. dataBinding을 작성하게 되면, findViewById와 같은 UI 프레임워크 호출이 사라져 데이터를 UI 요소에 연결하기 위해 필요한 코드를 최소화할 수 있다.
   - kotlin 코드 내에서 view를 호출하기 위해 따로 각 뷰를 불러오는 것이 아니라 DataBindingUtil과 같은 object를 이용해 binding class를 저장하고, 그 class를 이용해 각 뷰를 이용할 수 있어, 잘못된 id를 불러오는 실수등을 줄일 수 있다.
3. xml에 <data></data> 내부에 변수를 작성하는 것으로 xml에서 사용할 데이터를 kotlin class 내부에 의지하지 않고 작성할 수 있다.



### dataBinding 사용 방법
- 모듈 수준 gradle 파일에 추가
~~~
android{
	...
    buildFeatures {
         viewBinding true
    }
}
~~~

#### 참고한 블로그
- [데이터바인딩의 장/단점 토론](https://stackoverflow.com/questions/41462365/what-are-the-pros-and-cons-of-android-data-binding)
- [나이아카님의 AAC 데이터 바인딩이란?](https://no-dev-nk.tistory.com/82)
