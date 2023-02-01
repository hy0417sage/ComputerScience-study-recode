# Android 4대 컴포넌트
### 안드로이드를 구성하는 핵심 요소 4가지
1. 액티비티 (Activity)
2. 서비스 (Service)
3. 브로드캐스트 리시버 (Broadcast Receiver)
4. 콘텐트 프로바이더 (Content Provider)
- 이 구성요소는 시스템이나 사용자가 앱에 들어갈 수 있는 진입점이 될 수 있으며, 일부 구성요소는 다른 구성 요소에 의존한다. 
- 각 유형은 고유한 용도로 활용되며 구성 요소가 생성되고 소명되는 방식을 정의하는 고유한 수명주기가 있다. 

## 안드로이드는 이 4가지 구성요소를 한 뭉태기로 엮은 번들 형태다.
- 번들이란? [Bundle](ttps://kotlinworld.com/45)
    - Map 형태로 구현된 데이터의 묶음
- 이 구성요소들은 각각 개발자에 의해 정의된 자기 할일을 수행한다.

## 안드로이드의 시작점은 정해져 있지 않다.
- 안드로이드 앱은 다른 일반 프로그램들과 달리, 코드상으로 시작점이 정해져 있지 않다. 
- 대신, 안드로이드 앱 동작의 첫 시작점은 무조건 이 4가지 구성요소 중 1가지로 지정된다. 
- 공식 문서에선 'Each component is an entry point through which the system or a user can enter your app (각 컴포넌트는 시스템이나 사용자가 앱에 진입할 수 있는 진입점이다)' 라고 표현한다.

## 그럼 이들이 각각 어떤 역할을 하는지 하나씩 알아보자🔍
1. [액티비티](/Android/01%20%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C%204%EB%8C%80%20%EC%BB%B4%ED%8F%AC%EB%84%8C%ED%8A%B8/1-1%20%EC%95%A1%ED%8B%B0%EB%B9%84%ED%8B%B0%20(22-09-12).md)
2. [서비스](/Android/01%20%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C%204%EB%8C%80%20%EC%BB%B4%ED%8F%AC%EB%84%8C%ED%8A%B8/1-2%20%EC%84%9C%EB%B9%84%EC%8A%A4%20(22-09-13).md)
3. [브로드캐스트 리시버](/Android/01%20%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C%204%EB%8C%80%20%EC%BB%B4%ED%8F%AC%EB%84%8C%ED%8A%B8/1-3%20%EB%B8%8C%EB%A1%9C%EB%93%9C%EC%BA%90%EC%8A%A4%ED%8A%B8%20%EB%A6%AC%EC%8B%9C%EB%B2%84%20(22-09-14).md)
4. [컨텐츠 프로바이더](/Android/01%20%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C%204%EB%8C%80%20%EC%BB%B4%ED%8F%AC%EB%84%8C%ED%8A%B8/1-4%20%EC%BB%A8%ED%85%90%EC%B8%A0%20%ED%94%84%EB%A1%9C%EB%B0%94%EC%9D%B4%EB%8D%94%20(22-09-15).md)


### 참고
- https://developer.android.com/guide/components/fundamentals#Components
- https://developer.android.com/guide/components/intents-filters
- https://developer.android.com/guide/components/activities/activity-lifecycle
- https://developer.android.com/guide/components/fragments
- https://developer.android.com/guide/components/services
- https://developer.android.com/guide/components/broadcasts