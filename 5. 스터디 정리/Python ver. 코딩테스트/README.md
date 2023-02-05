# ☃︎ 이코테 & 파이썬을 파이썬 답게 정리

## itertools
- 순열(permutations) : 순서 고려
```python
list(permutations(['a', 'b', 'c'], 2))
```
- 조합(combinations) : 순서 고려하지 않음
```python
list(combinations(['a', 'b', 'c'], 2))
```
- product : 중복을 허용한 순열
```python
list(product(['a', 'b', 'c'], 2))
```

## heapq : 힙(heapq)
- 힙(Heap) 기능을 제공하는 라이브러리이다. 
    - 우선순위 큐 기능을 구현하기 위해 사용한다.(ex. 다익스트라)
    - 파이썬의 힙은 최소 힙으로 구성되어있어 단순히 원소를 힙에 전부 넣었다 빼는 것만으로도 시간복잡도 O(NlogN) 에 오름차순 정렬이 완료 된다.
    - 보통 최소 힙 자료구조의 최상단 원소는 항상 '가장 작은 원소'이기 때문
- heapq.heappush([], 2) : 힙에 원소를 삽입
- heapq.heappop([]) : 루트 노드를 삭제(최솟값 삭제)
- 파이썬은 최대힙을 지원하지 않는다.
~~~python
import heapq

def heapSort(iterable):
    h = []
    result = []

    for value in iterable:
        heapq.heappush(h, -value)

    for _ in range(len(h)):
        result.append(-heapq.heappop(h))
    resturn result
~~~

## bisect : 이진탐색(Binary Search)
- 이진 탐색 기능을 제공하는 라이브러리이다.
- 우선순위 큐 기능을 구현하기 위해 사용한다.
- bisect라이브러리는 정렬된 배열에서 특정한 원소를 찾아야 할 때 매우 효과적으로 사용한다.
- bisect_left(a, x) : 정렬된 순서를 유지하면서 리스트 a에 데이터 x를 삽입할 가장 왼쪽 인덱스를 찾는 메서드
- bisect_right(a, x) : 정렬된 순서를 유지하면서 리스트 a에 데이터 X를 삽입할 가장 오른쪽 인덱스를 찾는 메서드
    - 또한 정렬된 리스트에서 값이 특정 범위에 속하는 원소의 개수를 구하고자 할 때, 효과적으로 사용할 수 있다. bisect_right - bisect_left

## collections : 덱(deque), 카운터(Counter)
- 덱, 카운터 등의 유용한 자료구조를 포함하고 있는 라이브러리이다.
- deque : bfs
- Counter : 등장 횟수를 세는 기능을 제공한다.
- defualtdict : 딕셔너리 초기화


## math : 팩토리얼, 제곱근, 최대공약수(GCD), 삼각함수
- 필수적인 수학적 기능 제공
- math.factorial(x) # x!
- math.sqrt(7) # x의 제곱근을 반환
- math.gcd(21, 14)
- math,lcm(a, b) #최소공배수

## 아래처럼 사용 가능
list(map(len, mylist))

## 몫과 나머지 - divmod
- 파이썬의 divmod와 unpacking을 이용하면 나머지와 몫을 따로 구할 수 있다. 큰 숫자를 다룰 때 좋다.
~~~python
*divmod(7, 5)
~~~

## n진법으로 표기된 string을 10진법 숫자로 변환하기
~~~python
int('3212', 5) # 5진수인 '3212'를 10진수로 변환
~~~

## 문자열 정렬하기 - ljust, center, rjust
- 문자열을 좌측/가운데/우측 정렬
~~~python
s.ljust(n) # 좌측 정렬
s.center(n) # 가운데 정렬
s.rjust(n) # 우측 정렬

# '가나다라               ' # 좌측정렬
# '               가나다라' # 우측 정렬
# '       가나다라        ' # 가운데 정렬
~~~

## 알파벳 출력하기 - string 모듈
~~~python
import string 

string.ascii_lowercase # 소문자 abcdefghijklmnopqrstuvwxyz
string.ascii_uppercase # 대문자 ABCDEFGHIJKLMNOPQRSTUVWXYZ
string.ascii_letters # 대소문자 모두 abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
string.digits # 숫자 0123456789
~~~

## 원본을 유지한채, 정렬된 리스트 구하기 - sorted(list)
- .sort() : 원본의 멤버 순서를 변경
- 

## 2차원 리스트 뒤집기 - ⭐️zip⭐️
- 파이썬의 zip과 unpacking 을 이용하면 코드 한 줄로 리스트를 뒤집을 수 있습니다.
~~~python
mylist = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
new_list = list(map(list, zip(*mylist)))
~~~
- 파이썬의 zip 함수와 dict 생성자를 이용하면 코드 한줄로, 두 리스트를 합쳐 딕셔너리로 만들 수 있다.
~~~
animals = ['cat', 'dog', 'lion']
sounds = ['meow', 'woof', 'roar']
answer = dict(zip(animals, sounds)) # {'cat': 'meow', 'dog': 'woof', 'lion': 'roar'}
~~~

## 모든 멤버의 type 변환하기 - map
- Iterable의 모든 멤버의 type을 변환
~~~python
list1 = ['1', '100', '33']
list2 = list(map(int, list1))
~~~

## sequence 멤버를 하나로 이어붙이기 - join
- 시퀀스의 멤버들을 하나의 string으로 이어붙여야 할 때
~~~python
my_list = ['1', '100', '33']
answer = ''.join(my_list)
~~~

## 곱집합(Cartesian product) 구하기 - product
~~~python
import itertools

iterable1 = 'ABCD'
iterable2 = 'xy'
iterable3 = '1234'
print(list(itertools.product(iterable1, iterable2, iterable3)))
~~~

## 2차원 리스트를 1차원 리스트로 만들기 - from_iterable
~~~python
# 방법 1 - sum 함수
answer = sum(my_list, [])

# 방법 3 - itertools와 unpacking
import itertools
list(itertools.chain(*my_list))
~~~

## 이진 탐색하기 - binary search
~~~
import bisect
mylist = [1, 2, 3, 7, 9, 11, 33]
print(bisect.bisect(mylist, 3))
~~~