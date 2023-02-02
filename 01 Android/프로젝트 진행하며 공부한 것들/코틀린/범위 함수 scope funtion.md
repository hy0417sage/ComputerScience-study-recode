# 범위 함수 scope funtion
- 사례에 대한 적합한 범위 기능을 선택하는 데 도움을 주기 위해 자세히 설명하고 사용 권장 사항을 제공한다. 기술적으로 기능을 많은 경우에 상호 교환 가능 하므로 예제는 일반적인 사용 스타일을 정의하는 규칙을 보여준다.

# 범위함수를 사용하는 이유는 뭘까?
- 범위지정 함수는 특정 범위 안에서 객체의 이름을 생략하고 코드를 실행시켜주는 힘수이다.
- 범위 지정 함수를 사용하는 이유는 코드의 간결성과 유지보수의 용이함 때문이다. 
- 범위지정 함수 자체가 이름을 생략하고 실행시켜주는 함수이기 때문에, 사용하는 이유도 코드의 간결성과 유지보수의 용이함이 되는 것이다.

# 코틀린에서의 범위 지정 함수 
- 코틀린에서의 범위 지정 함수는 **apply, run, with, also, let**이 있다.
범위 지정 함수는 두가지 구성 요소를 가진다.
1. 수신 객체(receiver)
2. 수신 객체 지정 람다
- 범위 지정 함수를 이해하려면 수신 객체를 알아야 한다.
- 수신 객체라는 용어는 Kotlin의 확장 함수에서 등장한다. > [확장함수에 대해 알아보러 가기]()


## 수신객체 지정 람다 with와 apply
### 수신객체 람다란?
- 수신 객체를 명시하지 않고 람다의 본문 안에서 다른 객체의 메소드를 호출할 수 있게 하는 것을 수신객체 람다라고 한다.

### 수신객체 람다와 확장함수 비교
- this가 함수의 수신 객체를 가리키는 비슷한 개념을 떠올릴 수도 있다.
- 확장함수 안에서의 this는 그 함수가 확장하는 타입의 인스턴스를 가리킨다. 그리고 그 수신 객체 this의 멤버를 호출할 때에는 this. 을 생략할 수 있다.
    - 어떤 의미에서는 확장 함수를 수신 객체 지정 함수라 할 수도 있다.
    - 일반함수 <- 확장함수
    - 일반람다 <- 수신객체 지정 람다.

### 먼저 보고 이해하기 🔍
1. 수신 객체 지정 람다를 사용하면 람다 안에서 미리 정해둔 수신 객체의 메소드를 직접 호출할 수 있다.
2. 표준 라이브러리의 with 함수를 사용하면 어떤 객체에 대한 참조를 반복해서 언급하지 않으면서 그 객체의 메소드를 호출할 수 있다. apply를 사용하면 어떤 객체라도 빌더 그타일의 API를 사용해 생성하고 초기화 할 수 있다.

## 📚with 라이브러리
- 컨텍스트 내부에서 호출하는 함수이다.
- 객체의 이름을 반복하지 않고도 그 객체에 대해 다양한 연산을 수행할 수 있다.
- with문은 언어가 제공하는 특별한 구문이 아니다, 그냥 파라미터가 2개 있는 함수이다.
~~~kotlin
fun main() {
    val numbers = mutableListOf("a", "b", "c", "d")
    println(numbers.first())
    println(numbers.last())
    
    val firstAndLast = with(numbers){
        this.add("e") //this를 명시해서 지정한 수신 객체의 메소드를 호출한다.
        add("f") //this 를 생략하고 메소드를 호출한다.
        "${first()} ${last()}"
    }
    println(firstAndLast)
}
~~~
- 여기서 첫번째 파라미터는 numbers 리스트이다. 두번째 파라미터는 람다이다. 람다를 괄호 밖으로 빼내어 전체 함수 호출이 언어가 제공하는 특별 구문처럼 보이게 하는 것이다.
- with 함수는 첫번째 받은 객체를 두번째 인자로 받은 람다의 수신 객체로 만든다. 인자로 받은 람다 본문에서 this를 사용해 그 수신 객체에 접근할 수 있다.
### with 정리
- with 가 반환하는 값은 람다 코드를 실행한 결과며, 그 결과는 람다 식의 본문에 있는 마지막 식의 값이다. 
    - 하지만 때로는 람다의 결과 대신 수신 객체가 필요한 경우도 있다. 그럴때는 apply를 사용하면 된다.

