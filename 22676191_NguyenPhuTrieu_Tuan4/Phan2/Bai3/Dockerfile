# ==========================================
# Stage 1: Build ứng dụng React
# ==========================================
FROM node:18-alpine AS build

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Copy file package.json và package-lock.json (nếu có)
COPY package*.json ./

# Cài đặt dependencies
RUN npm install

# Copy toàn bộ source code vào container
COPY . .

# Build ứng dụng React (tạo thư mục build/)
RUN npm run build

# ==========================================
# Stage 2: Serve ứng dụng với Nginx
# ==========================================
FROM nginx:alpine

# Copy file build từ stage 1 vào thư mục serve của Nginx
COPY --from=build /app/build /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Chạy Nginx ở foreground
CMD ["nginx", "-g", "daemon off;"]
