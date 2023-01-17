# itertools
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

# heapq : 힙(heapq)
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

# bisect : 이진탐색(Binary Search)
- 이진 탐색 기능을 제공하는 라이브러리이다.
- 우선순위 큐 기능을 구현하기 위해 사용한다.
- bisect라이브러리는 정렬된 배열에서 특정한 원소를 찾아야 할 때 매우 효과적으로 사용한다.
- bisect_left(a, x) : 정렬된 순서를 유지하면서 리스트 a에 데이터 x를 삽입할 가장 왼쪽 인덱스를 찾는 메서드
- bisect_right(a, x) : 정렬된 순서를 유지하면서 리스트 a에 데이터 X를 삽입할 가장 오른쪽 인덱스를 찾는 메서드
    - 또한 정렬된 리스트에서 값이 특정 범위에 속하는 원소의 개수를 구하고자 할 때, 효과적으로 사용할 수 있다. bisect_right - bisect_left

# collections : 덱(deque), 카운터(Counter)
- 덱, 카운터 등의 유용한 자료구조를 포함하고 있는 라이브러리이다.
- deque : bfs
- Counter : 등장 횟수를 세는 기능을 제공한다.
- defualtdict : 딕셔너리 초기화


# math : 팩토리얼, 제곱근, 최대공약수(GCD), 삼각함수
- 필수적인 수학적 기능 제공
- math.factorial(x) # x!
- math.sqrt(7) # x의 제곱근을 반환
- math.gcd(21, 14)
- math,lcm(a, b) #최소공배수
