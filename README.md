# Spring Boot Review

화면 없이 **REST API만으로** 게시판 CRUD와 로그인을 만들어 보며 Spring Boot 기본기를 다지는 연습 프로젝트입니다.

## 기술 스택

| 구분 | 사용 기술 |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.1.2 (Spring Web MVC) |
| ORM | Spring Data JPA (Hibernate) |
| Database | H2 (in-memory) |
| Build | Gradle (Groovy DSL) |
| Etc | Lombok, Postman |

## 프로젝트 구조

```
src/main/java/com/yuchan/practice
├── PracticeApplication.java     # 시작점 (@SpringBootApplication)
├── controller/                  # 요청을 받고 응답을 돌려줌
│   └── BoardApiController.java
├── service/                     # 비즈니스 로직, 트랜잭션
│   └── BoardService.java
├── domain/
│   ├── entity/                  # DB 테이블과 매핑되는 클래스
│   │   └── Board.java
│   └── repository/              # DB 접근 (JpaRepository)
│       └── BoardRepository.java
├── dto/                         # 계층 간 데이터 전달용 객체
│   ├── BoardSaveRequestDto.java
│   ├── BoardUpdateRequestDto.java
│   └── BoardResponseDto.java
└── global/                      # 공통 설정, 예외 처리 (예정)
```

요청은 항상 이 방향으로 흐릅니다.

```
Client(Postman) → Controller → Service → Repository → DB
                     DTO  ←→  Entity
```

## API

| 기능 | Method | URL | 상태 |
|---|---|---|---|
| 게시글 등록 | `POST` | `/api/v1/posts` | ✅ |
| 게시글 단건 조회 | `GET` | `/api/v1/posts/{id}` | ✅ |
| 게시글 수정 | `PUT` | `/api/v1/posts/{id}` | ✅ |

### 요청 예시

```http
POST /api/v1/posts
Content-Type: application/json

{
  "title": "첫 글",
  "content": "안녕하세요",
  "author": "yuchan"
}
```

```http
PUT /api/v1/posts/1
Content-Type: application/json

{
  "title": "수정된 제목",
  "content": "수정된 내용"
}
```

## 실행 방법

1. JDK 21을 준비합니다.
2. IntelliJ에서 `PracticeApplication`의 `main`을 실행하거나, 터미널에서 실행합니다.

   ```bash
   ./gradlew bootRun
   ```

3. `http://localhost:8080` 으로 Postman 요청을 보냅니다.

테스트 실행:

```bash
./gradlew test
```

## 공부 노트

직접 정리한 개념 문서입니다.

- [Controller, Service, Repository: 3계층 구조 이해하기](<docs/구조이해하기.md>)
- [어노테이션(@) 이해하기](<docs/어노테이션(@) 이해하기.md>)

## 트러블슈팅

### 수정 API가 200을 반환하는데 DB 값이 바뀌지 않음

- **원인**: `BoardService.update()`에 `@Transactional`이 빠져 있었습니다. JPA의 변경 감지(Dirty Checking)는 트랜잭션이 끝날 때 UPDATE 쿼리를 보내기 때문에, 트랜잭션이 없으면 메모리의 객체만 바뀌고 끝납니다.
- **해결**: `update()`에 `@Transactional` 추가
- **배운 점**: 에러 없이 조용히 틀리는 버그는 "수정 후 다시 조회"처럼 결과를 직접 확인해야 잡을 수 있습니다.

### `@Transactional` import 실수

- `jakarta.transaction.Transactional`이 자동 import되어 있었습니다.
- 스프링 프로젝트에서는 `readOnly` 같은 옵션을 지원하는 `org.springframework.transaction.annotation.Transactional`을 사용합니다.

## 참고 자료

- [hianna - Spring Boot 입문 시리즈](https://hianna.tistory.com/1174) (게시판 CRUD 백엔드 API)
- [hianna - 3계층 구조 이해하기](https://hianna.tistory.com/1170)
- [hianna - 자주 쓰는 어노테이션](https://hianna.tistory.com/1171)
