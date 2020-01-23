# DDL문 (Data Definition Language)

- Database에서 사용하는 다양한 객체를 생성, 삭제, 변경하는 문

- 수행하면 DB에 바로 반영됨

- 종류 : table, index, sequance

- 생성 : create 객체 객체명;
		
		```sql
		create sequence board_no;
	```
	
- 삭제 : drop 객체 객체명;
		
		```sql
		drop sequence board_no
	```
	
	​	

#### 테이블

- 데이터를 관리할 최소 단위

- 생성

  ```sql
  create table 테이블명(
    
  	컬럼명 데이터타입 [[CONSTRAINT 제약조건이름] 컬럼레벨 제약조건]
      , ...
      , [[CONSTRAINT 제약조건이름] 테이블레벨 제약조건]
  );
  ```

- 변경

  ```sql
  alter table 테이블명( add | modify, change, drop ) 컬럼이름
  ```

  ```sql
  -- semp 테이블에 int타입 deptno추가
  alter table s_emp add deptno int;
  ```

  ```sql
  -- 컬럼 타입 변경
  alter table s_emp modify deptno varchar(30);
  ```

  ```sql
  -- 컬럼 이름 변경	: alter table 테이블이름 change 이전컬럼이름 변경할컬럼이름 변경할 자료명
  alter table s_emp change deptno address varchar(200);
  desc s_emp;
  ```

  ```sql
  -- 컬럼 삭제 : alter table 테이블이름 drop 칼럼이름
  alter table s_emp drop address;
  ```

  

- 삭제

  ```sql
  drop table 테이블명;
  ```

  주의점 : **부모테이블인 경우 삭제가 안됨. -> 자식 테이블 삭제 후 부모 테이블을 삭제해야 한다.**

  ​			 ex)  member(부모), goods(부모), orders(자식)
  ​				    삭제 순서 : orders->member or goods

  ```sql
  --  삭제 : alter table 테이블이름 drop 칼럼이름
  alter table s_emp drop address;
  ```

  ```sql
  drop table s_emp; -- 자식 테이블이 없어서 삭제 가능.
  drop table member; -- orders 테이블이 참조하고 있어서 삭제가 안됨.
  ```

  



char() : 변하는 크기

varchar() : 가변 크기

char(4) - 크기만큼 생성 (4개 생성)

varchar(4) - 크기 미만으로 쓸때 그 크기만큼만 생성 (1개 넣으면 1개만 생성)





# 제약조건

#### Primary key(주키)

- <u>행 데이터를 구별할 목적으로 사용하는 키</u>

- 중복되지 않은 데이터, null이 없는 데이터

- 주의점

  **중복된 데이터 또는 null인 데이터를 저장하면 error 발생**



#### Unique key

- 중복되지 않은 데이터만 저장할 경우

- 주의점

  **중복된 데이터 저장하면 error 발생**
  
  **null값 가능**



#### Not null

- <u>null이 없는 데이터</u>

- 컬럼 레벨에서만 설정할 수 있다.

- 주의점

  **null 데이터를 저장하면 error 발생**



#### Foreign key(외래키)

- 다른 테이블의 <u>**primary(부모키)** 컬럼의 데이터를 참조</u>

- 자식(참조) 테이블 : 데이터를 참조하는 테이블

- 외래키로 설정된 field는 부모키에 없는 데이터를 입력하면 오류 발생

- 부모키를 참조하고 있는 외래키에 데이터가 있다면 부모키 삭제시 오류 발생

- 형식

  컬럼레벨 : **[constraint foreign key 외래키명 ] references 참조할 테이블(참조할 주키) [on 옵션]**

  테이블레벨 : **[constraint foreign key 외래키명 (컬럼명) ] references 참조할 테이블(참조할 주키) [on 옵션]**

  ```sql
  create table goods(
  gno   int auto_increment primary key,
  brand varchar(100) not null,
  price int          default 0,
  maker varchar(50),
  image varchar(50),
  info  varchar(300),
  cno		char(2), -- references category(cno)이걸 밑에줄 대신 써도 됨.
  foreign key fk_goods_cno(cno) references category(cno)
  );
  ```

