# springboot_practice
스프링부트 회원 가입 및 게시판 연습

# mysql 사용자 및 database 생성 
create database db_codingrecipe;
create user user_codingrecipe@localhost identified by '1234';
grant all privileges on db_codingrecipe.* to user_codingrecipe@localhost;