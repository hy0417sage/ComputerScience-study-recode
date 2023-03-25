// 문자의 갯수를 새는 solution을 만들어주세요
// 문자열이 많이 나온 순서대로 정렬됩니다.
// 문자열 갯수가 같은 경우는 없다고 가정합니다.

fun main() {
   var result1 = solution("aaddcddaa")
   println(result1)
   // {d=4, a=4, c=1}
}

fun solution(str: String) : Map<String, Int>{
    
    val answerMap = mutableMapOf<String,Int>()
    val resultArr = mutableListOf<String>()
    
    for(i in str){
        resultArr.add(i.toString())
    }
    
    val distinctList = resultArr.distinct() 
    
    for(i in distinctList){
        answerMap[i] = resultArr.filter{it == i}.count()
    }
    
    return answerMap.toList().sortedBy { it.second }.reversed().toMap()
}