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
  * 