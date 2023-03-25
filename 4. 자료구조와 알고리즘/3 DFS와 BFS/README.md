# DFS와 BFS
## 📚 DFS
- DFS는 스택이나 재귀 함수를 통해서 구현이 가능하다.
![](https://blog.kakaocdn.net/dn/bx2jzp/btrvCmySMq9/0q60BqkWUKfb3ulIXrSzw0/img.gif)
### 재귀함수로 DFS 구현
~~~kotlin
fun dfs(index : Int){
    visited[index] = True
    println(index)

    for(next in graph[index]){
        if(!visited[next]){
            dfs(next)
        }
    }
}

dfs(1)
~~~
1. 1번 노드를 방문하였다 표시하고 1을 출력한다.
2. for 문으로 들어온다.
3. 1번과 가장 처음 연결되어있는 2번 노드가 next가 된다.
4. visited[2]는 false이므로 dfs(2) 한다.
    - dfs(1)의 for문은 dfs(2)가 끝날때 까지 기다린다.
5. dfs(2)를 통해 2번 노드를 방문 했다고 표시하고 2를 출력한다.
6. 계속 진행한다.

# 📚 BFS
![](https://blog.kakaocdn.net/dn/bFoj4T/btrvDiiKm0z/2i8hhEnGf3OIkeO5Sjafx1/img.gif)
- 넓이 탐색으로 큐를 통해서 구현한다. (선입선출)
- 코틀린에서는 LinkedLis를 이용해서 Queue처럼 사용할 수 있다.

### ‼️ ArrayList를 쓰지 않고 LinkedList를 쓰는 이유
- ArrayList는 첫 번째 원소를 제거하면, 빈 자리를 채우기 위해 두번째부터 마지막 원소를 전부 앞으로 한 칸씩 복사한다.
- 즉, ArrayList는 제일 앞의 원소를 뺄 때마다 O(n)의 시간 복잡도가 발생한다.
- **LinkedList**는 반면에 첫 원소를 빼려면 그냥 header의 포인터를 두 번째 원소로 연결만 시키면 되는 구조이기 때문에 LinkedList는 O(1) 시간 복잡도만에 첫 원소를 제거하는게 가능하다.
- 따라서 선입선출 방식으로 쓰기 위해서는 LinkedList를 사용하는 것이 효율적
~~~kotlin
import java.util.LinkedList

val queue = LinkedList<Int>()
fun bfs(start: Int){
    queue.add(start)
    visited[start] = true

    while(queue.isNotEmpty()){
        val head = queue.poll()
        println(head)

        for(next in graph[head]){
            if(!visited[next]){
                visited[next] = true
                queue.add(next)
            }
        }
    }
}
bfs(1)
~~~
- while 문을 통해 큐가 빌 때까지 다음 작업을 반복한다.
1. 큐에서 가장 처음 원소를 꺼낸다.
2. 꺼낸 원소 번호를 출력한다.
3. 꺼낸 원소와 연결된 노드들 중에서 방문하지 않은 노드들을 큐에 순서대로 집어넣는다. 그러면서 동시에 visited를 true로 만든다.
4. 위의 과정을 큐가 빌 때까지 반복한다.

### 정리
- DFS는 재귀함수와 for문으로 구현
- BFS는 큐(Kotlin에서는 LinkedList)와 while+for문으로 구현