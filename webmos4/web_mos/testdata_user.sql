prompt PL/SQL Developer import file
prompt Created on 2015年1月28日 by Administrator
set feedback off
set define off
prompt Disabling triggers for SYS_USER...
alter table SYS_USER disable all triggers;
prompt Loading SYS_USER...
insert into SYS_USER (SYS_USER_ID, PARTY_ROLE_TYPE_ID, PARTY_ROLE_ID, SYS_USER_NAME, PASSWORD, SET_PWD_TIME, UPDATE_PWD_TIME, LAST_PWD, CREATE_DATE, STS, STS_DATE, LOCAL_NET_ID, USER_NAME, DEPART_NAME, JIBIE)
values ('9999', 33, '1', 'tt', 'accc9105df5383111407fd5b41255e23', to_date('27-01-2015', 'dd-mm-yyyy'), to_date('27-01-2015', 'dd-mm-yyyy'), null, to_date('27-01-2015', 'dd-mm-yyyy'), 'A', to_date('04-12-2013', 'dd-mm-yyyy'), '471 ', '鱼淑婷', '呼哈路家庭', '网格经理');
commit;
prompt 1 records loaded
prompt Enabling triggers for SYS_USER...
alter table SYS_USER enable all triggers;
set feedback on
set define on
prompt Done.
