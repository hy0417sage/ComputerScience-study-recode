# LivData, MutableLiveData


## [LiveData](https://developer.android.com/topic/libraries/architecture/livedata?hl=ko)
- 생명주기를 인식하는 관찰 가능한 홀더 클래스
- activity, fragment, 서비스 등 다른 앱 구성 요소의 생명주기를 인식한다.
Obserber 클래스로 표현되는 관찰자의 수명 주기가 started또는 resumed 상태이면 LiveData는 관찰자를 활성 상태로 간주한다.
- 그리고 활성 상태의 관찰자에게만 업데이트 정보를 알린다.

## LiveData사용의 이점
### 1. UI와 데이터 상태의 일치를 보장한다.
- LiveData는 Obserber 패턴을 따른다. 
- LiveData는 데이터가 변경 될 때 Obserber 객체에 알린다. 코드를 통합하여 Observer 객체에 UI를 업데이트 할 수 있다.
- 이렇게 하면 앱 데이터가 변경 될 때마다 관찰자가 대신 UI를 업데이트 하므로 개발자가 업데이트 할 필요가 없다.
### 2. 메모리 누수 없음
- 관찰자는 Lifecycle 객체에 결합되어 있으며 연결된 생명주기가 끝나면 자동으로 삭제된다.
### 3. 중지된 활동으로 인한 비 정상 종료 없음
- 활동이 백 스택에 있을 때를 비롯하여 관찰자의 생명 주기가 비 활성 상태에 있으면 관찰자는 어떤 LiveData 이벤트도 받지 않음
    - 활동이 백 스택에 있다는 말이 뭐지?
### 4. 수명주기를 더이상 수동으로 처리하지 않는다.
- LiveData는 관찰하는 동안 관련 생명주기 상태의 변경을 인식하여 모든걸 자동으로 관리
### 5. 최신 데이터 유지
- 생명 주기가 비 활성화 되면 다시 활성화 될 때 최신 데이터를 수신
- 백그라운드에 있었던 활동은 포그라운드로 돌아온 직후 최신 데이터를 받는다
### 6. 적절한 구성 변경
- 기기 회전과 같은 구성 변경으로 인해 Activity, Fragment가 다시 생성되면 사용 가능한 최신 데이터를 즉시 받게 된다.
    -> viewmodel을 따로 만들지 않아도 그러려나??
### 7. 리소스 공유
- 앱에서 시스템 서비스를 공유할 수 있도록 실글톤 패턴을 사용하는 LiveData 객체를 확장하여 시스템 서비스를 래핑 할 수있다.
- LiveData 객체가 시스템 서비스에 한번 연결되면 리소스가 필요한 모든 관찰자가 LiveData 객체를 볼 수 있다. (LiveData 확장)[https://developer.android.com/topic/libraries/architecture/livedata?hl=ko#extend_livedata]
    - 래핑 : 

## LivaData 객체 사용
1. 특정 유형의 데이터를 보유할 LiveData의 인스턴스를 생성한다. 이 작업은 일반적으로 **ViewModel 클래스 내**에서 이루어진다.

2. onChanged() 메서드를 정의하는 Observer 객체를 만듭니다. 이 메서드는 LiveData 객체가 보유한 데이터 변경 시 발생하는 작업을 제어합니다. 일반적으로 활동이나 프래그먼트 같은 UI 컨트롤러에 Observer 객체를 만듭니다.
3. observe() 메서드를 사용하여 LiveData 객체에 Observer 객체를 연결합니다. observe() 메서드는 LifecycleOwner 객체를 사용합니다. 이렇게 하면 Observer 객체가 LiveData 객체를 구독하여 변경사항에 관한 알림을 받습니다. 일반적으로 활동이나 프래그먼트와 같은 UI 컨트롤러에 Observer 객체를 연결합니다.

## LivaData 객체 만들기
~~~kotlin
class NameViewModel : ViewModel() {

    // Create a LiveData with a String
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    // 처음에는 객체의 데이터가 설정되지 않음
}
~~~
- LiveData는 Collections를 구현하는 List와 같은 객체를 비롯하여 모든 데이터와 함께 사용할 수 있는 래퍼
- LiveData 객체는 일반적으로 ViewModel 객체 내에 저장되며 위와 같이 getter 메서드를 통해 액세스된다.

## LiveData 객체 관찰
- 대부분의 경우 onCreate() 메서드가 LiveData를 관찰하기 적합한 장소
    - 이유 : onResume()에서 중복 호출을 하지 않도록 하기 위함
    - 활동이나 프래그먼트에 활성 상태가 되는 즉시 표시할 수 있는 데이터가 포함되도록 하기 위함. 앱 구성요소는 STARTED 상태가 되는 즉시 관찰하고 있던 LiveData 객체에서 최신 값을 수신.
~~~kotlin
class NameActivity : AppCompatActivity() {

    // Use the 'by viewModels()' Kotlin property delegate
    // from the activity-ktx artifact
    private val model: NameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Other code to setup the activity...

        // Create the observer which updates the UI.
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            nameTextView.text = newName
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.currentName.observe(this, nameObserver)
    }
}
~~~
- 코틀린 by 키워드