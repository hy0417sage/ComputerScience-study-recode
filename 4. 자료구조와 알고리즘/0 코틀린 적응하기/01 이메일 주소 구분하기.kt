fun main() {
    val result1 = solution("abcd")
    println(result1)
    
    val result2 = solution("abcde")
    print(result2)
   
}

fun solution(str: String) : ArrayList<String> {
    
    val length = str.length
    var answer = arrayListOf<String>()
    answer.add(length.toString())
    
    if(length == 0){
        answer.add("true")
    }else{
        answer.add("false")
    }
    
    return answer
}