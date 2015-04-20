prompt PL/SQL Developer import file
prompt Created on 2015年1月28日 by Administrator
set feedback off
set define off
prompt Disabling triggers for SYS_USER...
alter table SYS_USER disable all triggers;
prompt Disabling triggers for SYS_USER_ALLOC...
alter table SYS_USER_ALLOC disable all triggers;
prompt Loading SYS_USER...
insert into SYS_USER (SYS_USER_ID, PARTY_ROLE_TYPE_ID, PARTY_ROLE_ID, SYS_USER_NAME, PASSWORD, SET_PWD_TIME, UPDATE_PWD_TIME, LAST_PWD, CREATE_DATE, STS, STS_DATE, LOCAL_NET_ID, USER_NAME, DEPART_NAME, JIBIE)
values ('9999', 33, '1', 'tt', 'accc9105df5383111407fd5b41255e23', to_date('27-01-2015', 'dd-mm-yyyy'), to_date('27-01-2015', 'dd-mm-yyyy'), null, to_date('27-01-2015', 'dd-mm-yyyy'), 'A', to_date('04-12-2013', 'dd-mm-yyyy'), '471 ', '鱼淑婷', '呼哈路家庭', '网格经理');
commit;
prompt 1 records loaded
prompt Loading SYS_USER_ALLOC...
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20000, '9999', 116, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20001, '9999', 117, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20002, '9999', 118, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20003, '9999', 119, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20004, '9999', 120, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20005, '9999', 121, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20006, '9999', 122, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20007, '9999', 136, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20008, '9999', 137, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20009, '9999', 1017, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20010, '9999', 138, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20011, '9999', 131, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20012, '9999', 132, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20013, '9999', 133, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20014, '9999', 134, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20015, '9999', 135, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20016, '9999', 100, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20017, '9999', 101, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20018, '9999', 102, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20019, '9999', 103, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20020, '9999', 104, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20021, '9999', 105, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20022, '9999', 106, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20023, '9999', 107, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20024, '9999', 108, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20025, '9999', 109, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20025, '9999', 110, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20026, '9999', 111, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20027, '9999', 112, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20028, '9999', 113, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20029, '9999', 114, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20030, '9999', 115, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
insert into SYS_USER_ALLOC (ALLOC_ID, SYS_USER_ID, FUNC_NODE_ID, SYS_ROLE_ID, GRANT_SYS_USER_ID, ENTRUST_FLAG, ALLOC_AUTH, RANGE_ID, CREATE_DATE, STS_DATE, STS, LOCAL_NET_ID)
values (20031, '9999', 130, 1, '1', 'A', 'A', 1, to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-01-2015 10:54:28', 'dd-mm-yyyy hh24:mi:ss'), 'A', '4710');
commit;
prompt 33 records loaded
prompt Enabling triggers for SYS_USER...
alter table SYS_USER enable all triggers;
prompt Enabling triggers for SYS_USER_ALLOC...
alter table SYS_USER_ALLOC enable all triggers;
set feedback on
set define on
prompt Done.
