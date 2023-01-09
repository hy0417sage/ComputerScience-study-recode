# Hilt란? (정의)
- 안드로이드 클래스에 생명주기를 고려한 의존성 주입 라이브러리이다. 

## Hilt를 사용하는 이유
- DI를 사용하는 이유와 + dagger에는 없는 안드로이드 생명주기를 고려한 라이브러리라는 점에서 사용한다.
    - Hilt는 dagger 위에 구축되어있다.
- 컴파일 타임 correctness, 런타임 퍼포먼스, scalability 측면에서 이득이다.
- 프로젝트에 있는 모든 안드로이드 클래스에 컨테이너를 제공하고 그들의 라이프사이클을 자동으로 관리해 준다.
- 의존성 그래프를 만들고 잘못된 의존성이나 의존성 사이클이 없는지 컴파일타임에 잡아준다.
- 런타임중에 실제 객체들과 그들의 의존성을 만들기위해 클래스를 생성해준다.

### Hilt 사용하는 방법
1. @HiltAndroidApp
    - 애플리케이션에 Hilt 코드를 자동으로 생성할 수 있게 한다.
    - Application의 확장 클래스에 붙여서 힐트의 코드를 생성할 수 있다.
~~~kotlin
@HiltAndroidApp
class MyApplication : Application() { ... }
~~~

2. @AndroidEntryPoint(component-scope)
    - 힐트 컴포넌트를 생성 (Abstract class 는 이 어노테이션이 필요없다.)
    - 해당 클래스의 생명주기를 따르는 의존성 컨테이너를 만든다.
3. @Inject constructor()
    - 생성자에 주입
    - 의존성으로서 인스턴스에게 타입을 제공하는데 필요한다.
    - 생성자에게 힐트가 그 클래스에 어떻게 인스턴스를 제공할지 알려준다.
4. @HiltViewModel 
    - ViewModel에 사용하는 어노테이션이다.
5. @Inject
    - 필드에 주입할 떄 사용한다.
    - 다른 안드로이드 클래스에 의존성을 제공받을 수 있다.
    - 주입된 필드 멤버는 private일 수 없다.(생성자 주입은 가능)
6. @Module
    - 주입할 객체 타입이 내가 가진게 아닐 때, construct-inject 가 불가할 때 힐트 모듈을 사용하여 바인딩 정보를 제공
    - construct-inject 가 불가할 때란 Interface, external library(ex Retrofit, OkHttpClient, Room databases, instance created by builder pattern)
7. @InstallIn(~::class)
    - 어느 안드로이드 클래스에 힐트가 사용될지 알려주기 위해 힐트모듈에 사용한다.
    - 괄호 안에 ActivityComponect::class 라고 표시한다면 모든 앱의 액티비티가 모듈안의 객체에 의존할 수 있다는 의미
8. @Binds
    - 인터페이스는 constructor-inject 할 수 없으므로 힐트 모듈안에 abstract function 에 어노테이트
    - 인터페이스의 어느 구현체가 객체로 사용될지 알려준다.
    - 함께 쓰인 abstract function 의 리턴타입은 인터페이스를 말하고 파라미터는 어느 구현클래스인지 말해준다.
9. @Provides
    - 함수의 바디부분은 힐트가 그 해당 리턴타입을 어떻게 제공할지 알려준다.
10. @ApplicationContext
    - predefined qualifiers 중 하나이다.
11. @ActivityContext
    - predefined qualifiers 중 하나이다.
 