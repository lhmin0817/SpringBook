Create Table USERS(
  id varchar2(8) primary key,
  password varchar2(8),
  name varchar2(20),
  role varchar2(20)
  );

--drop table users;

insert into users values('admin', '1234', '������','Admin');
insert into users values('user1',1234,'ȫ�浿','User');
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

insert into board (seq , title ,writer , content ) values( 1,'�����λ�', '������', '�ߺ�Ź �帳�ϴ�');
commit;
select  * from board;

