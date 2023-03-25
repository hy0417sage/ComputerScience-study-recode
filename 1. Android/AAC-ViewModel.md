# AAC-ViewModel
- MVVM의 ViewModel이랑 다르다!
- UI 컨트롤러와 별도의 생명주기를 가지는 사용자 입력 데이터를 저장하고 관리하도록 설계된 컴포넌트

## AAC-ViewModel을 사용하는 이유
- activity 및 fragment와 같은 UI 컨트롤러는
- (1) UI 데이터를 표시하거나
- (2) 사용자 작업에 반응하거나
- (3) 권한 요청과 같은 운영체제 커뮤니케이션을 처리한다.
- 데이터베이스나 네트워크에서 데이터로드를 UI 컨트롤러에게 책임지도록 요구하면 클래스가 팽창하게 됩니다. (책임이 많아지게 된다.)
- UI 컨트롤러에 이처럼 과도한 책임을 할당하게 되면 다른 클래스로 작업이 위임되지 않고, 단일 클래스가 앱의 모든 작업을 처리하려고 할 수 있기 때문에 AAC-ViewModel을 사용!

## 별도의 생명주기를 가지는 이점
- ViewModel의 생명주기는 UI 컨트롤러의 생명주기보다 더 길어,
- UI 컨트롤러가 재생성 되더라도 ViewModel에서 관리하는 데이터는 유지됩니다.
- 예를들어 화면을 회전하게 되면 액티비티는 onPause()를 시작으로 onDestory까지 호출되어 종료 되는데 ViewModel에서 별도로 데이터를 관리한다면 값이 초기화 되지 않고 유지됩니다.

## MVVM의 ViewModel과 AAC-ViewModel의 차이점
- MVVM ViewModel은 view와의 관계에서 단방행 통신을 하고, View에서 입력된 데이터를 Model로 전달하는 아키텍처 패턴의 요소
    - MVVM의 ViewModel은 View 와 Model 사이에서 데이터를 관리해주고 바인딩 해주는 역할을 합니다.
- 반면 AAC-ViewModel은 안드로이드 생명주기를 고려해 사용자 입력 데이터를 저장하고 관리하도록 설계된 컴포넌트로 MVVM 패턴을 구현하기 위해 사용하는 방법중 하나 입니다.
    - AAC viewmodel은 화면 회전 같은 환경에서 데이터를 보관하고 라이프 사이클을 알고 있어서 Activity나 Fragment의 Destroy시 onClear 함수를 통한 데이터 해제의 역할을 합니다.