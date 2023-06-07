# Moshi란?
JSON 과 객체 사이의 **직렬화와 역 직렬화**를 쉽고 안전하게 할 수 있도록 돕는 라이브러리이다.
- 리플랙션과 Codegen 방식의 변환을 모두 지원한다
  - 리플렉션 : 구체적인 클래스 타입을 알지 못하더라도 그 클래스의 메서드, 타입, 변수들에 접근할 수 있도록 해주는 자바 API를 말하며, 컴파일 시간이 아닌 실행 시간에 동적으로 특정 클래스의 정보를 추출할 수 있는 프로그래밍 기법

### 직렬화와 역직렬화
- 파일을 다른 컴퓨터로 보내기 전 통신이 가능하면서 나중에 재구성 할 수 있는 포맷으로 변환해주어야 한다.
- 직렬화된 파일은 다시 객체 형태로 변환되어애 하는데, 이 과정을 역직렬화라고 한다.

## Moshi Annotation에 JSON 구성요소 대응
- JSON 구조에 대해 사용할 수 있는 Annotation은 두가지이다.
  1. @JsonClass(generateAdapter = true)
  2. @field:Json(name = "[JSON 키]")

### @JsonClass(generateAdapter = true)
JSON Object에 대응되는 역할을 한다. JSON Object에 대응되는 class 를 만들 때 붙인다.
~~~
@JsonClass(generateAdapter = true)
data class Test()
~~~

### @field:Json(name = "[JSON 키]")
JSON 내부의 Key-Value 값에 붙인다. name이라는 파라미터를 가지는데 json 포멧의 key 값에 대응되도록 만들어야 한다.
~~~
@JsonClass(generateAdapter = true)
data class Test(
  @field:Json(name = "[JSON 키]") val id: Int,
  @field:Json(name = "[JSON 키]") val pw: Int,
  @field:Json(name = "[JSON 키]") val name: String,
)
~~~
