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

# 멀티 모듈 적용

## 모듈이란?
- 모듈은 소스 파일 및 빌드 설정으로 구성된 모음이며, 이를 통해 프로젝트를 별개의 기능 단위로 분할 할 수 있습니다.
- 프로젝트에는 하나 이상의 모듈이 포함될 수 있습니다.
- 하나의 모듈이 다른 모듈을 종속성으로 사용할 수 있습니다.

### 안드로이드 대표 세가지 모듈
1. Application : 앱을 실행하기에 반드시 필요한 모듈이며 빌드의 결과로 APK 파일을 생성합니다. -> app
2. Android Library : 안드로이드 프로젝트에서 지원되는 모든 파일 형식을 포함할 수 있습니다. -> presentation, data
3. Java or Kotlin Library : 순수한 Java 혹은 Kotlin으로만 이뤄진 모듈 입니다. 안드로이드 프레임워크로 독립적인 기능을 구현할 때 사용합니다. 빌드의 결과로는 JAR 파일이 생성됩니다. -> domain

<img width="700" src="https://github.com/hy0417sage/TIL/assets/97173983/3e161933-dbe6-41e9-8c49-cabc7328a8e5">

클린 아키텍처 기반으로 3개의 모듈 + app 모듈로 구성하면 됩니다
app 모듈을 사용하는 이유는 매니패스트 파일 설정과 Hilt 의존성 주입, Hilt 앱 설정을 위해 사용합니다.
domain은 안드로이드 의존성을 갖지 않고 Java와 Kotlin 코드로만 작성되어 있어 Java or Kotlin Library로 만들면 됩니다.
