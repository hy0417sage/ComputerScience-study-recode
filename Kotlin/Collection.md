## Collection(컬렉션)이란?
- 목록성 데이터를 처리하는 자료구조로 하나가 아닌 여러 데이터를 담을 때 사용한다.
- 자료구조(Data Structure) : 어떤 데이터를 담는것을 의미

### Kotlin의 Collection(컬렉션)
코틀린의 컬렉션은 자바의 컬렉션을 확장 구현한 것으로 가변 컬렉션과 불변 컬렉션으로 나뉜다.
- 가변 컬렉션 : 컬렉션에 element를 추가, 삭제할 수 있다.
- 불변 컬렉션 : 컬렉션에 element를 추가, 삭제할 수 없다.
- 불변 컬렉션이라 하더라도 Reference(참조) Type인 element의 필드는 바꿀 수 있다. 컬렉션 자제가 변경되는 것은 불가능 하지만, 컬렉션 내 객체 조작은 가능하다.

### 코틀린 List
```kotlin
val numbers = listOf(100, 200)
val numbers = mutableListOf(100, 200)

numbers.add(300) //추가

val emptyList = emptyList<Int>() //비어있는 리스트 초기화
val emptyList2 = emptyList()

fun textEmptyList(emptyList2: emptyList<Int>){
	//TODO : emptyList2의 타입이 이처럼 추론할 수 있다면, 타입 선언 하지 않아도 됨
}

//값 출력
println(numbers[0])
println(numbers.get(0)) //자바와 동일하게 사용할 수 있음

// For Each
for (number in numbers) {
    println(number)
}

// 전통적인 for문
for ((index, number) in numbers.withIndex()) {
    println("$index $number")
```

### 코틀린 Set
```kotlin
Set은 List와 다르게 순서가 없고, 값은 element는 하나만 존재할 수 있다
자료구조적 의미만 제외하면 모든 기능은 List와 비슷하다.

val numbers = setOf(100, 200)
val mutableNumbers = mutableSetOf(100, 200)

for(num in numbers){
	println("${num}")
}

for((index, value) in numbers.withIndex()){
	println("${index} ${value}")
}
```

### 코틀린 Map
```kotlin
val map = mapOf(1 to "SUNDAY", 2 to "MONDAY", 3 to "TUESDAY")
val mutableMap = mutableMapOf<Int, String>()
map[1] = "SUNDAY"
map[2] = "MONDAY"
map.put(3, "TUESDAY")

for (key in map.keys) {
    println("${key} ${map[key]}")
}

for ((key, value) in map.entries) { //entries 생략 가능
    println("${key} ${value}")
}
```
- 여기서 map.entries의 entries를 생략할 수 있다.<br> 이유는, Kotlin Library에서 map에 entries.iterator()를 반환하는 '확장함수'를 만들어 두었기 때문에 entries를 생략 하더라도 동일한 entries를 붙인것과 같은 결과가 나온다.

 
### 컬레션의 null 가능성
- ?의 위치에 따라서 null 가능성 의미가 달라지므로 차이를 잘 이해해야 한다.
1. List<Int> : 리스트 안에 Null이 들어갈 수 없으며, 리스트도 절때 Null이 될 수 없다.
2. List<Int?> : 리스트 안에 Null이 들어갈 수 있으며, 리스트는 절때 Null이 될 수 없다.
3. List<Int>? : 리스트 안에 Null이 들어갈 수 없으며, 리스트는 Null이 될 수 있다.
4. List<Int?>? : 리스트 안에 Null이 들어갈 수 있으며, 리스트도 Null이 될 수 있다.
 
### Java 와 Kotlin을 섞어 사용할 때 주의할 점
- Java에서 Koltin 컬렉션을 가져갈 때는 불변 컬렉션을 수정할 수 있고, non-nullable컬렉션에 null을 넣을 수 있다.
- Kotlin에서 Java의 컬렉션을 가져갈 때는 플랫폼 타입을 주의해야한다.
 
### 결국 위와 같은 문제를 방지하거나 해결하기 위해서는
1. Kotlin에 있는 컬렉션이 Java에서 호출되면 컬렉션 내용이 변할 수 있다는걸 감안해야 한다.
코틀린 쪽에서 Collections.unmodifableList(), Collections.unmodifableSet(), Collections.unmodifableMap()을 활용하면 변경 자체를 막을 수 있다.
2. Kotlin에서 Java 컬렉션을 가져다 사용할 때에는 플랫폼 타입을 신경써야 한다.
자바에서 코틀린에 List<Int>를 보내주면, 코틀린은 List<Int?> 인지 List<Int?>?인지 등 알수가 없다. 그러니 자바 코드를 보면서 어떤 값들이 허용되는지 맥락을 확인하고 Java 코드를 가져오는 지점을 적절히 Warpping해서 영향범 위를 최소화 해야 된다.
