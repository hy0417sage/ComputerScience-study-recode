# 수신 객체 지정자

## 범위 지정 함수 with
- 중복 사용을 제거하기 위해 범위 지정 함수 with를 사용
~~~kotlin
class Person {
    var name: String? = null
    var age: Int? = null
}
val person: Person = getPerson()
print(person.name)
print(person.age)
~~~
- 위의 코드를 아래와 같이 바꿀 수 있다.
~~~kotlin
val person: Person = getPerson()
with(person) {
    print(name)
    print(age)
}
~~~

# with와 비슷한 역할을 하는 함수
1. apply
2. let
3. also
4. run

## also
### with 와 also 의 차이점
1. 범위 지정 함수 호출시에 수신 객체가 어떻게 전달 되는가

# 아 이거 어렵다(나중에 추가 공부)
- [블로그](https://medium.com/@limgyumin/%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%9D%98-apply-with-let-also-run-%EC%9D%80-%EC%96%B8%EC%A0%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%EA%B0%80-4a517292df29)