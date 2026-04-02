# Stage 1: Build
FROM golang:1.21-alpine AS builder

WORKDIR /app

# Copy go mod file
COPY go.mod ./

# Copy source code
COPY . .

# Build the Go application
RUN go build -o main .

# Stage 2: Run
FROM alpine:latest

WORKDIR /app

# Copy the binary from the builder stage
COPY --from=builder /app/main .

# Expose port
EXPOSE 8080

# Run the application
CMD ["./main"]
