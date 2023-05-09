# 코틀린 문법 강의 기록
- [인프런 - [입문편] 안드로이드를 위한 코틀린(Kotlin) 문법](https://www.inflearn.com/course/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%9E%85%EB%AC%B8%ED%8E%B8/dashboard)

# 코틀린 기본에서 나아가기
## 엘비스 연산자 - null 처리를 위해 사용한다.

## Any, Is, As
### Any - 모든 아이들을 포하하는 조상 같은 친구
- str을 Any로 타입 선언을, string 값으로 초기화 한 후 Int 값으로 다시 값을 지정해 줘도 문제 없음
~~~kotlin
var str : Any = "abc"
println(str2)
str2 = 123
println(str2)
~~~
- 출력 : abc, 123


### Is - 타입이 맞나 확인 할 수 있음
~~~kotlin
var str : Any = "abc"
if(str is String){
    print("this is string")
}else{
    print("this is not string")
}
~~~

### As - 타입 변경 / 체크 동시에 가능
~~~kotlin
var str : String? = 1 as? String
print(str) //null 이 출력
~~~


<br>
