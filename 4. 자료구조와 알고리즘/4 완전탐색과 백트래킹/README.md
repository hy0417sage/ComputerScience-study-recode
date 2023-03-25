# 백트래킹 기법의 이해
## ❓ 백 트래킹이란 - 알고리즘이 아닌 문제를 푸는 전략이다.
- 제약 조건 만족 문제에서 해를 찾기 위한 전략
- 해를 찾기 위해, 후보군에 제약 조건을 점진적으로 체크하다가, 해당 후보군이 제약 조건을 만족할 수 없다고 판단되는 즉시 backtrack (다시는 이 후보군을 체크하지 않을 것을 표기)하고, 바로 다른 후보군으로 넘어가며, 결국 최적의 해를 찾는 방법이다.

### 실제 구현시
- 고려할 수 있는 모든 경우의 수(후보군)를 **상태 공간 트리**를 통해 표현한다.
<img width="400" alt="" src="https://user-images.githubusercontent.com/97173983/217141075-7096da5f-740e-4a94-ad00-2dc67fff9e96.png">
- 각 후보군을 **DFS 방식**으로 확인 (깊이 우선 탐색),
- 상태 공간 트리를 탐색하면서, 제약 조건이 맞지 않으면 해의 후보가 될 만돨한 곳으로 바로 넘어가서 탐색
    - 해당 루트가 조건에 맞지 않으면 포기하고 다른 루트로 바로 돌아가서, 탐색의 시간을 절약하는 기법이다.

### ‼️ 즉, 백 트래킹은
- 트리 구조를 기반으로 DFS로 깊이 탐색을 진행하면서 각 루트에 대해 조건에 부합하는지 체크, 만약 해당 트리(나무)에서는 노드는 더 이상 DFS 로 깊이 탐색을 진행하지 않고, 가지를 처버림

# 👿 백트래킹 기법을 적용할 수 있는 N-Queen 문제 이해
- NxN 크기의 체스판에 N개의 퀸을 **서로 공격할 수 없도록** 배치하는 문제
- 퀸은 다음과 같이 이동할 수 있으므로, 배치된 퀸 간에 공격할 수 없는 위치로 배치해야함
    - 퀸 : 수직, 수평, 대각선 이동(공격) 가능
    <img width="208" alt="" src="https://user-images.githubusercontent.com/97173983/217142064-cf4a190c-c927-4118-b8e0-66934e7d8a49.png">


## N Qeen 문제 - 가지치기
1. 한 행에는 하나의 퀸 밖에 위치할 수 없음 (퀸은 수평 이동이 가능하다)
2. 맨 위에 있는 행부터 퀸을 배치하고, 다음 행에 해당 퀸이 이동할 수 없는 위치가 없을 경우에는, 더이상 퀸을 배치하지 않고, 이전 행의 퀸의 배치를 바꿈
3. 만약 앞선 행에 배치한 퀸으로 인해, 다음 행에 해당 퀸들이 이동할 수 없는 위치가 없을 경우에는, 더 이상 퀸을 배치하지 않고, 이전 행의 퀸의 배치를 바꿈

### 💡 즉, 문제 풀이는
- 맨 위의 행부터 전채 행까지 퀸의 배치가 가능한 경우의 수를 상태 공간 트리 형태로 만든 후, 
- 각 경우를 맨 위의 행부터 DFS 방식으로 접근, 
- 해당 경우가 진행이 어려울 경우, 더 이상 진행하지 않고, 다른 경우를 체크하는 방식으로 진행!

1. 한 행에는 하나의 퀸 밖에 위치할 수 없음 (퀸은 수평이동이 가능하다)
2. 퀸은 대각선 이동이 가능하다 -> 대각선과 수평에 해당되는 위치는 배치할 수 없다.
    <img width="236" alt="" src="https://user-images.githubusercontent.com/97173983/217144614-bba3d5c9-22a9-4e74-90a1-4152c36ecf50.png">
    - 수직 방향 체크하기 : 같은 열의 크기를 가지고 있으면 배치할 수 없다. 열 - 열
    - 대각선 방향 체크하기 : 행과 열간의 갭이 같으면 대각선에 위치해 있다라고 할 수 있다. abs(열 - 열) == abs(행 - 행)

#### ➕ 코틀린 절대값 구하는 법
~~~kotlin
import kotlin.math.*
abs(숫자)
~~~

# ⌨️ 코틀린 코드 작성하기
~~~Kotlin
import java.util.*
import kotlin.math.*

fun main(){
    
    // 수직 체크, 대각선 체크
    fun is_available(candidate, current_col) : Boolean {
        var current_row = candidate.length
        for(queen_row in 0..current_row-1){
            if(candidate[queen_row] == current_col 
            || abs(candidate[queen_row] - current_col) == current_row - queen_row){
                return false
            }
        }
    }

    // 1. 최대 몇개가 필요한지, 2. 현재의 행, 3. 지금까지 배치된 배치정보, 4. 전체 배치 정답
    fun DFS(N, current_row, current_candidate, final_result){
        //종료 조건
        if(current_row == N){
            final_result.add(current_candidate)
            return
        }

        // 수직 체크, 대각선 체크
        for(candidate_col in 0..N-1){
            if(is_available(current_candidate, candidate_col)){
                current_candidate.add(candidate_col)
                DFS(N, current_row+1, current_candidate, final_result)
                current_candidate.pop()
            }
        }
    }

    fun solve_n_queens(N){
        var final_result = mutableListOf()
        DFS(N, 0, [], final_result)
        return final_result
    }
}
~~~