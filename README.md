# Use Case

![](https://lavish-archeology-de0.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fdb74ba2d-24e4-4d4b-ae5c-07d99cd33ca1%2Fa619043f-cda3-47de-bdf0-79a011c8c897%2F%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-06-05_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_3.34.26.png?table=block&id=8c358dbe-e1b4-42db-8061-aab39af8168f&spaceId=db74ba2d-24e4-4d4b-ae5c-07d99cd33ca1&width=1420&userId=&cache=v2)

# 시스템 구성도

![](https://lavish-archeology-de0.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fdb74ba2d-24e4-4d4b-ae5c-07d99cd33ca1%2Fccbe5a9a-7a7c-4761-acf1-0efb9f612e77%2F%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-06-05_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%258C%25E1%2585%25A5%25E1%2586%25AB_12.20.11.png?table=block&id=adb3a31d-32ae-499f-bf16-8a77abdb04dc&spaceId=db74ba2d-24e4-4d4b-ae5c-07d99cd33ca1&width=1420&userId=&cache=v2)

---

# Domain 분석

---

## `Member`

- id
- username
- password
- auth

## `Article`

- id
- uuid
- title
- memberID (fk)
- contents
- postDate(LocalDate)
- ~~password (추후삭제)~~

## `Comment`

- id
- ~~uuid~~
- contents
- postDate

---

# Domain API Spec

## `/members`

- **POST /signup (회원가입, JWT)**
- **GET /signin (로그인)**

## `/articles`

- **GET / (게시글 전체조회)**
- **POST / (게시글 작성)**
- **GET /{UUID} (게시글 상세 조회)**
- **PATCH / (게시글 수정)**
- **DELETE /{UUID} (게시글 삭제)**

## `/comments`

- **POST / (댓글 작성)**
- **PUT / (댓글 수정)**
- **DELETE / 댓글 삭제**

---

# Configurat**ion**

- **Spring Security**
- **ResponseEnvelope**
- **Token Filter 전역 처리**
- **Global Exception**

---

# ERD

![](https://lavish-archeology-de0.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fdb74ba2d-24e4-4d4b-ae5c-07d99cd33ca1%2F68a0b31e-4108-457c-8516-45da2d37a336%2F%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-06-05_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_4.05.59.png?table=block&id=1666e4c1-c1b8-45ff-bb87-644edd12f833&spaceId=db74ba2d-24e4-4d4b-ae5c-07d99cd33ca1&width=1420&userId=&cache=v2)

---

# 질문

1. 처음 설계한 API 명세서에 변경사항이 있었나요?
   변경 되었다면 어떤 점 때문 일까요? 첫 설계의 중요성에 대해 작성해 주세요!
2. ERD를 먼저 설계한 후 Entity를 개발했을 때 어떤 점이 도움이 되셨나요?
3. JWT를 사용하여 인증/인가를 구현 했을 때의 장점은 무엇일까요?
4. 반대로 JWT를 사용한 인증/인가의 한계점은 무엇일까요?
5. 만약 댓글 기능이 있는 블로그에서 댓글이 달려있는 게시글을 삭제하려고 한다면 무슨 문제가 발생할까요? Database 테이블 관점에서 해결방법이 무엇일까요?