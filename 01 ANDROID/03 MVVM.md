# 안드로이드 아키텍처 디자인패턴
- 디자인 패턴이 생겨난 이유는 무엇일까?
~~~
안드로이드에서 아키텍쳐 디자인패턴을 사용하는 이유는 뷰, 모델, 컨트롤러 등의 역할을 명확하게 분리하여 코드의 가독성을 높이고, 유지보수성을 향상시키기 위함입니다. 
또한, 디자인패턴을 사용하여 코드의 재사용성을 높이고, 개발 과정에서 발생할 수 있는 버그의 가능성을 줄일 수 있습니다. 
~~~

## MVC, MVP, MVVM 패턴이 발전된 과정
- 유지보수와 개발 효율을 위해, 각각의 역할을 나누어 코드 관리를 하기 위해 생겨났습니다.
~~~
(1) 안드로이드 MVC 패턴에서는 View와 Model 사이에 Controller라는 중간 역할이 있어서 각각의 역할이 명확하지 않고, 
View와 Model 사이의 의존성이 높아져서 유지보수가 어렵다는 문제가 있었습니다.

(2) 이에 대한 대안으로 MVP 패턴이 등장, MVP 패턴에서는 Presenter라는 중간 역할이 Controller보다 가볍고 단순하며, View와 Model 사이의 의존성을 완전히 끊어서 각각의 역할이 더욱 명확해졌다.
하지만 Presenter가 View와 Model에 직접적으로 의존하게 되어서 Presenter의 테스트가 어렵다는 문제가 있었습니다.

(3) 그래서 MVVM 패턴이 등장했습니다. MVVM 패턴에서는 ViewModel이라는 중간 역할이 있습니다. 
ViewModel은 Model과 View를 완전히 분리시켜주면서도, Presenter와는 달리 View에 직접적으로 의존하지 않고, 데이터 바인딩을 통해 View와 Model을 연결시켜준다. 
따라서 ViewModel의 테스트가 용이하며, 코드의 재사용성과 유지보수성이 향상됩니다.
~~~

## MVC
- MVC = Model + View + Controller
- Controller는 여러개의 View를 선택할 수 있는 1:n 구조입니다. Controller는 View를 선택할 뿐 직접 업데이트 하지 않습니다. 단순한 구조이지만, View와 Model 사이의 의존성이 높아 애플리케이션이 커질수록 복잡하고 유지보수가 어렵게 만들 수 있습니다.

### MVP
View는 UI의 입력 및 표시만 담당합니다. 그리고 View - Presenter는 각각 추상적인 형태로 명령을 주고 받는다. 예로 들면 contract patter의 사용, Task Id 등이 있습니다. 그래서 View가 먼저 사용자의 입력을 통보하면 Presenter는 모델과의 통신을 통해 데이터의 갱신 및 스스로 판단 후 비즈니스 로직을 처리 후 View에게 변경 ㄹ요청을 하게 됩니다.
View - Presenter가 추상적인 형태로 명령을 주고 받기 때문에 View는 입력이나 이벤트를 넘겼을 때 어떠한 처리를 통해 들어오는지 여부는 알 수 없습니다.  즉, Presenter가 일련의 처리 ( ex. 로딩바를 보여줘 )를 요청하니 처리하는 것이라 생각하면 됩니다.

### MVVM
- MVVM에서 ViewModel은 Presenter와 마찬가지로 mediator 역할만을 담당합니다.
- 차이점은 **View와의 관계에서 ViewModel은 단방향, Presenter는 양방향 처리를 한다는 점**입니다.
- 즉, Presenter는 View에게 뷰 변경 요청을 보내지만, ViewModel은 View에게 어떠한 요청도 보내지 않습니다. 단지 View가 ViewModel의 데이터를 관찰하다 변경되면 변경을 적용할 뿐입니다. 그래서 코드 상에선 View가 ViewModel을 호출해 observable or callback 형태로 받는다.
- MVVM 패턴을 사용할 경우, ViewModel이 View와 Model을 완전히 분리시켜주기 때문에, Presenter와 달리 View에 직접적으로 의존하지 않아서 ViewModel의 테스트가 용이하며, 코드의 재사용성과 유지보수성이 향상된다는 장점이 있습니다.

## MVVM의 ViewModel과 ACC-ViewModel의 차이
- MVVM의 ViewModel은 View 와 Model 사이에서 데이터를 관리해주고 바인딩 해주는 역할을 합니다.
- AAC viewmodel은 화면 회전 같은 환경에서 데이터를 보관하고 라이프 사이클을 알고 있어서 Activity나 Fragment의 Destroy시 onClear 함수를 통한 데이터 해제의 역할을 합니다.
~~~
MVVM(Model-View-ViewModel) 아키텍처 패턴은 UI와 비즈니스 로직을 분리하여 개발하는 방식입니다. 이 패턴에서 ViewModel은 View와 Model 사이에서 데이터를 관리하고 전달하는 역할을 합니다. ViewModel은 View의 상태를 관리하며, Model에서 가져온 데이터를 가공하여 View에 전달합니다. 또한, ViewModel은 View와 Model의 의존성을 없애기 위해 인터페이스를 사용합니다.
ACC-ViewModel은 안드로이드 아키텍처 컴포넌트 중 하나로, ViewModel을 확장하여 안드로이드의 생명주기를 고려한 기능을 제공합니다. ACC-ViewModel은 ViewModel과 마찬가지로 View와 Model 사이에서 데이터를 관리하고 전달하는 역할을 하지만, 안드로이드의 생명주기에 따라 데이터를 저장하고 복원하는 기능을 제공합니다. 또한, ACC-ViewModel은 View가 화면에서 사라진 후에도 데이터를 유지할 수 있도록 하는 기능도 제공합니다.
따라서, MVVM의 ViewModel과 ACC-ViewModel은 기본적으로는 비슷한 역할을 수행하지만, ACC-ViewModel은 안드로이드의 생명주기를 고려한 기능을 추가로 제공합니다.
~~~

## MVI이란? (찍먹)
- MVI는 MVVM 비슷하지만, ViewModel 대신에 Intent를 사용하여 뷰와 비즈니스 로직을 분리하여 구현하는데 더욱 적합합니다.