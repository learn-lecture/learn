### TypeQuery, Query

* 반환 값이 있을 때 TypeQuery
`TypeQuery<T>`
* 반환 값이 없을 때 Query

### ResultList vs SingleResult

* ResultList -> 결과 없으면 empty 리스트 반환 
* SingleResult -> 결과에 따라 예외 발생
  * 결과 X: NoResultException
  * 결과 > 1: NonUniqueResultException
  * try-catch 의 복잡함
  * Spring Data Jpa 에서 Optional 반환

### Parameter Binding

* 이름 기준, 위치 기준 바인딩 가능
* 위치 기반은 비추
  * 필드 추가 위치에 따라 순서가 밀림

### Projection

* Select 절에 조회할 대상을 지정하는 것
* Select 한 대상들이 영속성 컨텍스트에서 관리 됨
* 여러 값 조회 Query
  * 한계가 명확 - Object를 반환
  * DTO를 생성해서 select new package.dto 로 생성 가능
  
### Paging

* setFirstResult, setMaxResults 로 페이징 가능
  * Hibernate 의 기술이 아니라면 oracle 같은데선 굉장히 긴 쿼리가 됨
  * JDBCTemplate 의 한계를 해결해주는 기술

### JOIN

* 별거 없어서 생략

### Sub Query

* 서브 쿼리 지원 함수 
  * EXISTS
    * ALL
    * ANY/SOME
  * IN
  
* FROM 절은 SubQuery가 안됨
  * SELECT * FROM (SELECT ... ) X
  * JOIN으로 풀어서 해결
  * 그 외 해결법
    * Native Query
      * 유지 보수 더러움
      * Java가 SQL 의존적
      * 하지만 가장 직빵
    * Select 2번 
      * 네트워크 비용 2배
      * 하지만 캐싱 적용 가능
      * 데이터가 너무 크면 Native
    * Application에서 작업
      * 코드로 제어
      * FROM 절 SubQuery는 주로 VIEW를 위해 작업
      * VIEW 는 Application 에서 관리하는 관심사가 더 적당
    * 상황에 따라 적절하게 선택

### JPQL 타입 표현과 기타식
  * 상속관계에서 `where type() = ?` 로 해결 가능

### 조건식
  * 일반 CASE
    * `select case when {condition} then {result} else {result} end from ...`
  * 단순 CASE
    * `select case {criteria} when {condition} then {result} else ...`
  * coalesce
    * `select coalesce({field}, {result}) from ...`
    * null이면 result 반환
  * nullif
    * `select nullif({field}, {criteria} from ...`
    * field가 criteria와 동일하면 null을 반홚

### JPQL 기본 함수
  * Dialect 에 따라 Hibernate 에서 기본 함수들을 등록해줌.
    * 없는 함수같은 경우 `Dialect.registerFunction()` 을 통해 등록해줘야 됨.
      * 해당 방식은 Hibernate 5.x에 대한 방식이다.
    * 등록된 방언 클래스로 properties 를 설정
    * 언제 쓰일까?
      * DBA가 custom function을 만들었을 때
    * Hibernate 6.x 부터는 어떻게 할 수 있을까?
      * Hibernate 5.x 처럼 Dialect를 상속받을 수 있지만, 권장되지 않는 방식)
      * Hibernate 6.x 부터는 FunctionContributor를 구현하는게 BestPractice
      * Code 참조