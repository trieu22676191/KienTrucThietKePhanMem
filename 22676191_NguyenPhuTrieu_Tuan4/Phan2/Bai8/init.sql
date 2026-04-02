-- Tạo database mydb
CREATE DATABASE mydb;

-- Kết nối vào database mydb và tạo bảng
\c mydb;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Thêm dữ liệu mẫu
INSERT INTO users (name, email) VALUES
    ('Nguyen Van A', 'nguyenvana@example.com'),
    ('Tran Thi B', 'tranthib@example.com'),
    ('Le Van C', 'levanc@example.com');
