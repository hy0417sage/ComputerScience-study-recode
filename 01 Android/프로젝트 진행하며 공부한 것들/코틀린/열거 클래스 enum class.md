# 열거 클래스
- 값을 열거하는 클래스이다.
~~~kotlin
fun main() {
    println(Direction.NORTH)
    Direction.values().forEach{
    	println(it)
    }
}

enum class Direction{
    NORTH, SOUTH, WEST, EAST
}
~~~