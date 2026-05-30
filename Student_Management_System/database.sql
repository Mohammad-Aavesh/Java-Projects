create database project1;

use project1;

create table student
(roll_no int auto_increment primary key,
name varchar(50),
father_name varchar(50),
class varchar(5),
section char(1),
date_of_birth varchar(15),
address varchar(80),
mobile_no varchar(10));

create table result
(roll_no int auto_increment primary key,
hindi int,
english int,
maths int,
science int,
computer int,
total int,
percentage varchar(10),
grade char(5),
res boolean default false,
exist boolean default false
);

DELIMITER //
create procedure Enter_Data(in n varchar(50),in fn varchar(50),in dob varchar(15),in clas varchar(5),in sec char,in address varchar(80),in mobile varchar(10))
BEGIN
	INSERT INTO student(name,father_name,class,section,date_of_birth,address,mobile_no) values (n,fn,clas,sec,dob,address,mobile);
    Insert INTO result (exist) values(true);
END //
DELIMITER ;

DELIMITER //
create procedure Update_Data(in n varchar(50),in fn varchar(50),in dob varchar(15),in clas varchar(5),in sec char,in addr varchar(80),in mobile varchar(10),in r int)
BEGIN
	UPDATE student SET name = n, father_name = fn, class = clas, section = sec, date_of_birth = dob, address = addr, mobile_no = mobile WHERE roll_no = r;
END //
DELIMITER ;

DELIMITER //
create procedure update_name(in n varchar(50),in r int)
BEGIN
	UPDATE student SET name = n WHERE roll_no = r;
END //
DELIMITER ;

DELIMITER //
create procedure update_father_name(in fn varchar(50),in r int)
BEGIN
	UPDATE student SET father_name = fn WHERE roll_no = r;
END //
DELIMITER ;

DELIMITER //
create procedure update_class(in clas varchar(5),in r int)
BEGIN
	UPDATE student SET class = clas WHERE roll_no = r;
END //
DELIMITER ;

DELIMITER //
create procedure update_section(in sec char,in r int)
BEGIN
	UPDATE student SET section = sec WHERE roll_no = r;
END //
DELIMITER ;

DELIMITER //
create procedure update_address(in addr varchar(80),in r int)
BEGIN
	UPDATE student SET address = addr WHERE roll_no = r;
END //
DELIMITER ;

DELIMITER //
create procedure update_mobile(in mobile varchar(10),in r int)
BEGIN
	UPDATE student SET mobile_no = mobile WHERE roll_no = r;
END //
DELIMITER ;

DELIMITER //
create procedure update_dob(in dob varchar(15),in r int)
BEGIN
	UPDATE student SET date_of_birth = dob WHERE roll_no = r;
END //
DELIMITER ;

DELIMITER //
create procedure Delete_Data(in r int)
BEGIN
	delete from student where roll_no = r;
    delete from result where roll_no = r;
END //
DELIMITER ;

DELIMITER //
create procedure Retrieve_Data()
BEGIN
	select * from student;
END //
DELIMITER ;

DELIMITER //
create procedure get_roll_no()
BEGIN
	select roll_no, name from student;
END //
DELIMITER ;

DELIMITER //
create procedure fetch_roll_no()
BEGIN
	select roll_no from result;
END //
DELIMITER ;

DELIMITER //
create procedure Generate_Result(in r int)
BEGIN
	update result set res = 1 where roll_no = r;
END //
DELIMITER ;

DELIMITER //
create procedure Enter_Marks(in h int,in e int,in m int,in s int,in c int,in t int,in p varchar(10),in g varchar(5),in r int)
BEGIN
	UPDATE result set hindi = h,english = e,maths = m,science = s,computer = c,total = t,percentage = p,grade = g where roll_no = r;
END //
DELIMITER ;

DELIMITER //
create procedure Retrieve_Marks()
BEGIN
	select * from result;
END //
DELIMITER ;

DELIMITER //
create procedure get_total(in r int)
BEGIN
	select roll_no, total from result where roll_no = r;
END //
DELIMITER ;

DELIMITER //
create procedure get_res(in r int)
BEGIN
	select res,name from result natural join student where roll_no = r;
END //
DELIMITER ;

DELIMITER //
create procedure get_MarkSheet(in r int)
BEGIN
	select * from student natural join result where roll_no = r;
END //
DELIMITER ;
