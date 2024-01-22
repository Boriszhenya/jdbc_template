DROP TABLE IF EXISTS employees;
-- Create a sample employees table
CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    salary DECIMAL(10, 2)
);

-- Insert some sample data into the employees table
INSERT INTO employees (name, salary) VALUES
('John Doe', 50000.00),
('Jane Smith', 60000.00),
('Bob Johnson', 75000.00),
('Alice Williams', 80000.00),
('Charlie Brown', 55000.00);
