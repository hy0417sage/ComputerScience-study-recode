// 리스트에 있는 숫자들을 모두 더한 후 리스트의 갯수로 나눈 값을 출력하세요.
fun main() {
    val arr1 = arrayListOf<String>("1", "2")
    val result1 = solution(arr1)
    println(result1)
  }
  
  fun solution(arr: ArrayList<String>) : Double {
      
      var answer = 0.0
      val length = arr.size
      
      for(i in arr){
          answer += i.toDouble()
      }
      
      return answer/length
  }