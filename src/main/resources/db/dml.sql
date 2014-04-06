insert into HOBBY (NAME, DESCRIPTION) values ('Snowboarden', 'Surfen op sneeuw ...');
insert into HOBBY (NAME, DESCRIPTION) values ('Surfen', 'Surfen ...');

insert into USER (NAME) values ('TEST USER 1');
insert into USER (NAME) values ('TEST USER 2');
insert into USER (NAME) values ('TEST USER 3');

insert into USER2HOBBY (USER_ID, HOBBY_ID) values (1,1);
insert into USER2HOBBY (USER_ID, HOBBY_ID) values (1,2);
insert into USER2HOBBY (USER_ID, HOBBY_ID) values (2,1);
insert into USER2HOBBY (USER_ID, HOBBY_ID) values (3,2);

insert into TODO (DESCRIPTION, USER_ID) values ('Learn angular js', 1);
insert into TODO (DESCRIPTION, USER_ID) values ('Become snowboard teacher', 1);
insert into TODO (DESCRIPTION, USER_ID) values ('Move to zwitserland', 1);
insert into TODO (DESCRIPTION, USER_ID) values ('Op tijd in bed kruipen', 1);
insert into TODO (DESCRIPTION, USER_ID) values ('Functioneringsgesprek', 1);