# savedInstanceState 란?
- savedInstanceState의 null체크는 액티비티가 재생성 되었는지 체크할 수 있는 수단이 됨.
- 화면이 세로모드에서 가로모드로 전환시 onCreate함수가 다시 호출된다.
- 만약 전역 변수를 설정하고 그 값을 유지하며 한상 사용해야 하는 경유라도 화면이 세로에서 가로로 변경될 경우 전역 변수에 설정한 값이 모두 초기화 된다.
- 이런 경우 변경된 값을 유지하고 싶다면 savedInstanceState을 이용하는 것이 좋다.
- 초기값을 유지하고 싶다면 사용!!
- 메서드에 유지하고자 하는 값을 저장 savedInstanceState 가 null이 아닌 경우 그 값을 불러서 쓸 수 있다.

### savedInstanceState가 null인지 왜 check할까?
- Activity가 최초로 생성 되었다면 savedInstanceState는 null이다.
- Activity가 재생성되어 onCreate()가 다시 호출 되었다면 savedInstanceState는 null이 아니다.
- 하지만 savedInstanceState의 null체크는 액티비티가 재생성 되었는지 체크할 수 있는 수단이 되는것이고, 재생성 되었을때는 프레그먼트를 화면에 보여주지 않는것인가?
- 아니다. 프레그먼트는 잘 나온다. 이상하다. 분명 액티비티는 재생성된것이고, 프레그먼트를 추가한적이 없는데, 프레그먼트가 화면에 나온다.
- 이유는, 이미 생성되어 추가되었던 프레그먼트들은 configuration change에도 프레그먼트매니저(supportFragmentManager)에의해 살아남아 자동으로 관리가 된다. 

