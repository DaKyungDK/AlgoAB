# Group By

##### 데이터를 특정 컬럼의 데이터를 기준으로 group으로 나눠서 group함수 적용

##### group by 컬럼명 지정한 컬럼의 데이터를 분류해서 group을 생성

```sql
select cno, count(*), avg(price)
from goods
group by cno; -- 자동으로 distinct 됨
```



```sql
-- 부서별 급여 평균과, 최소 급여, 최대 급여를 조회. 부서별로 볼거기 떄문에, deptno도 select하기.
-- avg만 소숫점 밑으로 많이나와서, 소숫점 둘쨋자리에서 반올림 했음.
select deptno, round(avg(sal),2), min(sal), max(sal)
from emp
group by deptno;
```

```sql
-- 업무별 근무 인원, 급여 평균과, 최소 급여, 최대 급여를 조회
select job, round(avg(sal),2), min(sal), max(sal)
from emp
group by job;
```



```sql
-- 업무별 근무 인원, 급여 평균과, 최소 급여, 최대 급여를 조회
select ifnull(job,'total'), count(*),round(avg(sal),2), min(sal), max(sal)
from emp
group by job with rollup; -- roll up 은 전체 통계 - job부분이 null이 된다. 

* 윗부분 설명 *
-- 이게 roll up에 의해서 추출된 부분.
select job, count(*),round(avg(sal),2), min(sal), max(sal)
from emp;
-- null인 부분을, 위에있는 ifnull을 이용해 total로 채움.
select job, count(*),round(avg(sal),2), min(sal), max(sal)
from emp
group by job with rollup;
```



```sql
-- 부서별, 업무별 근무 인원, 급여 평균과 최소급여, 최대 급여를 조회
-- 가장 위에 전체 통계 나오고, 중간에 부서별 통계가 나온다(그룹바이가 두개기 때문)
select deptno, job, count(*), round(avg(sal),2),min(sal),max(sal)
from emp
group by deptno, job with rollup -- 첫번째 컬럼별 통계도 내준다.
		-- 1차	2차 분류 .. 처음으로 부서별로 나누고, 업무별로 나눔
order by deptno;
```



![1565657572127](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565657572127.png)

##### 오라클 버전 : rollup이 함수

```sql
select deptno, job, count(*), round(avg(sal),2),min(sal),max(sal)
from emp
group by rollup(job); -- 이부분 다름
```



업무별 급여 평균이 2500이상의 업무 조회  ->where쓸수 없다. where 행 하나하나에 대하여 처리가능

##### group에 대한 조건은 having으로 조건 걸음



#### having

- group의 값으로 조건
- 형식] group by 컬럼, ...
	   having 조건
where => 행 하나 하나에 대한 조건 처리.. having은 group by를 쓸때 사용.

```sql
-- 업무별 급여 평균이 2500이상인 업무 조회
select job, round(avg(sal),2) as avgSal -- as 생략 가능하지만, 써주는게 가독성이 좋다.
from emp
group by job -- ; -- 이걸 쓰면 having쓰기 전꺼 볼수있음.
having avg(sal)>=2500;
```

```sql
-- 카테고리별 가격 평균을 조회. 단, 가격 평균이 500000이상인 카테고리는 제외하고 조회한다.
select cno, round(avg(price),2) as avgPrice
from goods
group by cno
having avg(price)<500000;
```

```sql
-- 급여가 1500이상인 사원들의 부서별 급여 평균을 조회
-- 단, 급여 평균이 2000이상인 부서만 조회 -> 보통 조건에 평균, 최대 이런거가 들어가면 having으로 조건.
select *, avg(sal) as avgSal
from emp
where sal>=1500
group by deptno
having avg(sal)>=2000;
```



```sql
select cno, round(avg(price),2) as avgPrice
from goods
group by cno with rollup;
```

![1565659710687](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565659710687.png)

#### cno가 null이어서 나온 것과 rollup에 의한 null을 분류하고 싶을 떄.

##### grouping()

rollup에 의해 조회된 데이터는 1, 그렇지 않은 데이터는 0이 된다.

```sql
select cno,grouping(cno), round(avg(price),2) as avgPrice
from goods
group by cno with rollup;
```

![1565659805303](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565659805303.png)

##### grouping응용

```sql
-- cno가 null인 경우 미분류로 표시, 그렇지 않으면 카테고리 번호로 표시
-- rollup에 의해 null인 경우 total
select if(grouping(cno)=1, 'total',ifnull(cno,'미분류')) as cno
	, round(avg(price),2) as avgPrice
from goods
group by cno with rollup;
```

![1565660016776](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565660016776.png)



### Pivot table (시험에 안나옴.)

![1565660170081](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565660170081.png)

위 테이블을 2차원적으로 보고싶을 때,

부서별 업무별 평균 급여를 Pivot table로 표시하기.

``` sql
select if(grouping(deptno)=1, 'total',ifnull(deptno,'신입사원')) as deptno
	, round(avg(if(job='CLERK',sal,0)),2) as CLERK
    , round(avg(if(job='MANAGER',sal,0)),2) as MANAGER
    , round(avg(if(job='PRESIDENT',sal,0)),2) as PRESIDENT
    , round(avg(if(job='ANALYST',sal,0)),2) as ANALYST
    , round(avg(if(job='SALESMAN',sal,0)),2) as SALESMAN
    , round(avg(sal),2) as JobTotal
from emp
group by deptno with rollup 
order by deptno;
```

![1565660750236](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565660750236.png)

##### 위 식에서는, 원하는 job이 아닐 때 평균을 구할 때 null이 아닌 0도 영향을 주기 때문에, 문제가 있음

- if에 null을 넣어야, job이 원하는게 아닐 때 통계를 낼때 영향을 안줌.

![1565660971715](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565660971715.png)

![1565660995871](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565660995871.png)

