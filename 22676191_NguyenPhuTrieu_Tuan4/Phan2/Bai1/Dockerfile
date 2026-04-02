# Sử dụng Node.js 18 làm base image
FROM node:18

# Đặt thư mục làm việc trong container
WORKDIR /usr/src/app

# Copy package.json và package-lock.json (nếu có)
COPY package*.json ./

# Cài đặt dependencies
RUN npm install

# Copy toàn bộ source code vào container
COPY . .

# Expose cổng 3000 cho ứng dụng
EXPOSE 3000

# Khởi chạy ứng dụng
CMD ["node", "server.js"]