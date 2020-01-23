# DML문 (Data Manipulation Language)

- 데이터를 insert, update, delete하는 문장
- DML문을 수행하면 수행된 내용이 임시저장소에 저장되어 실제 DB에는 반영되지 않는다.

- DML문을 수행 후, TCL(Transaction Control Language)을 수행해야 한다. (Auto Commit인 경우 해당 X)

- update, delete문을 수행하면 commit 또는 rollback하기 전까지 해당 행은 locking된다.

### TCL문

​	**commit**									임시 저장소에 저장된 Data를 DB에 반영

​	**savepoint 이름**						임시 저장 위치

​	**rollback**									작업한 모든 DML문 수행을 취소

​	**rollback 세이브포인트이름**	지정한 savepoint까지 취소



auto commit 해제 방법 (다시 설정하려면 체크)

![1565676104761](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565676104761.png)



### 데이터 수정 (update)

```sql
-- 전체 데이터 수정
update 테이블명 set 컬럼명=value;

-- 조건에 맞는 데이터만 수정
update 테이블명 set 컬럼명=value
where 조건;
```



### 데이터 추가 (insert)

```sql
insert into 테이블명(컬럼명, ...) values(값, ...) -- 컬럼과 순서대로 매칭됨
```

- 테이블에 지정한 컬럼에 지정한 값으로 추가

```sql
insert into 테이블명 values(값, ...)
```

- 테이블 구조에 설정된 모든 컬럼에 구조에 지정된 순서대로 값이 설정되서 insert됨



```sql
-- auto commit 해제 한 후, 이 문장을 수행 한 후, commit을 안하고 나갔다가 오면, db에 insert안되있음.
insert into goods(brand,price,maker,cno)
values('미니선풍기',22000,'카카오프렌즈',20);
```



### 데이터 삭제 (delete)

- 전체 데이터 삭제 (삭제가 안되지만, 아래 사진 참고 - 체크 해제해야 삭제 가능.)
	
	```sql
	delete from 테이블명;
	```
	
- 조건에 맞는 데이터만 삭제
	
	```sql
	delete from 테이블명 where 조건;
	```



```sql
delete from emp;
delete from emp where sal< 2000;
rollback;
select * from emp;
commit;
```

![1565678308999](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565678308999.png)

edit->preference->sql editor 이걸 체크 해제하면 delete 테이블 가능.