// 숫자를 받아서 팩토리얼 값을 출력하는 함수를 만드세요
// 3! > 3 * 2 * 1 = 6
fun main() {
    var result1 = solution(3)
    println(result1)
 }
 
 fun solution(number: Int) : Int{
     
     var answer = 1
     
     for(i in 1..number){
         answer *= i
         print(i)
     }
     
     return answer
     
 }