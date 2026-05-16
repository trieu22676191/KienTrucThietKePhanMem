$root = Split-Path -Parent $PSScriptRoot
Set-Location $root
. "$PSScriptRoot\env.ps1"
& (Join-Path $root "mvnw.ps1") clean install -DskipTests @args
