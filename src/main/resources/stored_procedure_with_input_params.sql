-- Create a sample stored procedure in MySQL
DELIMITER //
CREATE PROCEDURE getEmployeeInfoById (empId INT)
BEGIN
    -- Select employee information based on the provided employee ID
    SELECT *
    FROM world.employees
    WHERE employee_id = empId;
END //
DELIMITER ;
