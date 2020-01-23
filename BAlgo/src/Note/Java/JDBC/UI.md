UI

WIndow를 상속받은 Frame

BoarderLayout 화면을 5구역으로 나눠서 한 구역에 한 개의 UI 컴포넌트 배치

컴포넌트의 크기는 구역의 크기

구역에 컴포넌트를 배치하지 않으면 Center가 그 구역을 차지한다.



FlowLayout

- 컴포넌트를 중앙에서 양 옆으로 배치

- Top -> Bottom으로 배치

- 컴포넌트의 크기를 유지해서 배치



GridLayout

- 화면을 행렬로 나누고 모든 컴포넌트 크기는 동일하게 지정
  - row, col, vgap, hgap  			ex) GridLayout( 2 , 3 , 5 , 5 )



CardLayout 

- 모든 컴포넌트가 한 영역에 쌓여서 나중에 추가한 컴포넌트만 화면에 보인다.
- 이벤트를 통해서 화면을 교체



여러개의 레이아웃을 써야 화면이 예쁨



**Help - Eclipse Marketplace - WindowBuilder검색 후 설치**

![1565311306312](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565311306312.png)



![1565312686928](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565312686928.png)



Anonymous Nested 클래스

- 클래스 이름이 없음

- 부모 이름을 이용해서 객체 생성

- 상속을 받고 클래스 선언하고 동시에 객체 생성

- 형식1)    new 부모() {}

  형식2)    부모클래스명 변수명 = new 부모() {}

- 한번만 생성하고 다른곳에서 쓸일 없음



##### ActionListener

버튼이 클릭되거나 엔터키가 입력되면 처리하는 처리해주는 인터페이스



