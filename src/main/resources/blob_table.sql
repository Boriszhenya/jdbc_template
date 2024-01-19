DROP TABLE IF EXISTS blob_table;
CREATE TABLE blob_table (
                            id INT PRIMARY KEY,
                            data LONGBLOB,
                            file_name VARCHAR(255),
                            data_type VARCHAR(16)
);
