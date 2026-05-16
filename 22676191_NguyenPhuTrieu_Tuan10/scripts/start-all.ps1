$root = Split-Path -Parent $PSScriptRoot
Set-Location $root
. "$PSScriptRoot\env.ps1"

$mvn = Join-Path $root "mvnw.ps1"

Write-Host "JAVA_HOME=$env:JAVA_HOME" -ForegroundColor DarkGray
Write-Host "Starting RabbitMQ (docker compose)..." -ForegroundColor Cyan
docker compose up -d

Write-Host "Building project (Maven Wrapper)..." -ForegroundColor Cyan
& $mvn clean install -DskipTests -q
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }

$services = @(
    @{ Name = "user-food-service"; Port = 8081 },
    @{ Name = "order-service"; Port = 8082 },
    @{ Name = "payment-service"; Port = 8083 },
    @{ Name = "notification-service"; Port = 8084 },
    @{ Name = "api-gateway"; Port = 8080 }
)

foreach ($svc in $services) {
    Write-Host "Starting $($svc.Name) on port $($svc.Port)..." -ForegroundColor Green
    $cmd = "cd '$root'; `$env:JAVA_HOME='$env:JAVA_HOME'; & '$mvn' -pl $($svc.Name) spring-boot:run"
    Start-Process powershell -ArgumentList "-NoExit", "-Command", $cmd
    Start-Sleep -Seconds 8
}

Write-Host ""
Write-Host "All services starting. Gateway: http://localhost:8080" -ForegroundColor Yellow
Write-Host "Frontend: cd frontend; npm run dev -> http://localhost:5173" -ForegroundColor Yellow
