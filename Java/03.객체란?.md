## 📍객체란 무엇일까?
    ⭐️ 자바의 신1, 3장을 읽고 정리

#### 01. 클래스와 객체의 차이점은?
- 클래스는 추상적인 개념 혹은 사물을 만들었다면 객체는 이를 실체화 시키는 것입니다.
- 예를들어 자동차 클래스를 만들었으면 이 자동차를 현대차, 기아차, 외제차 등으로 나누며 실체화 시킵니다.

#### 02. 객체를 생성하기 위해서 꼭 사용해야 하는 예약어는 무엇인가?
- new 예약어 입니다.
- 클래스 명은 말그대로 이러한 클래스가 있다 껍데기를 보여주는 것이고 앞에 new를 붙여야지 객체를 생성할 수 있습니다.

#### 03. 객체를 생성하기 위해서 사용하는 메소드 같이 생긴 클래스의 이름에 소괄호가 있는 것을 뭐라고 하는가?
- 생성자라고 합니다.
- 이것을 클래스를 만들때마다 직접 만들 필요는 없습니다. 알아서 생성해 주기 때문입니다.

#### 04. 객체의 메소드를 사용하려면 어떤 기호를 객체 이름과 메소드 이름 사이에 넣어주어야 하는가?
- 객체의 메소드를 사용하기 위해서는 .을 객체 이름과 메소드 사이에 넣어주면 됩니다.
- 이 점(.)은 클래스의 행동 즉 메소드를 호출합니다.

#### 05. 메소드를 사용하기 위해서는 어떤 것을 만들어야 하는가?
- 메소드를 사용하기 위해서는 객체를 이용해서 메소드를 부르면 됩니다.
- 점으로 호츨할 수 있습니다.

#### 06. 메소드를 사용하기 위해서 만들어야 하는 것은 어떤 예약어를 사용하고, 클래스의 무엇을 사용해야 할까?
- new 예약어를 사용하고 클래스의 이름을 사용해야 합니다.

***

#### 자바의신1 - 3장 연습문제
```java
public class Profile(){
    String name;
    int age;

    public static void main(String[] args){
        Profile profile = new Profile(); //Profile 클래스의 객체 선언
        
        profile.setName("송현영");
        profile.setAge(27);
        
        profile.printName();
        profile.printAge();
    }

    private void setName(String dtr){
        name = str; //매개변수로 받은 str값을 name에 할당
    }

    private void setAge(int val){
        age = val; //매개변수로 받은 val값을 age에 할당
    }

    public void printName(){
        System.out.println("My name is " + name);
    }

    public void printAge(){
        System.out.println("My age is " + age);
    }
}
```
