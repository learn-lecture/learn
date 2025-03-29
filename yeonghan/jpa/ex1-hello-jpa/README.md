### 간단한 정리

엔티티 매니저 팩토리
- 어플리케이션 전체 공유
  엔티티 매니저
- 쓰레드 간 공유가 되지 않음.
- 쓰레드 하나 당 점유
  데이터 변경
- 한 트랜잭션 안에서
    - Why?
    - 쓰레드 하나가 EM을 점유하기 때문
    - 데이터 변경을 두 트랜잭션에 거쳤을 때, 발생하는 문제점
      - 데이터 무결성을 침해 

영속성 컨텍스트
- 엔티티를 저장하는 영구적인 환경
- 논리적인 개념
  비영속/영속/준영속/삭제
- 비영속: 객체 레벨
- 영속: 객체와 DB 사이 레벨
    - ID: 객체로 1차 캐싱
    - find 연산 시 1차 캐시에서 선 조회
    - 1차 캐시에 없을 경우 DB 조회 후 1차 캐시에 저장
    - 쓰기지연
        - 트랜잭션 간 1차 캐시에 객체 저장 후 한 번에 query 발행
        - commit 시점에 query를 발행하는 구조
            - 1차 캐시에 데이터가 있으면, 따로 저장을 하지 않아도 commit에서 알아서 식별
            - ID: 객체 | 스냅샷 과 같은 형태로 있음.
    - Flush
      - 스냅샷과 객체를 비교 후 변경사항이 있을 경우, flush (Dirty Checking)
      - 한 트랜잭션 내 JPQL 쿼리 실행 시 Flush가 자동으로 호출 됨
      - JPQL은 DB 레벨에 직접 접근, DB에 데이터가 있어야 하므로 1차 캐시 내용이 미리 Flush 됨
      - FlushModeType으로 관리 가능
- 준영속: 영속 -> 영속성 컨텍스트에서 분리됨 (Detached)

Column Unique vs Table Unique
- Column Unique는 이름 설정이 랜덤
- Table Unique를 활용하는 것이 에러 식별 가능성에 좋음

@Temporal
- Legacy에서 Date 필드를 쓸 경우만

@Id
- Identity
  - DB에게 ID 전략을 맡김
  - MySQL Auto Increment의 단점 
    - 트랜잭션 내에서 1회 Insert가 필요
- squence
  - oracle, postgre와 같은 DB의 ID 전략
  - 마찬가지로 트랜잭션 내 1회 select가 필요
    - 성능을 최적화 하기 위해 AllocationSize를 활용가능
- Table
  - 비추, DB 낭비 + 성능 감소
  - squenece와 유사