# LiveData
- 안드로이드 생명주기를 인식하는 데이터 홀더 클래스
- 데이터와 UI의 동기화로 항상 최신 데이터를 유지할 수 있는 특징을 가지고 있습니다.

## 좀 더 자세히 알아보자
- LiveData는 백그라운드 스레드에서 작업을 수행하고, UI 스레드에서만 값을 업데이트할 수 있도록 도와줍니다. 이를 위해서는 옵저버 패턴을 사용하여 데이터가 변경될 때마다 UI를 업데이트합니다. 하지만 비동기적으로 데이터를 가져오는 경우 데이터 변경 사항을 LiveData로 전달하는 일이 복잡해질 수 있습니다.
- 안드로이드 lifecycle내에서  관찰할 수 있는 데이터 홀더 클래스로 ViewModel의 데이터 필드를 보유하도록 설계되어있습니다.
- 정의 : 안드로이드 생명주기를 인식하는 데이터 홀더 클래스, 데이터와 UI간의 동기화로 항상 최신 데이터를 유지함
- 특징 : 옵저버 패턴을 따름, 값이 변경될 때 Observer 객체에 알리면 관찰자가 대신 UI를 업데이트
- 탄생 배경 : 뷰와 뷰 모델 간의 데이터 흐름을 더욱 쉽게 구현할 수 있도록 탄생
- 장점 : UI와 데이터 상태의 일치 보장, 메모리 누수 없음, 
- 단점 : LiveData는 백그라운드 스레드에서 작업을 수행하고, UI 스레드에서만 값을 업데이트할 수 있도록 도와주기 때문에  데이터 변경 사항을 LiveData로 전달하는 일이 복잡해질 수 있습니다.
- 동작 방식 
    → LiveData는 Observer 인터페이스를 사용하여 관찰합니다. Observer 인터페이스에는 유일한 메서드인 onChanged가 있습니다. 이 메서드는 LiveData의 값이 변경되었을 때 호출되는 콜백 메서드로 이 메서드 안에서 LiveData의 값을 받아 사용하면 됩니다.
    → 데이터와 UI간의 동기화로 항상 최신 데이터를 유지할 수 있어 사용하였습니다. LiveData는 안드로이드 생명주기가 활성 상태일때 데이터가 변경되면 Observer 객체에 알려줘 매번 UI를 업데이트하는 코드를 작성할 필요 없이 데이터의 상태와 UI를 일치 시킬 수 있습니다. (UI 스레드를 사용합니다)
    → LiveData는 옵저버 패턴을 따라 기본 데이터가 변경 될 때 구독자에게 알립니다. 구독자는 알림을 받으면 해당 객체의 UI를 업데이트 할 수 있습니다.
    → [동작] LifeCycleOwner상태가 STARTED, RESUMED 일 때 Observer에 래핑된 데이터의 수정에 대해 알림을 받을 수 있습니다.
    → [장점] DESTROYED 상태로 이동하면 옵저버가 즉시 구독이 최소되어 메모리 누수에 대한 걱정 없이 사용할 수 있습니다.


## LiveData 세부동작
- onChanged() 메서드를 정의하는 Observer 객체를 만든다. 이 메서드는 LiveData 객체가 보유한 데이터 변경 시 발생하는 작업을 제어합니다.
- observe() 메서드를 사용하여 LiveData 객체에 Observer 객체를 연결합니다. observe() 메서드는 LifecycleOwner 객체를 사용합니다. 이렇게 하면 Observer 객체가 LiveData 객체를 구독하여 변경사항에 관한 알림을 받습니다.
- LiveData는 UI Controller가 (resume, start)일때 옵져브가 활성상태=명령 처리 가능 이라고 간주해서 업데이트 정보를 알리고 모든 관찰자가 트리거됩니다
- LiveData는 활성 관찰자에게만 업데이트 정보를 알립니다. LiveData 객체를 보기 위해 등록된 비활성 관찰자는 변경사항에 관한 알림을 받지 않습니다.
- Lifecycle 객체의 상태가 (DESTROYED)로 변경될 때 관찰자를 삭제할 수 있습니다. 이는 특히 활동 및 프래그먼트에 유용합니다. 활동과 프래그먼트는 LiveData 객체를 안전하게 관찰할 수 있고, 수명 주기가 끝나는 즉시 수신 거부되어 누수를 걱정하지 않아도 되기 때문입니다.

### LiveData가 lifecycle을 아는 이유
- lifecycle onwer는 SAM(Single Abstract Method) 이며, 이를 activity와 fragment에서 이를 상속하고 있습니다.
    → 즉, LiveData의 observe 함수를 이용해 onwer와 observer를 등록해 액티비티와 프래그먼트의 변수로서 사용한다면 각 화면 별 생명주기에 따라 LiveData는 자신의 임무를 수행합니다.
~~~kotlin
@MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        assertMainThread("observe");
        if (owner.getLifecycle().getCurrentState() == DESTROYED) {
            // ignore
            return;
        }
        LifecycleBoundObserver wrapper = new LifecycleBoundObserver(owner, observer);
        ObserverWrapper existing = mObservers.putIfAbsent(observer, wrapper);
        if (existing != null && !existing.isAttachedTo(owner)) {
            throw new IllegalArgumentException("Cannot add the same observer"
                    + " with different lifecycles");
        }
        if (existing != null) {
            return;
        }
        owner.getLifecycle().addObserver(wrapper);
    }
~~~

## LiveData를 지양하고 싶다면?
- model 쪽에서 UI 스레드로 동작하는 LiveData를 지양하고 싶다면, suspend를 사용하여 UI에서 직접 데이터가 변화할 때 알려주면 됩니다. → 본인이 원하는 스타일을 선택하면 됩니다. (Flow도 사용 가능)

## LiveData setValue(T), postValue(T)
→ setValue() : LiveData를 구독하고 있는 상태에서 setValue(T)를 통해 변경시킨다면 메인 스레드에서 그 즉시 값이 변경됩니다. 메인 스레드가 값을 dispatch
→ postValue() : postValue역시 main 스레드에서 동작합니다. 하지만 그 과정이 백그라운드에서 진행됩니다. 내부적으로 Handler를 통해 Handler(Looper.mainLooper()).post() → setValue()로 변경됩니다.

## ObservableField와 LiveData의 차이점
- ObservableField은 LifeCycle을 모르나 LiveData는 LifeCycle을 알고 있습니다.

## LiveData를 사용할 때 Mutable LiveData의 변수를 get()으로 가져오는 방식과 = 으로 가져오는 방식 두가지의 차이점은?
- 라이브 데이터를 캡슐화 하는지 아닌지의 차이로, 외부에서 직접 필드 내부값을 노출시켜 변경하는 것 보다 변경할 수 있는 함수를 만들어 노출합니다.

