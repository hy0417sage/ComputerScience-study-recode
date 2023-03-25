// 리스트에 있는 숫자들을 뒤집어서 반환하는 solution을 만드세요
fun main() {
    val arr1 = arrayListOf(1, 2, 3, 4, 5)
    val result1 = solution(arr1)
    println(result1)
    //[5, 4, 3, 2, 1]
}

fun solution(arr: ArrayList<Int>) : ArrayList<String> {
    
    var length = arr.size
    val answerList = ArrayList<Int>()
    
    for(i in 0..length-1){
        answerList.add(arr[length-1-i])
    }
    
    return answerList as ArrayList<String>
}