# 코틀린의 함수들
```
1. 확장함수
2. 중위함수 infix함수
3. inline 함수
4. 지역함수
```

### 확장함수
**'클래스 안에 있는 메소드처럼 호출할 수 있지만 함수를 밖에 호출할 수 있게 하자'** <br>

**확장함수 사용법**
```kotlin
fun 확장하려는클래스.함수이름(파라미터) : 리턴타입 {
    //this(수신객체)를 이용해 실제 클래스 안의 값에 접근
}
```
실사용 예시 : String 클래스를 확장하는 함수
```kotlin
//String의 마지막 문자를 반환하는 함수
fun String.lastChar() : Char{
    return this[this.length-1]
}
```
 
### 만약에.. 이렇다면?
1. 확장함수는 원본 클래스의 private, protected 멤버 접근이 안된다.
- 만약 확장 함수가 public이고 수신 객체 클래스의 private 함수를 가져오면 캡슐화가 깨지는 것 아닌가?
- 애당초 확장함수에서는 클래스에 있는 private, protected 멤버를 가져올 수 없다
2. 멤버함수, 확장함수 중 멤버함수에 우선권이 있다.
- 멤버함수와 확장함수의 시그니처가 같다면 멤버함수를 우선으로 호출된다.
- 확장함수를 만들었지만 다른 기능의 똑같은 멤버함수가 생기면 오류가 발생할 수 있다.
3. 확장함수는 현재 타입을 기준으로 호출된다
- 확장함수가 오버라이드 된다면 현재 타입의 확장 함수가 호출된다.
 
#### Java에서 Kotlin의 확장함수를 가져다 사용하는 법
- 자바에서는 코틀린에 존재하는 확장함수를 정적 메소드를 부르는 것처럼 사용 가능하다.
- 확장 프로퍼티의 원리는 확장함수 + custom getter와 동일하여 확장 프로퍼티와도 연결된다.
- 마치 자바에 있는 getter를 코틀린 클래스에서 custom getter로 만드는 것 처럼 프로퍼티로 확장 할 수 있다.
 
### infix 함수 (중위함수)
- 중위함수란 새로운 함수 종류가 아니고 함수를 호출하는 새로운 방법이다.
- downTo, step도 함수이다!
변수.함수이름(argment) 대신 변수 함수이름 argment 방식으로 작성할 수 있다.
<br>

**infix 함수 예시**
```kotlin
//그냥 확장함수
fun Int.add(other: Int): Int {
    return this + other
}
//확장함수 + 중위함수
infix fun Int.infixAdd(other: Int): Int{
	return this + other
}

9.add(5) //그냥 확장함수만 사용할 경우 사용법

9.infixAdd(6) //중위함수와 같이 사용할 경우1
9 infixAdd 6 //중위함수와 같이 사용할 경우2
```

### inline 함수 (아직 잘 모르겠음..)
- 함수가 호출되는 대신, 함수를 호출한 지점에 함수 본문을 그대로 복붙하고 싶은 경우에 사용한다.
- 사용하는 이유 : 함수를 파라미터로 전달할 때 오버헤드를 줄일 수 있다.
- 하지만 inline 함수의 사용은 성능 측정과 신중하게 사용되어야 한다. 그리고 코틀린 라이브러리에서는 최적화를 어느정도 해뒀기 때문에 적절하게 inline 함수가 붙어있다.

### 지역함수 
- 함수안에 함수를 선언하는 것을 지역함수라고 한다.
- 함수로 추출하면 좋을것 같은데, 이 함수를 지금 함수 내에서만 쓰고 싶을 때 사용할 수 있다.
- 하지만, depth가 깊어지기도 하고, 코드가 그렇게 깔끔하지는 않다.
```kotlin
//중복되는 코드를
fun createPerson(firstName: String, lastName: String): Person {
	if(firstName.isEmpty()) {
    	throw IllegalArgmentException("firstName은 비어있을 수 없습니다. 현재 값 : ${firstName}")
    }
    
    if(lastName.inEmpty()) {
    	throw IllegalArgmentException("lastName은 비어있을 수 없습니다. 현재 값 : ${lastName}")
    }
    
    return Person(firstName, lastName, 1)
}

//이렇게 바꿀 수 있다.
fun createPerson(firstName: String, lastName: String): Person {

	fun validateName(name: String, fieldName: String) {
    	if(name.isEmpty()){
        	throw IllegalArgmentException("firstName은 비어있을 수 없습니다. 현재 값 : ${firstName}")
        }
    }
    
    validateName(firstName, "fistName")
    validateName(lastName, "lastName")
    
    return Person(firstName, lastName, 1)
}
```
 
## 최종정리
1. Java코드가 있는 상황에서, Kotlin 코드로 추가 기능 개발을 하기 위해 확장함수와 확장프로퍼티가 등장했다.
2. 확장함수는 원본 클래스의 private, protected 멤버 접근이 안된다.
3. 멤버함수, 확장함수 중 멤버함수에 우선권이 있다.
4. 확장함수는 현재 타입을 기준으로 호출된다.
5. 함수 호출방식을 바꿔주는 infix 함수가 존재한다.
6. 함수를 복사-붙여넣기 하는 inline 함수가 존재한다.
7. Kotlin에서는 함수 안에 함수를 선언할 수 있고 이를 지역함수라고 부른다.
