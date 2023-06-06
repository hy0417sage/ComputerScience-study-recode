# TEST CODE
- 구현한 동작이 올바르게 동작하는지 검증하기 위해 테스트 코드를 작성
- 다양한 엣지 케이스들을 고려한 테스트 케이스를 코드로써 작성해두면 편리하게 동작의 무결성을 보장 할 수 있다.

## 안드로이드에서 테스트
- 단순히 화면에 보여지지 않는 테스트 뿐만 아니라 UI 인터렉션이 정상적으로 이루어지는지 검증하기 위한 UI 테스트 까지 고려해야 한다.

### Unit Test / 유닛 테스트
- 테스트 코드라고 했을 때 가장 일반적으로 생각하는 테스트
- 유닛 단위로 기능을 검증하는 것
  - 유닛이란 메소드, 클래스 등이 될 수 있다.
- 안드로이드에서는 JUnit, Mockito, PowerMock 등을 사용할 수 있다.

### UI Test
- 사용자와의 인터랙션을 검증하게 된다.
- 사용자 인터렉션은 버튼 클릭, 테스트 입력, 스크롤 등이 될 수 있다.

## 안드로이드 프로젝트에서의 테스트
안드로이드 스튜디오에서 프로젝트를 생성하면 테스트 관련 툴이 자동으로 추가된다. app 단위의 build.gradle 파일의 의존성 쪽을 보면 아래와 같은 구문들이 추가되어 있는 것을 볼 수 있다.
~~~
testImplementation 'junit:junit:4.+'
androidTestImplementation 'androidx.test.ext:junit:1.1.2'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
~~~
JUnit, Espresso 등 Unit 테스트 툴과 UI 테스트 툴이 기본적으로 탑재된다.
