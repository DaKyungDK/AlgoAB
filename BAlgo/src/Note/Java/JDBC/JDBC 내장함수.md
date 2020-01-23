#### JDBC 내장함수

floor() : 버림

ceilling() : 올림

round(숫자, 자릿수) 반올림

```sql
select gno, brand, price
	,floor(case
			when price<10000 then price*1.15
			else price*1.1
			end) as INC_price
from goods
order by INC_price;
```



#### dual 테이블

- Dumy 테이블( 1행 1열) , 모든 테이블에 존재
- 함수의 수행 결과를 조회할 경우에는 select문을 통해서만 조회
  - 따라서 함수 결과만 조회할 경우 dual테이블을 이용해서 조회



#### 문자열 연결

- 주어진 문자열들을 하나로 연결시켜준다.
  - mysql : concat('문자열','문자열','문자열', ... )
  - 기타   : 문자열 || 문자열 || 문자열 ...

```sql
select concat(brand, ' 상품은 ',maker,' 제조사에서 생산한 제품입니다')
from goods;
```



#### 문자열 추출

left ( column or 문자열, 추출할 개수) 				: 왼쪽에터 지정한 개수까지의 문자열을 추출
right ( column or 문자열, 추출할 개수)			   : 오른쪽부터 지정한 개수까지의 문자열을 추출
substring ( column or 문자, 시작위치, 개수) 	: 왼쪽의 시작 위치부터 지정한 개수만큼 추출

​	또는 substr(column or 문자, 시작위치, 개수)

- 시작 위치는 1부터!

```sql
select brand, right(brand,2),left(brand,2),substring(brand,2,3),substr(brand,2,3)
from goods;
```

![1565585762226](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565585762226.png)

```sql
-- repeat(column or 문자, 숫자 ) : 지정한 문자열을 지정할 수 만큼 반복
select repeat('hello',5) from dual;
```

![1565586158304](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565586158304.png)

length() 	: 문자열 길이
reverse()   : 문자열을 거꾸로 표시
upper() 	: 문자열을 대문자로 변환
lower()	  : 문자열을 소문자로 변환

```sql
select email, length(email), reverse(email),upper(email),lower(email)
from member;
```



#### 공백 삭제

- rtrim(column or 문자) : 오른쪽의 공백 삭제

- ltrim(column or 문자) : 왼쪽의 공백 삭제

- trim(column or 문자) : 좌우의 공백 삭제

  ```sql
  select rtrim('     hello      '), ltrim('     hello'     ), trim('    hello      ')
  from dual;
  ```

  

#### 논리 관련 함수

​    case when ~ then : ANSI(표준) Query
​    if(논리식, 참일 때, 거짓일 때)
​    - 논리식이 참이면 참일 떄 값을 출력하고 논리식이 거짓이면 거짓일 때 출력한다.

```sql
/*
	상품번호, 상품명, 인상전 가격, 인상 후 가격을 조회 하시오.
    가격 인상은 다음과 같다.
    가격이 10000원 미만이면 15%인상하고 10000원 이상은 10%가격을 인상하시오.
    인상된 가격을 기준으로 오름차순으로 정렬하시오
*/
select gno, brand, price
	,case
		when price<10000 then price*1.15
--      when price>=10000 then price*1.1 과 같음
        else price*1.1
	end as INC_price
from goods
order by INC_price;
-- 위 문장과 같음.
select gno,brand,price
	,if(price<10000,price*1.15
    ,if(price<=50000,price*1.1,price *1.05)) as INC_price
from goods;
```

#### Oracle인 경우

​    decode( 논리식, 참일 때, 거짓일 때)
​    decode( 논리식, 참일 때
​			, 논리식, 참일 때
​            , 논리식, 참일 때
​            , 논리식, 참일 때
​            , 논리식, 참일 때
​            ...
​            ,거짓일 때)

```sql
-- 분류 번호가 10인 경우 음식
-- 20인 경우 전자제품
-- 30인 경우 책
-- 40인 경우 가구
select * from category;
select gno,brand,cno
	,if(cno='10','음식'
    ,if(cno='20','전자제품'
    ,if(cno='30','책',if(cno='40','가구','미분류')))) as 분류명
from goods;
```



