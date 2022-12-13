# viewBinding 정의
- 뷰와 상호작용하는 코드(findViewById)를 보다 쉽게 작성할 수 있는 기능이다.

## viewBinding 을 사용하는 이유
- findViewById를 사용할 필요가 없다.
- Type-Safe 레이아웃 내에서 정확한 view 타입을 찾아 맵핑한다.
- Null-Safe 레이아웃에 없는 id를 findViewById를 했을 때의 NPE를 방지해준다.


### viewBinding 사용 방법
- 모듈 수준 gradle 파일에 추가
~~~
android{
	...
    buildFeatures {
         viewBinding true
    }
}
~~~