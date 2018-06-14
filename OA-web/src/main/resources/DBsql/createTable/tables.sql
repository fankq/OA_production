--创建部门信息表
create table dept_inf
(
ID NUMBER(11) NOT NULL PRIMARY KEY, 
NAME VARCHAR2(50) NOT NULL,
REMARK VARCHAR2(300) DEFAULT NULL
)
COMMENT ON TABLE DEPT_INF IS '部门信息表';
comment on column dept_inf.id is 'id(主键)';
comment on column dept_inf.name is '部门名称';
comment on column dept_inf.REMARK is '备注';
--创建职位信息表主键序列
create sequence sq_dept_inf_id
increment by 1
start with 1
maxvalue 9999999;

--创建职位信息表
create table job_inf 
(
ID NUMBER(11) NOT NULL PRIMARY KEY, 
NAME VARCHAR2(50) NOT NULL,
REMARK VARCHAR2(300) DEFAULT NULL
)
COMMENT ON TABLE job_inf IS '职位信息表';
comment on column job_inf.id is 'id(主键)';
comment on column job_inf.name is '职位名称';
comment on column job_inf.REMARK is '备注';
--创建职位信息表主键序列
create sequence sq_job_inf_id
increment by 1
start with 1
maxvalue 9999999;


--创建用户信息表
create table user_inf
(
id number(11) not null primary key,
loginname varchar2(50) not null,
password  varchar2(100) not null,
status    number(2) default 1,
createDate timestamp default sysdate not null ,
username  varchar2(50) not null
)
COMMENT ON TABLE user_inf IS '用户信息表';
comment on column user_inf.id is 'id(主键)';
comment on column user_inf.loginname is '登录名';
comment on column user_inf.password is '登录密码';
comment on column user_inf.status is '用户状态';
comment on column user_inf.createDate is '创建日期';
comment on column user_inf.username is '用户名称';
--创建用户信息表主键序列
create sequence sq_user_inf_id
increment by 1
start with 1
maxvalue 9999999;

--创建员工信息表
create table employee_inf(
       id number(11) not null primary key,
       dept_id number(11) not null ,
       job_id number(11)  not null,
       name varchar2(50)   not null,
       card_id varchar2(50) not null,
       address varchar2(100) not null,
       post_code varchar2(50) default null,
       tel varchar2(16) default null,
       phone varchar2(11) not null,
       QQ_NUM varchar2(20) default null,
       email varchar2(50) not null,
       sex char(1) default '1',
       party varchar2(10) default null,
       birthday date default null,
       race varchar2(100) default null,
       education varchar2(100) default null,
       speciality varchar2(100) default null,
       hobby varchar2(100)default null,
       remark varchar2(500) default null,
       create_date timestamp default sysdate  not null,

        check(sex in ('1','2')),
       constraint fk_job_id foreign key(job_id) references job_inf(id), 
       constraint fk_dept_id foreign key(dept_id) references dept_inf(id)
)
comment on table employee_inf is '员工信息表';
comment on column employee_inf.id is '员工id';
comment on column employee_inf.dept_id is '部门id';
comment on column employee_inf.name is '员工姓名';
comment on column employee_inf.post_code is '邮编';
comment on column employee_inf.race is '民族';
comment on column employee_inf.hobby is '爱好';
comment on column employee_inf.speciality is '专业';
comment on column employee_inf.remark is 'remark';
comment on column employee_inf.create_date is '创建日期';
--创建员工信息表主键序列
  create sequence sq_employee_inf_id
  increment by 1
  start with 1
  maxvalue 9999999;


--创建通告信息表
create table notice_inf(
       id number(11) not null primary key,
       title varchar2(100) not null,
       context clob not null,
       create_date timestamp default sysdate not null ,
       user_id number(11) default null,
       constraint fk_user_id foreign key(user_id) references  
       user_inf(id)       
)

comment on table notice_inf is '通告信息表';
comment on column notice_inf.id is '通告id';
comment on column notice_inf.title is '通告标题';
comment on column notice_inf.context is '通告内容';
comment on column notice_inf.create_date is '发布日期';
comment on column notice_inf.user_id is '发布人';
--创建主键通告信息id
   create sequence sq_notice_inf_id
    increment by 1
    start with 1
    maxvalue 9999999;

--创建文档信息表
create table document_inf(
  id number(11) not null PRIMARY key,
  title varchar2(50) not null ,
  filename varchar2(300) default null,
  remark varchar2(300) not null,
  create_date TIMESTAMP default sysdate not null ,
  user_id number(11) DEFAULT  null,
  constraint fk_document_user_id foreign key
  (user_id) REFERENCES user_inf(id)
)
comment on table document_inf is '文档信息表';
--创建主键文档信息id
create sequence sq_document_inf_id
    increment by 1
    start with 1
    maxvalue 9999999;