```sql
/*
	상품번호, 상품명, 분류번호 조회 단, 분류번호가  null인 경우 미분류로 표시
*/
select gno, brand
	,if(cno is null,'미분류',cno) as cno
from goods;

/*ifnull(문자 or column, 대체 정보) : null인 경우 대체 정보로 조회됨.*/
select gno, brand, ifnull(cno,'미분류') as cno
from goods;
```



#### 날짜 관련 함수

```sql
select curdate(), curtime(), now(), sysdate(), current_timestamp() from dual;
```

![1565588686449](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565588686449.png)

current_timestamp : 더 정밀한 시간.



### 날짜 계산

- DATE_ADD(날짜, INTERVAL 기준값), ADDDATE(날짜, INTERVAL 기준값) 날짜에서 기준값 만큼 더한다.

- DATE_SUB(날짜, INTERVAL 기준값), SUBDATE(날짜, INTERVAL 기준값) 날짜에서 기준값 만큼 뺀다.
- 기준값 : YEAR, MONTH, DAY, HOUR, MINUTE, SECOND

```sql
/*오늘*/
select date_add(sysdate(),interval 1 day) from dual;
select ADDDATE(sysdate(),interval 1 day) from dual;
/*어제*/
select date_sub(sysdate(),interval 1 day) from dual;
select SUBDATE(sysdate(),interval 1 day) from dual;
```



##### DAYOFWEEK( 날짜) 날짜의 주별 일자 출력(일요일(1),월요일(2), ... , 토요일(7))

```sql
select DAYOFWEEK(SYSDATE())
from dual;

select sysdate() as today
	,case when DAYOFWEEK(sysdate()) = 1 then '일요일'
		  when DAYOFWEEK(sysdate()) = 2 then '월요일'
          when DAYOFWEEK(sysdate()) = 3 then '화요일'
          when DAYOFWEEK(sysdate()) = 4 then '수요일'
          when DAYOFWEEK(sysdate()) = 5 then '목요일'
		  when DAYOFWEEK(sysdate()) = 6 then '금요일'
          when DAYOFWEEK(sysdate()) = 7 then '토요일'
	end as '요일'
from dual;
```



##### 날짜의 요일을 숫자로 반환하는 WEEKDAY(월요일(0) ~ 일요일(6) 반환)

```sql
select weekday(sysdate()) from dual;
```



##### 1월1일부터 해당 날짜까지의 날수를 반환하는 DAYOFYEAR! (디데이 계산?!ㅎ)

```sql
select DAYOFYEAR(sysdate()) from dual;
```



##### 오늘 부터 11월 29일까지 남은 날수 계산

```sql
select dayofyear(s'20191129')-dayofyear(sysdate()) from dual;
```



##### 날짜의 년,월,일,시,분,초를 반환

YEAR, MONTH, DAY, HOUR, MINUTE, SECOND

```sql
select year(sysdate()),month(sysdate()),dayofmonth(sysdate())
	,hour(sysdate()),minute(sysdate()),second(sysdate())
from dual;
```



##### 해당 월, 요일의 이름을 반환하는 MONTHNAME / DAYNAME

```sql
select monthname(sysdate()),dayname(sysdate())
from dual;
```



##### 해당 날짜의 분기를 반환해주는 QUARTER (1~4 반환)

```sql
select QUARTER(sysdate()) from dual;
```



##### date_format(column, format)

![1565591413961](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565591413961.png)

```sql
select date_format(sysdate(),'%y년%m월 %d일  %p %h시 %i분 %s초') from dual;
```

![1565591433000](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565591433000.png)



#### group 함수

- 지정한 group에 함수 기능 처리한 데이터가 1개가 조회 된다.
  - max()	min()	avg()	sum()	: null값을 제외하고 연산 처리
  - count(*)          : 모든 행 개수를 조회
  - count(컬럼명) : null이 아닌 행의 개수 조회

- 주의) group의 수와 다른 데이터를 조회하면 mysql : 결과가 나오지만 의미없는 데이터

  ​																		    Oracle : error 발생

- group by로 그룹을 지정하지 않으면 전체 데이터가 한개의 group으로 지정

```sql
select count(*), count(cno)
	, max(price), min(price),sum(price),avg(price)
from goods;
```



```sql
-- 다른건 다 1개만 나오는건데, select cno를 하게되면 여러개가 나온다. 따라서 마지막값 출력.
select cno, count(*), count(cno)
	, max(price), min(price),sum(price),avg(price)
from goods;
```

