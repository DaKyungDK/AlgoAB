# JDBC

##### DESC 테이블이름

- 테이블 구조 보기

##### insert into member(컬럼 이름, 컬럼이름, ...) values(값,값, ..) 

-> 순서 주의 할 것.



##### 관계형 데이터베이스 기본은 Entity



​																		처리순서

##### select [distinct] *|컬럼명 [ as alias ] 		   (4)

##### from 테이블명 [ alias ] 									 (1)

##### [ where 조건 ]													 (2)

##### [ group by 컬럼명,...  [having 조건] ]			  (3)

##### [ order by 컬럼명 [ asc | desc ] ]					 (5)

- 테이블 조회

- *과 컬럼명은 같이 쓸 수 없다.
- 조건을 사용하면, 그 조건에 맞는 데이터만 가져온다.
- 기본적으로 4번(select줄)에서 생성한 alias는, 5번 order by에서만 사용할 수 있다. -> mysql은  모든 줄에서 쓸 수 있게 해준다. 오라클 등에서는 불가. ->시험문제 주의할 것!



```sql
#member 테이블의 모든 행, 모든 열 정보를 추출
select * from member;
```



```sql
#member 테이블의 모든 회원의 아이디, 이름, 이메일을 조회
select id,name,email
from member;
```



```sql
-- 전체 상품에 대해서 상품번호, 상품명, 가격, 10%인상한 가격을 조회
select gno, brand, price, price*1.1 as INC_Price -- 인상된 가격
from goods;
```

컬럼이 숫자인 경우, 연산이 가능하다.



```sql
-- where절을 이용해서 조건에 맞는 행만 추출
-- 조건 연산자 : ==, !=(<>와 같음), >, >=, <, <=
-- 			      and , or
-- 가격이 5000원 이상 되는 상품들의 상품번호, 상품명, 가격 조회
select gno, brand, price
from goods
where price>=5000;
```

```sql
-- id가 wltmd0426이 아닌 회원들의 모든 정보 조회
select * from member
where id<>'wltmd0426';
-- 위와 같은 결과
select * from member
where id!='wltmd0426';
```

##### is null				null 정보를 조회

##### is not null		null이 아닌 정보를 조회

```sql
-- 분류 번호가 없는 상품 조회
select * from goods where cno is null;
-- 분류 번호가 있는 상품들의 모든 정보 조회
select * from goods where cno is not null;
```



#### distinct 중복 값 제거

```sql
-- 상품이 있는 분류 번호 조회
select distinct cno from goods;
```

```sql
-- 가격이 5000원 이상 15000원 이하인 제품들의 모든 조회
select * from goods
where price >= 5000 and price<=15000;
```



#### between 범위 검사

* 작은값 이상 큰값 이하의 데이터를 조회할 때 사용
* 작은값과 큰 값 위치를 바꾸면, 조회가 안됨.
* **형식] 컬럼명 between 작은값 and 큰값**

```sql
select * from goods
where price between 5000 and 15000;
```

```sql
-- 큰값 and 작은값 -> 조회가 안됨
select * from goods
where price between 15000 and 5000;
-- 조건(15000이상이면서 5000원 이하)에 맞는 데이터가 없기 때문에 조회 불가.
```

```sql
-- 분류 번호(cno)가 10번이거나 20번인 상품들의 상품번호, 이름, 가격, 분류 번호를 조회
select cno, brand,price,cno
from goods
where cno = 10 or cno = 20 -- cno='10' or cno='20'
-- mysql은 varchar를 '10'으로 안하고 10으로 조회해도, 된다. 다른건 불가.
```



##### in	열거형 데이터로 조회할 때

##### 	    주어진 데이터 중 하나라도 일치하면 조회 됨.

- 형식] 컬럼명 in (값, 값, ...)

##### not in

