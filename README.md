# 스프링 부트와 JPA로 구현하는 멀티 테넌트

본 프로젝트는 multi tenancy를 tenant per schema로 구현하였다.

## Quick Start
`/src/resources/api.http`를 인텔리제이로 열어서 각 API를 실행해보면 작동을 확인할 수 있다.

## 구현 내용

도메인(즉 비즈니스 로직)에 필요한 테이블들은 `/src/resources/schema_per_tenant.sql`에 정의 되어 있다. 해당 테이블들은 테넌트 등록 시에 해당 테넌트의 스키마에 생성되어야 한다.

반면 테넌트 관리에 필요한 테이블은 `/src/resources/schema.sql`에 정의되어 있다. 해당 테이블들은 서버 시작 시에 생성되야 한다.

테넌트를 동적으로 추가할 수 있다. 테넌트를 등록하면 데이터베이스에 해당 테넌트를 위한 스키마가 동적으로 생성되고 관련된 도메인 테이블도 자동으로 생성된다.

등록된 테넌트의 id를 요청 헤더에 포함해서 로케이션 추가를 할 경우 해당 테넌트의 스키마에 로케이션이 추가된다.(조회 또한 해당 테넌트의 스키마에서 조회된다.)

## 주의 사항

스키마 생성 과정에서 SQL Injection이 발생할 수 있다. 그러나 입력값을 제한하여 이러한 문제를 예방하고자 했다. 자세한 내용은 `src/main/java/com/autoever/multitenancy/admin/TenantSchemaInitializer`의 코드와 주석을 참고하라.