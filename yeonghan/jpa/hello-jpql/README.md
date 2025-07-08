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