## 📚apply 
- 항상 자신에게 전달된 객체를 반환한다.
- apply는 거의 with와 같다. 유일한 차이는 값을 반환하지 않고, 자신에게 전달된 객체를 반환한다는 점이다.
~~~kotlin
fun main() {
    
    var tester1 = Person("Tester1")
    println(tester1)
    
    tester1.age = 20
    tester1.city = "Seoul"
    
    println(tester1)
    
    val tester2 = Person("Tester2").apply{
        age = 21
        city = "Busan"
    }
    
    println(tester2)
}

data class Person(
var name: String,
var age: Int = 0,
var city: String = "")
~~~
~~~
Person(name=Tester1, age=0, city=)
Person(name=Tester1, age=20, city=Seoul)
Person(name=Tester2, age=21, city=Busan)
~~~
- apply는 확장함수로 정의되어있다. apply의 수신 객체가 전달받은 람다의 수신 객체가 된다.
- 이 함수에서 apply를 실행한 결과는 Person 객체다. 따라서 그 객체의 age나 city를 호출해서 Person객체를 얻을 수 있다.

### apply는 확장 함수로 정의 되어있다.
- apply를 객체 초기화에 활용하는 예로 안드로이드의 TextView 컴포넌트를 만들면서 특성 중 일부를 설정해보자
~~~kotlin
fun createView(context: Context) = TextView(context).apply{
    text = "Simple Text"
    textSize = 20.0
    setPadding(10, 0, 0, 0)
}
~~~
- 이처럼 apply 함수를 사용하면 함수의 본문에 간결한 식을 사용할 수 있다.


# 📚 let
- non null 일 때 동작하고, null 일 때 동작하지 않도록
~~~kotlin
fun main() {
    
    val str : String? = "hi"
    val length = str?.let {
        println(it)
        println("실행실행 hi 부분")
        it.length
    }
    
    println(length)
    
    val str2 : String? = null
    val length2 = str2?.let{
        println(it)
        println("실행실행 null 부분")
        it.length
    }
    
    println(length2)   
}
~~~
- 출력 : 실행실행 hi 부분 / 2 / null
- 변수에 값이 있는 경우 let 문 안의 정보들이 출력되지만
- 변수에 값이 null 인경우 let문 안의 정보들은 출력되지 않고 null 만 반환한다.


# 📚 run 
- 객체 초기화와 리턴값 계산이 필요할 때 사용
- 람다에 개체 초기화와 반환 값 계산이 모두 포함되어있을 때 유용하다.
~~~kotlin
fun main() {
    val service = multiPortService("www.naver.com", 80)
    val result1 = service.query(service.prepareRequest() + " to port ${service.port}")
    
    println(result1)
    
    val result2 = service.run{
        port = 404
        query(prepareRequest() + " to port $port")
    }
    
    println(result2)
}

class multiPortService(var url : String, var port: Int){
    fun prepareRequest(): String = "기본 요청 url $url"
    fun query(request: String) = "결과 query $request"
}
~~~

# 📚 also 
- 객체에 대해 추가적인 작업
~~~kotlin
fun main() {
    var numbers1 = mutableListOf(1, 2, 3, 4)
    numbers1.also{
        println("$numbers 여기에서 5를 추가합니다.")
    }
    
    val numbers2 = mutableListOf(1, 2, 3, 4)
    numbers.also{
        println("$numbers2 여기에서 5를 추가합니다.")
    }.add(5)
    println(tester2)
}
~~~


### 추가로 정리해야하는 부분
1. 범위 지정 함수들의 차이점
2. let, also, run
3. 혼용하기 쉬운점 대비하기