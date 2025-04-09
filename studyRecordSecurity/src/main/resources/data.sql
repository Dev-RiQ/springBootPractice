create database studyrecordsecuritydb;


insert into studies values(  CURTIME() ,CURDATE() , 30, 2 , 1 ,'공부기록1');
insert into studies values(  CURTIME() ,CURDATE() , 150, 3 , 1 ,'공부기록2');
insert into studies values(  CURTIME() ,CURDATE() , 10, 2 , 2 ,'공부기록3');
insert into studies values(  CURTIME() ,CURDATE() , 5, 4 , 2 ,'공부기록4');
insert into users values(6,'admin','관리자','admin','admin','ROLE_ADMIN','NONE');
update users set role='ROLE_ADMIN' where user_id=8;
