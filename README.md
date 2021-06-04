# woowahan-admin-service

* [참고사항] woowahan-board-service -> woowahan-admin-service -> woowahan-frontend
  -> (테스트 후 데이터 생성) -> woowahan-batch-service 순으로 각 서비스의 README.md 읽고 실행 시
  번거로움 없이 진행 가능합니다.

본 프로젝트는 Admin(User) 서비스를 제공합니다.
<br/>

<프로젝트 실행 방법><br/>
해당 프로젝트는 MySQL 기반 프로젝트입니다. 로컬 PC에 MySQL 환경설정 셋팅이 필요합니다.

1. 본 서비스를 실행 시키기 전 woowahan-board-service 의 README.md 를 참고하여 필요한 table 들을 생성해야 합니다.
2. /resources/application.yml 파일의 datasource.url, datasource.username, datasource.password
   값을 로컬 환경의 MySQL 설정 값으로 수정하여야 합니다.  
3. ./gradlew build 을 실행합니다.
4. java -jar build/libs/woowahan-admin-service-0.0.1-SNAPSHOT.jar

* 본 서비스는 Swagger UI 를 제공합니다. http://localhost:8001/swagger-ui/#/

#Code Convention
* (Entity)
1. Entity 의 @Id 필드명은 'id' 로 통일합니다.
2. 각 Entity 가 Immutable 상태를 유지할 수 있도록 특별한 경우를 제외하고 Setter 제공을 금지합니다.
3. Entity equals() 재정의 시 꼭 hashcode() 도 재정의 해주시길 바랍니다.
* (DTO)
1. RequestParam 객체는 /dto/request 경로에 *Request 파일명으로 생성바랍니다.
2. RequestBody 객체는 /dto/request 경로에 *RequestBody 파일명으로 생성바랍니다.
3. Client 에게 전달되는 객체는 *View 파일명으로 재정의 바랍니다. 특별한 경우를 제외하고 엔티티 상태로 반환을 금지합니다.

#Todo
1. Gateway 서비스 구현하여 @CrossOrigin 제거 (보안 위험)