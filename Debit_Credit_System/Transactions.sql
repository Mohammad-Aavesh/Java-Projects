create database RECORDS;
use RECORDS;
create table transactions(Date varchar(15),Type varchar(10),Amount int,Descrip varchar(50));
select * from transactions where Amount = 4000;
use RECORDS;
alter table students add id INT AUTO_INCREMENT PRIMARY KEY;
select * from students;
Insert into students (Full_Name,Marks,City) values ("bhavyansh",67,"Harda");
