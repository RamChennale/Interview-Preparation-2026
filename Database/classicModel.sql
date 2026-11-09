create database classicmodels;  -- creating DB 
use classicmodels; -- using the DB 
show tables; -- get the list of tables 
select * from classicmodels.customers; -- all data from table 
SELECT * FROM classicmodels.employees;
select  firstName from classicmodels.employees; -- get the name col data 
select firstname, lastname from classicmodels.employees;
		-- Use the SELECT FROM statement to select data from a table.
		-- Use the SELECT * to select data from all columns of a table.
        
  -- FUNCTIONS       
select now(); -- NOW() function in the SELECT statement to return the current date and time of the server where MySQL server
 
select concat('ram','  ','chennale');
select concat('ram',' ','chennale') as 'FULL NAME';

--
-- *****************************
select firstName, lastName from classicmodels.employees order by firstName asc, lastName desc;
select * from classicmodels.orderdetails where priceEach order by priceEach desc;

use classicmodels; 
 -- create a simple SP_1
 
delimiter //
create procedure selectAllCustomers()
Begin 
	select * from classicmodels.customers;
End //
delimiter ;

call classicmodels.selectAllCustomers();

-- SP-2 with input parameter
use classicmodels;

delimiter // 
create procedure selectAllCustoByCity(IN cityName varchar(50))
begin 
	select * from classicmodels.customers where city= cityName;
end //
delimiter ;

call selectAllCustoByCity('Versailles');	

use classicmodels;
	select * from classicmodels.customers ;
delimiter //
create procedure selectCustByCityAndPin(in mycity varchar(50), in pincode varchar(50))
begin
	select * from classicmodels.customers where city = mycity and postalCode = pincode;
end //
delimiter ;

call selectCustByCityAndPin('Aachen','52066');




