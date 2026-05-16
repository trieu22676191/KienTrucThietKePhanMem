# Maven Wrapper + tự đặt JAVA_HOME (không cần set thủ công)
$root = $PSScriptRoot
. (Join-Path $root "scripts\env.ps1")
& (Join-Path $root "mvnw.cmd") @args
exit $LASTEXITCODE
