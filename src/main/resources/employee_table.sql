-- Create a sample employees table
CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(255),
    salary DECIMAL(10, 2)
);

-- Insert some sample data into the employees table
INSERT INTO employees (employee_id, employee_name, salary) VALUES
(1, 'John Doe', 50000.00),
(2, 'Jane Smith', 60000.00),
(3, 'Bob Johnson', 75000.00),
(4, 'Alice Williams', 80000.00),
(5, 'Charlie Brown', 55000.00);
