# My SQL 기본

mysql은 group by에도 select의 alias를 쓸 수 있다. (order by)도
oracle은 order by에만.

Ansi (case when then..)

if - 오라클에선 decode (논리식. 참,거짓)
ifnull - 오라클에선 nvl (값1, 값2) 값1이 아니면 값2 수행

DATE_FORMAT - 오라클에선 to_char(데이터,'날짜')

%Y, %y, %m 외우기

![1570495104723](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1570495104723.png)



count(*) : 테이블의 모든 레코드 수를 구한다.

count(필드명) : NULL 값이 아닌 레코드 수를 구한다.



![1570498837486](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1570498837486.png)



![1570499204429](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1570499204429.png)



primary key로 select -> point query!