# KOTLIN CODINGTEST
- 코틀린 코딩테스트

## 리스트 string으로 출력하기
- 코틀린에서 배열 데이터 출력 형식을 변경할 때 사용한다.
- 리스트.joinToString("") 

## 특정 문자열 포함되는지 확인
- 문자열.contains(특정문자)

## 리스트 정렬
- .sorted()

## 리스트 혹은 문자열 동시 ZIP
- str1.zip(str2)

## 공백으로 구분하기 
- .split(" ")

## intRange를 이용하여 array 생성
- (0..10).toList().toIntArray()

# fold: 리스트에 있는 원소들을 누적해서 계산하는 방법 (값으로 출력)
- 코틀린에는 컬렉션에 있는 요소를 누적해서 더하여 반환하는 reduce, fold 함수를 사용할 수 있다.
~~~kotlin
리스트.fold(1) { total, num -> total * num }
~~~

# .map: 리스트 각 원소에 더하기 혹은 곱하기에 유용 (리스트로 출력)
~~~kotlin
if (k % 2 == 1) arr.map { it * k }.toIntArray()
else arr.map { it + k }.toIntArray()
~~~

# forEach() 함수 : 데이터의 개수만큼 특정 구문을 반복 실행할 때 유용
~~~kotlin
리스트.forEach {index -> answer += index}
~~~

# 리스트 슬라이스
~~~kotlin
리스트.slice(1..3)
~~~

# filter, filterIndexed 사용해 배열 요소 필터링
### filter 함수
- filter 함수는 리스트 이터레이션 하면서 리턴이 true인 값만 필터링 한다.
- 가장 기초이고, Boolean 값에 따라 필터링을 할지 안 할지 결정한다.
~~~kotlin
리스트.filter { it % 2 == 0 } //조건에 맞는 값만 출력됨
~~~

### filterNot 함수
- 조건이 아닌 경우에만 남길 때 사용하는 함수이다. 가독성이 걱정될 때 적절히 사용하묜 로직을 보기 좋게 정리할 수 있다.
~~~kotlin
리스트.filterNot { it == 0 } 
~~~

### filterIndexed 함수
- 인덱스를 통해 처리하고 싶을 때 인덱스와 값을 각각 받을 수 있다.
~~~kotlin
리스트.filterIndexed { index, v -> index == 3 }
~~~


# n번째 원소부터 특정 원소까지
~~~kotlin
리스트.slice(n - 1 ..num_list.size)
~~~

# 대문자, 소문자
### 대문자, 소문자로 바꾸기
- .toUpperCase()
- .toLowerCase()
### 대문자, 소문자 확인
- .isUpperCase()
- .isLowerCase()

