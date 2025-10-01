# FolderMonitorService Setup Guide

## Prerequisites

- [.NET SDK 8.x](https://dotnet.microsoft.com/download) must be installed.

## 1. Install Required Packages

```sh
dotnet add package System.ServiceProcess.ServiceController
dotnet add package System.Configuration.ConfigurationManager
```

## 2. Restore Packages

```sh
dotnet restore
```

## 3. Build as a Self-Contained Executable

```sh
dotnet publish -c Release -r win-x64 --self-contained true -p:PublishSingleFile=true
```

## 4. Install the Service (Run as Administrator)

```sh
sc create FolderMonitorService binpath= "C:\Users\cm304\Downloads\FolderMonitorService\bin\Release\net8.0-windows\win-x64\publish\FolderMonitorService.exe" start= auto
```

## 5. Start the Service

```sh
sc start FolderMonitorService
```

## 6. Add a Description to the Service

```sh
sc description FolderMonitorService "This service monitors changes in a specific folder"
```

## 7. Remove the Service

```sh
sc stop FolderMonitorService
sc delete FolderMonitorService
```
