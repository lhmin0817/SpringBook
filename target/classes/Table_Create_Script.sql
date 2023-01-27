Create Table USERS(
  id varchar2(8) primary key,
  password varchar2(8),
  name varchar2(20),
  role varchar2(20)
  );

--drop table users;

insert into users values('admin', '1234', '관리자','Admin');
insert into users values('user1',1234,'홍길동','User');
commit;
select * from users;

create Table Board(
 SEQ number(5) primary key,
  title varchar2(200),
  writer varchar2(20),
  content varchar2(2000),
  regdate date default sysdate,
  cnt number(5) default 0
);

insert into board (seq , title ,writer , content ) values( 1,'가입인사', '관리자', '잘부탁 드립니다');
commit;
select  * from board;

