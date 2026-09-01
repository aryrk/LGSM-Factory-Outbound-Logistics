$rootDir = Split-Path -Parent $PSScriptRoot

function Ensure-EnvFile {
    param(
        [string]$Target,
        [string]$Source
    )

    if (Test-Path $Target) {
        Write-Host "Using existing $Target"
        return
    }

    if (Test-Path $Source) {
        Copy-Item $Source $Target
        Write-Host "Created $Target from $Source"
    } else {
        throw "No template found for $Target"
    }
}

Ensure-EnvFile -Target (Join-Path $rootDir "logistics/.env") -Source (Join-Path $rootDir "logistics/.env.example")
Ensure-EnvFile -Target (Join-Path $rootDir "logistics-FE/.env") -Source (Join-Path $rootDir "logistics-FE/.env.example")
