# CleanArchitecture + Modularization
- CleanArchitecture + Modularization로 앱을 설계하기 위해 공부하는 공간입니다.
- 관심사의 분리를 통해 (UI, Domain, Data Layer)를 통해 코드의 복잡성을 줄이고, 
- 의존 관리를 분리해 유지보수가 편하도록 만드는 것이 목표입니다.

## 일반 아키텍처 원칙 (CleanArchitecture, MVVM, Repository)
- 아키텍처 원칙은 크게 두 가지로 볼 수 있습니다.
  - 관심사 분리
  - 데이터 모델에서 UI 도출하기 (이것도 일종의 관심사 분리로 볼 수 있겠죠)
- UI 및 앱의 구성요소 수명주기는 앱의 데이터와 네트워크 연결에 직접적인 연관성이 없어야 합니다.

### 지속 모델이 이상적인 이유
- android OS 에서 리소스를 확보하기 위해 앱을 제거해도 사용자 데이터가 삭제되지 않습니다.
- 네트워크 연결이 취약하거나 연결되어 있지 않아도 앱이 계속 동작합니다.
앱 아키텍처를 데이터 모델 클래스에 기반하는 경우 앱의 테스트 가능성과 견고성이 더 높아집니다.

## 권장 앱 아키텍처
- 애플리케이션에는 두개 이상의 레이어(UI, Data Layer)가 있어야 합니다.
  - 추가적으로 UI와 Data 레이어 간 상호작용을 간소화 하고 재 사용하기 위한 Domain Layer를 추가할 수 있습니다. 

<img src="https://github.com/hy0417sage/TIL/assets/97173983/c4276314-8ac4-4d24-9861-e999948a2b7d" width="500">

- https://developer.android.com/jetpack/guide

### 1. [UI Layer](https://developer.android.com/topic/architecture/ui-layer)
- UI레이어의 역할은 화면에 애플리케이션 데이터를 표시하는 것입니다.
- 데이터가 변할 때마다 변경사항을 반영하도록 UI가 업데이트 되어야 합니다.

#### UI Layer 구성
1. UI 요소 : **View** or **Jetpack Compose**
2. 데이터를 보유하고 이를 UI에 노출하며 로직을 처리하는 상태 홀더 : **ViewModel** 클래스
<img src="https://github.com/hy0417sage/TIL/assets/97173983/ab303462-489d-4eb9-9572-8abb4009f256" width="500">

### 2. [Data Layer](https://developer.android.com/topic/architecture/data-layer)
- 앱의 비즈니스 로직(앱의 데이터 생성, 저장, 변경 방식을 결정하는 규칙)이 포함되어 있습니다.
- 앱에서 처리하는 다양한 데이터마다 저장소 클래스를 만들어야 합니다.
  - 영화 관련 데이터(MoviesRepository, PaymentRepository 클래스 만들기)
<img src="https://github.com/hy0417sage/TIL/assets/97173983/fea25f05-ca03-4264-9d16-54012ea2127e" width="500">

### 3. (선택적 레이어) [Domain Layer](https://developer.android.com/topic/architecture/domain-layer)
- 캡슐화를 담당합니다. (상호작용자)
- 복합성을 처리하거나 재 사용성을 선호(필요)하는 경우 사용합니다.
- 이 레이어의 클래스는 하나의 기능을 담당해야 합니다. 예를 들어 ViewModel에서 시간대를 사용하여 화면에 적절한 메시지를 표시하는 경우 앱에는 GetTimeZoneUseCase 클래스가 있을 수 있습니다. (이걸 항상 염두하면서 설계하기)

