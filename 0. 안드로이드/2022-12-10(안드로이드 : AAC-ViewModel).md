# AAC-ViewModel
- MVVM의 ViewModel이랑 다르다!

## 정의 : AAC-ViewModel이란?
- Android 생명주기를 고려하여 UI 관련 데이터를 저장하고 관리하는 요소이다.

## 사용이유 : AAC-View을 왜 사용할까?
- activity 및 fragment와 같은 **UI 컨트롤러**는 
- (1) UI 데이터를 표시하거나
- (2) 사용자 작업에 반응하거나
- (3) 권한 요청과 같은 운영체제 커뮤니케이션을 처리한다.
- 데이터베이스나 네트워크에서 데이터로드를 UI 컨트롤러에게 책임지도록 요구하면 클래스가 팽창하게 됩니다. (책임이 많아지게 된다.)
- UI 컨트롤러에 이처럼 과도한 책임을 할당하게 되면 다른 클래스로 작업이 위임되지 않고, 단일 클래스가 앱의 모든 작업을 처리하려고 할 수 있다.

### ACC-ViewModel 생명주기
- ![acc-viewModel](./%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C/%EC%9D%B4%EB%AF%B8%EC%A7%80/ACC-ViewModel.png)
- activity와 fragment가 분리될 때까지 살아남기 때문에 Activity의 화면 회전 같은 상황에 데이터를 유지할 수 있습니다.
- 또한 viewModel을 이용하여 Activity에 포함 되어있는 Fragment와 통신할 수 있다.
- ViewModel을 사용하게 되면 이러한 UI 컨트롤러에 부담스러울 수 있는 책임을 분담하고 유지보수, 재사용성, 테스트 등을 쉽게 만들어 준다.

## 사용방법 : ACC-ViewModel을 사용하는 방법!