# Sử dụng image PostgreSQL phiên bản 15
FROM postgres:15

# Thiết lập biến môi trường cho PostgreSQL
ENV POSTGRES_USER=admin
ENV POSTGRES_PASSWORD=admin123
ENV POSTGRES_DB=postgres

# Copy file SQL vào thư mục initdb để tự động chạy khi container khởi tạo lần đầu
COPY init.sql /docker-entrypoint-initdb.d/

# Expose cổng mặc định của PostgreSQL
EXPOSE 5432
