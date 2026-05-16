# Tự động đặt JAVA_HOME nếu chưa có (Windows)
if (-not $env:JAVA_HOME -or -not (Test-Path "$env:JAVA_HOME\bin\java.exe")) {
    $candidates = @(
        "C:\Program Files\Java\jdk-23",
        "C:\Program Files\Java\jdk-21",
        "C:\Program Files\Java\jdk-17"
    )
    foreach ($path in $candidates) {
        if (Test-Path "$path\bin\java.exe") {
            $env:JAVA_HOME = $path
            break
        }
    }
    if (-not $env:JAVA_HOME) {
        $settings = cmd /c "java -XshowSettings:properties -version 2>&1" | Select-String "java.home"
        if ($settings -match "=\s*(.+)") {
            $env:JAVA_HOME = $Matches[1].Trim()
        }
    }
}

if (-not $env:JAVA_HOME) {
    Write-Error "Không tìm thấy JAVA_HOME. Cài JDK 17+ và đặt biến môi trường JAVA_HOME."
    exit 1
}
