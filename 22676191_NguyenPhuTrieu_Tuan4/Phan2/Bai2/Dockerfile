# Sử dụng Python 3.9 làm base image
FROM python:3.9

# Đặt thư mục làm việc trong container
WORKDIR /app

# Copy file requirements.txt vào container
COPY requirements.txt .

# Cài đặt các dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Copy toàn bộ source code vào container
COPY . .

# Expose cổng 5000 cho ứng dụng
EXPOSE 5000

# Khởi chạy ứng dụng Flask
CMD ["python", "app.py"]