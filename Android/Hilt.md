# Hilt란?
안드로이드 클래스에 생명주기를 고려한 의존성 주입 라이브러리이다.

## Hilt를 사용하는 이유
- DI를 사용하는 이유와 + dagger에는 없는 안드로이드 생명주기를 고려한 라이브러리라는 점에서 사용한다.
    - Hilt는 dagger 위에 구축되어있다.
- 컴파일 타임 correctness, 런타임 퍼포먼스, scalability 측면에서 이득이다.
- 프로젝트에 있는 모든 안드로이드 클래스에 컨테이너를 제공하고 그들의 라이프사이클을 자동으로 관리해 준다.
- 의존성 그래프를 만들고 잘못된 의존성이나 의존성 사이클이 없는지 컴파일타임에 잡아준다.
- 런타임중에 실제 객체들과 그들의 의존성을 만들기위해 클래스를 생성해준다.

## 의존성 주입 프레임워크 비교
### Hilt
- Hilt는 Dagger2를 기반으로 만들어진 DI 프레임워크입니다.
- Android 앱의 컴포넌트에 대한 DI를 쉽게 구현할 수 있도록 도와줍니다.
- Hilt는 코드의 가독성과 유지보수성을 높여줍니다.
- Hilt의 장점은 다음과 같습니다.
    - Dagger2의 복잡한 설정을 간소화하여 DI를 쉽게 구현할 수 있습니다.
    - Android의 컴포넌트 수명주기를 고려하여 DI를 수행합니다.
    - 코드를 단순화하여 가독성과 유지보수성을 높입니다.
- Hilt의 단점은 다음과 같습니다.
    - Dagger2를 기반으로 만들어졌기 때문에 Dagger2를 알아야합니다.
    - Hilt를 사용하면 성능이 조금 느려질 수 있습니다.

### Dagger
- Dagger는 DI 프레임워크로서 의존성 주입을 구현하는 데 사용됩니다. Dagger는 컴파일 타임에 의존성 그래프를 생성하므로 런타임 시간에는 Reflection을 사용하지 않아 빠릅니다.
- 장점
    - 코드의 가독성과 유지보수성이 높습니다.
    - 컴파일 타임에 의존성 그래프를 생성하므로 런타임 시간에 Reflection을 사용하지 않아 빠릅니다.
- 단점
    - Dagger의 학습 곡선이 높습니다.
    - Dagger를 구현하기 위해서는 추가적인 코드가 필요합니다.

### Koin
- Koin은 Kotlin 기반의 DI 프레임워크입니다.
- Koin은 코드의 가독성과 유지보수성을 높이면서도 DI를 구현할 수 있습니다.
- Koin의 장점은 다음과 같습니다.
    - 코드의 가독성과 유지보수성이 높습니다.
    - Kotlin과의 호환성이 높습니다.
- Koin의 단점은 다음과 같습니다.
    - Koin을 사용하기 위해서는 추가적인 라이브러리가 필요합니다.
    - Koin을 사용하면 성능이 조금 느려질 수 있습니다.

## Hilt 사용하는 방법
## Hilt 사용방법

Hilt를 사용하기 위해서는 먼저 프로젝트 수준의 build.gradle 파일에 아래의 코드를 추가해야 합니다.
~~~
buildscript {
    ...
    dependencies {
        ...
        classpath 'com.google.dagger:hilt-android-gradle-plugin:2.31.2-alpha'
    }
}

// Apply the Gradle Plugin
apply plugin: 'dagger.hilt.android.plugin'
~~~

그리고 app 모듈의 build.gradle 파일에 아래의 코드를 추가해야 합니다.
~~~
plugins {
    ...
    id 'kotlin-kapt'
    id 'dagger.hilt.android.plugin'
}

android {
    ...
}

dependencies {
    ...
    implementation "com.google.dagger:hilt-android:2.31.2-alpha"
    kapt "com.google.dagger:hilt-android-compiler:2.31.2-alpha"
    implementation 'androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03'
    kapt 'androidx.hilt:hilt-compiler:1.0.0'
}
~~~

Hilt를 사용하려는 Application 클래스에 `@HiltAndroidApp` 어노테이션을 추가합니다.
~~~
@HiltAndroidApp
class MyApplication : Application() {
    ...
}
~~~

의존성 주입을 받을 클래스에 `@Inject` 어노테이션을 추가하고, 생성자에서 `@Inject` 어노테이션을 추가합니다.
~~~
class MyViewModel @Inject constructor(
    private val myRepository: MyRepository
) : ViewModel() {
    ...
}
~~~

필요한 의존성 주입을 받는 클래스를 생성할 때는 `@AndroidEntryPoint` 어노테이션을 추가합니다.

~~~
@AndroidEntryPoint
class MyFragment : Fragment() {
    ...
}
~~~


필요한 의존성 주입을 받는 클래스 내에서 `by viewModels()` 또는 `by activityViewModels()`를 사용하여 ViewModel을 가져올 수 있습니다.

~~~
class MyFragment : Fragment() {

    private val viewModel: MyViewModel by viewModels()

    ...
}
~~~


위의 방법을 따라 구현하면 Hilt를 사용하여 의존성 주입을 간편하게 구현할 수 있습니다.


### Hilt 어노테이션
- @HiltAndroidApp
    - 애플리케이션에 Hilt 코드를 자동으로 생성할 수 있게 한다.
    - Application의 확장 클래스에 붙여서 힐트의 코드를 생성할 수 있다.
~~~
@HiltAndroidApp
class MyApplication : Application() { ... }
~~~
- @AndroidEntryPoint(component-scope)
    - 힐트 컴포넌트를 생성 (Abstract class 는 이 어노테이션이 필요없다.)
    - 해당 클래스의 생명주기를 따르는 의존성 컨테이너를 만든다.
- @Inject constructor()
    - 생성자에 주입
    - 의존성으로서 인스턴스에게 타입을 제공하는데 필요한다.
    - 생성자에게 힐트가 그 클래스에 어떻게 인스턴스를 제공할지 알려준다.
- @HiltViewModel
    - ViewModel에 사용하는 어노테이션이다.
- @Inject
    - 필드에 주입할 떄 사용한다.
    - 다른 안드로이드 클래스에 의존성을 제공받을 수 있다.
    - 주입된 필드 멤버는 private일 수 없다.(생성자 주입은 가능)
- @Module
    - 주입할 객체 타입이 내가 가진게 아닐 때, construct-inject 가 불가할 때 힐트 모듈을 사용하여 바인딩 정보를 제공
    - construct-inject 가 불가할 때란 Interface, external library(ex Retrofit, OkHttpClient, Room databases, instance created by builder pattern)
- @InstallIn(~::class)
    - 어느 안드로이드 클래스에 힐트가 사용될지 알려주기 위해 힐트모듈에 사용한다.
    - 괄호 안에 ActivityComponect::class 라고 표시한다면 모든 앱의 액티비티가 모듈안의 객체에 의존할 수 있다는 의미
- @Binds
    - 인터페이스는 constructor-inject 할 수 없으므로 힐트 모듈안에 abstract function 에 어노테이트
    - 인터페이스의 어느 구현체가 객체로 사용될지 알려준다.
    - 함께 쓰인 abstract function 의 리턴타입은 인터페이스를 말하고 파라미터는 어느 구현클래스인지 말해준다.
- @Provides
    - 함수의 바디부분은 힐트가 그 해당 리턴타입을 어떻게 제공할지 알려준다.
- @ApplicationContext
    - predefined qualifiers 중 하나이다.
- @ActivityContext
    - predefined qualifiers 중 하나이다.