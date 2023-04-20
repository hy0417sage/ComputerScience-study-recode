# View
- 안드로이드 UI를 구성하기 위해 사용한다.
- UI 요소들의 조상 객체가 View이다.
- 드로잉, 이벤트 처리를 담당하는 UI 구성요소의 기본 클래스 이다.

## 위젯, 컴포넌트
- View를 상속받아 구현하는 TextView, Button 등 어떤 특수 목적을 가지고 있는 View를 위젯, 컴포넌트라고 부른다.
- 새로운 위젯을 만들기 위해선 View를 반드시 상속하여 구현해야 한다.
- 그리고 그러한 위젯들을 담는 부모 뷰, 즉 Layout 역시 View를 상속받는 ViewGroup을 상속받아 구현한다.

## View는 이렇게 그려진다.
- 안드로이드에서 사용자에게 보여지는 화면(사용자와 인터렉션하는 컴포넌트 Activity)는 포커스를 받고 나면, Android에게 View Hierarchy의 
- 루트 노드를 제공하며 레이아웃을 그리게 된다.
  - onCreate() 내에서 setOnContentView()를 통해 루트 노드 전달
  - 레이아웃은 루트 노드부터 리프노드까지 트리를 따라 순서대로 그려지게 된다.
- TopDown 방식
    - 부모 뷰는 자식 뷰들의 draw()를 호출하여 화면에 지정된 형태로 자식 뷰들을 그려줄 것을 요청
    - 각각의 뷰들은 알아서 그려질 책임이 있음
- 레이아웃을 그리는 과정은 Measure, Layout 두 단계를 거친다.

### 1. Measure 단계
- 역할: 뷰의 크기를 확인한다.
- measure() 메소드 호출을 통해 이루어짐
- 모든 뷰들은 각각 자신의 크기 측정 값을 저장함 (너비와 높이)
- 뷰의 measure()가 반환되면, getMeasuredWidth() 및 getMeasuredHeight() 값을 자식 뷰들의 값과 함께 설정해야한다.
- 부모 뷰는 자식들에게 두번 이상의 measure()를 호출할 수도 있음 (자식 뷰들의 크기 합이 너무 크거나, 너무 작을때 등)

### 2. Layout 단계
- 역할: 뷰를 측정하고 화면에 배치한다.
- layout 메서드 호출을 통해 이루어짐
- 부모뷰는 Measure 단계에서 측정된 크기를 사용하여 모든 자식 뷰들의 위치를 배정
- 즉, Measure 때 모아놓은 크기 수치값을 기준으로 전체적인 레이아웃을 그림

## 뷰는 그려지기 전부터 화면에 온전히 표시되기까지 생명주기가 존재한다.
![image](https://user-images.githubusercontent.com/97173983/233283733-da3e4545-5e47-482f-8df2-6eca12646c57.png)
### 1. constructor()
  - 모든 뷰는 생성자에 의해 생명주기가 시작 됨
  - addView()메소드를 갖게 됨
### 2. onAttachedToWindow()
  - 부모 뷰가 addView()를 호출함으로써 View가 윈도우에 붙을 때 호출된다.
  - 고유ID를 통해 View에 접근 가능해짐
  - 이 순간부터 뷰를 그리기 위한 surface를 가짐
    - 단, onDetachedFromWindow() 호출 이후에는 surface가 없음. 액티비티 onDestoryed() 호출될 때, 혹은 부모 뷰에서 해당 뷰를 제거할 때 호출
  - 따라서 이 순간부터는 리소스 할당 및 리스너 설정 등이 가능해짐

#### surface란 무엇인가?
안드로이드에서 UI를 렌더링하기 위한 개념이다.
- UI를 렌더링 하기 위한 개념들
  - Window > Surface > Canvas > View

#### window
- 하나의 화면 안에서는 여러개의 Window를 가질 수 있고, 이러한 Window들은 WindowManager가 관리한다.
- Window는 뭔가를 그릴 수 있는 창이며, 보통 하나의 surface를 가지고 있다.
- 어플리케이션은 windowManager와 상호작용하여 Window를 만들어 내고 각각의 window 표면에 그리기 위해 surface를 만든다.
- 일반적으로 Activity가 window를 가지게 된다.

#### surface
- surface는 화면에 합성되는 픽셀을 보유한 객체이다. 화면에 표시되는 모든 window는 자신만의 surface가 포함 되어있으며, Surface Flinger가 여러 소스로부터 그래픽 그래펙 데이터 버퍼를 받고, 그것들을 합성해서 Display로 보낸다.
- 개별 Surface는 이중 버퍼 렌더링을 위한 1개 이상 (보통 2개)의 버퍼를 가진다.
  - 이중 버퍼 렌더링이란, 스크린에 출력될 화면에는 프로임 버퍼에 저장되는데, 하나의 버퍼만 가지는 경우 이미지가 반복해서 그려지게 되거나, 이미지가 다 그려지지 않아도 화면 주사율 때문에 렌더링을 해야할 때, 다 그려지지 않은 프레임 버퍼가 렌더링 되어서
이미지가 깜빡이는 Flicker 현상이 나타날 수 있다. 이를 해결하기 위해 프레임 버퍼에 바로 렌더링 하지 않고, 다른 버퍼를 만들어서 그 버퍼에 렌더링을 완료 하고, 다음 프레임 버퍼에 옮기는 방식을 사용하여 Flicker 현상을 해결할 수 있다.
  - 렌더링이란? [안드로이드 공식 - 렌더링](https://developer.android.com/topic/performance/rendering?hl=ko), 사용자의 품질 인식에 영향을 주는 앱의 핵심 관점은 이미지와 텍스트를 화면에 부드럽게 렌더링하는 것이다. 앱이 화면에 그려질 때 버벅거림과 느려지는 것을 피하는 것이 중요하다.
    - 렌더링 작업: 오버드로 줄이기, 성능 및 뷰 계층 구조, GPU 렌더링 프로파일을 사용한 분석

#### Canvas
- 실제 UI를 그리기 위한 공간으로 비트맵이 그려지는 공간이다.

### 3. onMeasure()
- measure()에서 호출하는 콜백 메소드이다.(View의 크기를 측정하기 위해 호출됨)
  - 부모 뷰의 경우에는 모든 자식 뷰들의 measure()를 호출한 뒤 자신의 크기를 결정한다.
  - setMeasuredDimenstion() 호출하여 명시적으로 너비와 높이 설정한다.

### 4. onLayout()
- 


참고 : https://colinch4.github.io/2020-11-25/Window,-Surface,-Canvas,-View/
  
    
