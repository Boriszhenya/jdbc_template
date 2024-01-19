DROP TABLE IF EXISTS big_table;
CREATE TABLE big_table (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    description TEXT,
    age INT,
    salary DECIMAL(10, 2),
    birthdate DATE,
    married BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

