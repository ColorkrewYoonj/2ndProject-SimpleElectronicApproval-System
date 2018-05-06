CREATE TABLE p2_employees
                     (
                     employee_ID varchar2(50) primary key
                     ,employee_name varchar2(50)
                     ,department_ID varchar2(20)
                     ,employee_email varchar2(100)
                     ,manager_ID varchar2(50)
                     ,password varchar2(50)
                     ,constraint employees_fk foreign key(department_ID) 
                     references p2_departments(department_ID)
                     );
                     
                     
create table p2_content
                    (
                    content_name varchar2(50)
                    );
insert into p2_content values('근로계약서.doc');
insert into p2_content values('위탁판매계약서.doc');
insert into p2_content values('장비임대계약서.doc');
insert into p2_employees values('말단','윤재호','it','anum91@naver.com','it팀장','1234');
insert into p2_employees values('it팀장','윤재호','it','anum91@naver.com','it부장','1234');
insert into p2_employees values('it부장','윤재호','it','anum91@naver.com','사장','1234');
insert into p2_employees values('사장','윤재호','it','anum91@naver.com','없음','1234');
drop table p2_departments;
drop table p2_signState;
drop table p2_employees;
drop table p2_docDepartment;

create table p2_departments
                        (
                        department_ID varchar2(20) primary key
                        ,department_name varchar(100)
                        ,manager_ID varchar2(50)
                        );
insert into p2_departments values('it','it부','it팀장');
create SEQUENCE p2_departments_seq;
create table p2_docDepartment
                        (
                        signState_ID varchar2(30) default 'w' --쿼리
                        ,department_ID varchar2(20)           
                        ,draftDate date default sysdate       --쿼리
                        ,employee_ID varchar2(50)       
                        ,manager_ID varchar2(50)               --쿼리
                        ,doc_Name varchar2(100)                
                        ,doc_ID number(30) primary key         --쿼리
                        ,doc_content varchar2(1500)
                        ,isPersonal varchar2(30)
                        ,f_sign varchar2(30)                    --쿼리
                        ,s_sign varchar2(30)                    --쿼리   
                        ,t_sign varchar2(30)                    --쿼리
                        ,constraint ID_fk foreign key(employee_ID) 
                        references p2_employees(employee_ID)
                        ,constraint department_fk foreign key(department_ID) 
                        references p2_departments(department_ID)
                        ,constraint signState_fk foreign key(signState_ID) 
                        references p2_signState(signState_ID)
                        );
create SEQUENCE p2_docDpartment_seq;

insert into p2_docDepartment values('w','it',sysdate,'말단','it팀장','게시글1',1,'내용','개인','아직','안','씀');
insert into p2_docDepartment values('w','it',sysdate,'말단','it팀장','게시글2',2,'내용2','개인','아직','안','씀');
insert into p2_docDepartment values('o','it',sysdate,'말단','it팀장','게시글3',3,'내용','개인','아직','안','씀');
insert into p2_docDepartment values('o','it',sysdate,'말단','it팀장','게시글4',4,'내용','개인','아직','안','씀');
insert into p2_docDepartment values('c','it',sysdate,'말단','it팀장','게시글5',5,'내용','개인','아직','안','씀');
insert into p2_docDepartment values('c','it',sysdate,'말단','it팀장','게시글6',6,'내용','부서','아직','안','씀');
insert into p2_docDepartment values('r','it',sysdate,'말단','it팀장','게시글7',7,'내용','부서','아직','안','씀');
insert into p2_docDepartment values('r','it',sysdate,'말단','it팀장','게시글8',8,'내용','부서','아직','안','씀');


--문서상태--
create table p2_signState
                    (
                    signState_ID varchar(30) primary key
                    ,signState varchar(30)
                    );
insert into p2_signState values('w','대기');
insert into p2_signState values('o','진행');
insert into p2_signState values('c','완료');
insert into p2_signState values('r','반려');
                    


commit;