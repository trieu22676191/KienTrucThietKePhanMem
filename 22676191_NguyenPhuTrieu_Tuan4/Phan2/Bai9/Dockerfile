# Sử dụng image redis:latest
FROM redis:latest

# Copy file cấu hình tùy chỉnh vào container
COPY redis.conf /usr/local/etc/redis/redis.conf

# Expose port Redis
EXPOSE 6379

# Khởi chạy Redis với file cấu hình tùy chỉnh
CMD ["redis-server", "/usr/local/etc/redis/redis.conf"]