- 옵션 종류

  - **delete cascade** : 부모키 삭제시 외래키에 해당하는 모든 행을 삭제 (위험하기 때문에 잘 안씀)
  - **delete set null** : 부모키 삭제시 외래키에 null 값이 설정됨 (주로 사용)

  

#### **Check**

- insert, update시 데이터 조건에 맞는지 검사
- 조건 맞지 않으면 error발생



#### **Default**

- 값이 입력되지 않거나 check조건에 맞지 않으면 기본으로 설정되는 값



##### 주문 테이블 생성하기 (Orders)

ono		: 주문 번호					primary key
odate	: 주문일
id		: 주문한 고객 아이디		foreign key
gno		: 상품 번호					foreign key
quantity: 주문한 수량
address : 배송지

```sql
-- 날짜 기본값 설정 5.6.5 미만은 now()
-- 				5.6.5 이상은 current_timestamp (현재 5.6.8 사용중)
create table orders (
	ono 		int primary key auto_increment,
    odate 		datetime default current_timestamp,
    id 			varchar(30), -- references Member(id),
    gno 		int not null, -- references Goods(gno),
    quantity	int,
    address		varchar(200),
    foreign key fk_orders_id(id) 	references member(id),
    foreign key fk_orders_gno(gno)	references goods(gno)
);
```



```sql
create table s_emp(
	 empno	int			primary key
    ,ename	varchar(30)	not null
    ,salary	decimal(11,2) -- 11자리,~소숫점 2자리까지.
    ,commission_pct decimal(4,2)
    ,constraint check(commision_pct in (10,12.5,15,17.5,20)) -- 이중에 하나가 아니면 에러
    ,constraint check(salary >= 1000)
);
```



### truncate (신중히 사용할 것 - 복구 불가이기 때문에 쓰지 말 것 ㅎ)

- 테이블의 모든 데이터를 삭제
- 복구할 수 없다.

```sql
truncate 테이블명;
```



```sql
-- 테이블의 구조와 데이터 복제
create table emp2 as select * from emp;
select * from emp2;
truncate emp2; -- 테이블의 데이터만 삭제
rollback; -- truncate을 통해 삭제된 데이터는 rollback을 해도 복구되지 않는다.
```



### index

- 인덱스는 지정한 컬럼을 기준으로 메모리 영역에 목차를 B-Tree형태로 생성

- select문의 성능을 향상시키기 위해 index를 생성

- primary key와 foreign key에 대해 자동으로 index를 생성한다.

  (Oracle이나 기타 - primary key에 대해 자동 index를 생성한다.) - foreign key에 대해서는 직접 생성해야 한다. (자동X)

- 형식

  ```sql
  create index 인덱스명 on 테이블명(컬럼명, ...)
  -- 컬럼 하나만 인덱스 만드는게, 두개보다 성능은 좋다. 하지만 두개가 자주검색된다면, 컬럼 두개를 인덱스만드는게 좋다.
  ```

- index단점 
  - 실제 테이블 말고도, 메모리에 테이블을 새로 생성해야 하기 때문에 메모리 안좋음
  - 데이터를 삽입, 수정, 삭제등을 할 때, 인덱스도 바뀌어야 하므로 속도가 느려진다.
  - B-tree는 어느정도 편향이 될 수 밖에 없기 때문에, 수정 삽입, 삭제 등을 할 때 점점 느려질 수 밖에 없다.
  - 자주 수정을 해도 인덱스가 필요하다면 만들지만, 플래그먼트가 발생해서 느려질 수도 있어서 한달에 한 번 정도 성능이 느려지면 주기적으로 index를 drop한다.
  - 삽입, 수정, 삭제가 잦을때에는 index를 만들지 않는게 정석이다. (하지만 실전에서는 안쓸 수가 없으므로, 한달에 한 번 쯤 index드롭)