​	    주어진 데이터와 모두 일치하지 않으면 조회 됨.

 ```sql
-- 분류 번호가 10, 20번이 아닌 상품들을 조회
select gno, brand, price, cno
from goods
where cno!='10' and cno!='2';
-- 위와 같은 코드.
select gno, brand, price, cno
from goods
where cno not in ('10','20');
 ```



```sql
-- 제조사가 LG나 롯데가 아닌 상품들을 조회
select gno, brand, maker, price, cno
from goods
where maker not in('LG','롯데');
```



#### like

- 제공한 조건이 포함되어 있는 데이터를 조회
- 대체 문자 (와일드 카드)를 사용해야 한다.
  - _ : 한 문자 대체
  - % : 문자 길이 상관X
- = 보다 오래 걸리기 때문에, 꼭 필요할 때에만 사용하도록 할 것.

```sql
'김__' -> 성이 김씨이며 이름이 두글자
'김%' -> 성이 김씨인 모든 이름 추출
'%title%' -> title데이터가 들어가는것 모두 추출
```

```sql
-- 삼성이라는 글자가 들어가 제조사가 삼성인 모든 제품을 조회
select *
from goods
where maker like '%삼성%';
```

```sql
-- 상품명이 마우스인 모든 제품을 조회
select *
from goods
where brand like '%마우스%';
```

```sql
-- 삼성이라는 글자가 들어가 제조사가 삼성인 모든 제품을 조회
-- 느려서 쓰지 않는게 좋음.
select *
from goods
where maker not like '%삼성%';
```



#### case ~when ~then		when에 지정된 조건이 참이면, then이 수행됨.

형식]

​		case

​				when  조건 then 수행할 값, 연산식

​				when ...

​				else 주어진 조건이 모두 아닌 경우 수행할 값, 연산식

​		end

```sql
-- 분류 번호가 10인 경우 음식
-- 20인 경우 전자제품
-- 30인 경우 책
-- 40인 경우 가구
select gno, brand, price
	,case
		when cno='10' then '음식'
        when cno='20' then '전자제품'
        when cno='30' then '책'
        when cno='40' then '가구'
        else '미분류'
	end as 분류명
from goods;
```

```sql
/*
	상품번호, 상품명, 인상전 가격, 인상 후 가격을 조회 하시오.
    가격 인상은 다음과 같다.
    가격이 10000원 미만이면 15%인상하고 10000원 이상은 10%가격을 인상하시오.
*/
select gno, brand, price as "인상 전 가격" -- alias 부여시 공백이 들어간 컬럼명은 ""로 표시. mysql은 작은 따옴표로도 가능.
	,case
		when price<10000 then price*1.15
--      when price>=10000 then price*1.1 과 같음
        else price*1.1
	end as "인상 후 가격"
from goods;
```



### order by : 정렬

- 기본적으로 오름차순으로 정렬한다.
- asc : 오름차순, desc : 내림차순

```sql
-- 가격을 기준으로 오름차순으로 정렬
select gno, brand, price
from goods
order by price; -- 기본이 오름차순이므로 asc는 생략해도 됨.

-- 가격을 기준으로 내림차순으로 정렬
select gno, brand, price
from goods
order by price desc; 
```

```sql
-- 분류번호를 기준으로 내림차순 정렬, 같은 분류번호 내에서는 제품명을 기준으로 오름차순
select gno, brand, price, maker, cno
from goods
order by cno desc, brand;
```

```sql
/*
	상품번호, 상품명, 인상전 가격, 인상 후 가격을 조회 하시오.
    가격 인상은 다음과 같다.
    가격이 10000원 미만이면 15%인상하고 10000원 이상은 10%가격을 인상하시오.
    인상된 가격을 기준으로 오름차순으로 정렬하시오
*/
-- "인상 후 가격" 등으로 공백으로 alias를 줄 시 정렬 안됨.
select gno, brand, price
	,case
		when price<10000 then price*1.15
--      when price>=10000 then price*1.1 과 같음
        else price*1.1
	end as INC_price
from goods
order by INC_price;
```



