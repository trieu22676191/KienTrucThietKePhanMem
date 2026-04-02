# ============================================
# Stage 1: Build stage - Dùng node:18 để build code
# ============================================
FROM node:18 AS builder

WORKDIR /app

# Copy package.json và cài đặt dependencies
COPY package.json ./
RUN npm install

# Copy source code và build
COPY src/ ./src/
RUN npm run build

# ============================================
# Stage 2: Production stage - Dùng node:18-alpine để chạy
# ============================================
FROM node:18-alpine

WORKDIR /app

# Copy package.json và chỉ cài production dependencies
COPY package.json ./
RUN npm install --only=production

# Copy code đã build từ stage 1
COPY --from=builder /app/dist ./dist

EXPOSE 3000

CMD ["npm", "start"]
