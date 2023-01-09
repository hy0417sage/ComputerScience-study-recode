# Hilt란? (정의)
- 안드로이드 클래스에 생명주기를 고려한 의존성 주입 라이브러리 이다. (dagger라는 라이브러리는 생명주기를 고려하지 않았음)

## Hilt를 사용하는 이유
- DI를 사용하는 이유와 + dagger에는 없는 안드로이드 생명주기를 고려한 라이브러리라는 점에서 사용한다. 

### Hilt 사용하는 방법
1. @HiltAndroidApp
    - 애플리케이션에 Hilt 코드를 자동으로 생성할 수 있게 한다.
~~~kotlin
@HiltAndroidApp
class ExampleApplication : Application() { ... }
~~~
2. @AndroidEntryPoint(component-scope)
    - 해당 클래스의 생명주기를 따르는 의존성 컨테이너를 만든다.
3. @Inject
    - 필드 주입할 떄 사용, 