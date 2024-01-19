-- Create a sample stored procedure in MySQL
DELIMITER //
CREATE PROCEDURE getEmployeeById (IN empId INT, OUT empName VARCHAR(255), OUT empSalary DECIMAL(10, 2))
BEGIN
    -- Select employee information based on the provided employee ID
    SELECT employee_name, salary INTO empName, empSalary
    FROM world.employees
    WHERE employee_id = empId;
END //
DELIMITER ;
