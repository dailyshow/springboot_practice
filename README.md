# springboot_practice
스프링부트 회원 가입 및 게시판 연습

# mysql 사용자 및 database 생성 
create database db_codingrecipe;

create user user_codingrecipe@localhost identified by '1234';

grant all privileges on db_codingrecipe.* to user_codingrecipe@localhost;

## application.yml
처음 실행할 때

hibernate:

ddl-auto: create 를 입력하고 두번째 부터는 update로 변경해줘야함
create 를 그대로 두게 되면 테이블이 삭제되고 다시 만들어짐

ddl-auto: update
