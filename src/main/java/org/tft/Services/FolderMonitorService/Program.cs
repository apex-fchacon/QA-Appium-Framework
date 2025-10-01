using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Configuration;
using System.IO;
using System.Threading;
using System.Threading.Tasks;
using System;

namespace FolderMonitorService
{
    // Programa principal
    class Program
    {
        static async Task Main(string[] args)
        {
            var builder = Host.CreateApplicationBuilder(args);
            
            // Configurar como servicio de Windows
            // builder.Services.AddWindowsService(options =>
            // {
            //     options.ServiceName = "FolderMonitorService";
            // });

            // Registrar el servicio worker
            builder.Services.AddHostedService<FolderMonitorWorker>();
            
            // Configurar logging
            builder.Services.AddLogging(config =>
            {
                config.AddConsole();
                // config.AddEventLog();
            });

            builder.Configuration
                .SetBasePath(Directory.GetCurrentDirectory())
                .AddJsonFile("appsettings.json", optional: true, reloadOnChange: true);


            var host = builder.Build();
            await host.RunAsync();
        }
    }

    // Worker service
    public class FolderMonitorWorker : BackgroundService
    {
        private readonly ILogger<FolderMonitorWorker> _logger;
        private readonly IConfiguration _configuration;
        private FileSystemWatcher? _fileWatcher;
        private string _folderPath;
        private string _logPath;

        public FolderMonitorWorker(ILogger<FolderMonitorWorker> logger, IConfiguration configuration)
        {
            _logger = logger;
            _configuration = configuration;
            
            // Obtener configuración
            _folderPath = _configuration["FolderPath"] ?? Environment.GetEnvironmentVariable("FolderPath") ?? @"C:\MonitoredFolder";
            _logPath = _configuration["LogPath"] ?? Environment.GetEnvironmentVariable("FolderPath") ?? @"C:\Logs\FolderMonitor.log";
        }

        protected override async Task ExecuteAsync(CancellationToken stoppingToken)
        {
            _logger.LogInformation("Monitor Service Started");
            
            try
            {
                // Verificar que la carpeta existe
                if (!Directory.Exists(_folderPath))
                {
                    Directory.CreateDirectory(_folderPath);
                    _logger.LogInformation("Folder Created: {FolderPath}", _folderPath);
                }

                // Configurar FileSystemWatcher
                _fileWatcher = new FileSystemWatcher(_folderPath);
                _fileWatcher.Filter = "*.*";
                _fileWatcher.IncludeSubdirectories = true;
                
                // Configurar eventos
                _fileWatcher.Created += OnFileChanged;
                _fileWatcher.Changed += OnFileChanged;
                _fileWatcher.Deleted += OnFileChanged;
                _fileWatcher.Renamed += OnFileRenamed;
                
                _fileWatcher.NotifyFilter = NotifyFilters.CreationTime | 
                                          NotifyFilters.LastWrite | 
                                          NotifyFilters.FileName | 
                                          NotifyFilters.DirectoryName;
                
                _fileWatcher.EnableRaisingEvents = true;
                
                _logger.LogInformation("Monitoring Folder: {FolderPath}", _folderPath);
                
                // Bucle principal con verificaciones periódicas
                while (!stoppingToken.IsCancellationRequested)
                {
                    await Task.Delay(TimeSpan.FromMinutes(1), stoppingToken);
                    
                    // Verificación periódica
                    if (Directory.Exists(_folderPath))
                    {
                        var files = Directory.GetFiles(_folderPath, "*.*", SearchOption.AllDirectories);
                        _logger.LogInformation("Periodic verification - Total files: {FileCount}", files.Length);
                        WriteToLogFile($"Periodic verification - Total files: {files.Length}");
                    }
                }
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Error in monitoring service");
            }
            finally
            {
                _fileWatcher?.Dispose();
                _logger.LogInformation("Monitoring service stopped");
            }
        }

        private void OnFileChanged(object sender, FileSystemEventArgs e)
        {
            try
            {
                var message = $"File {e.ChangeType}: {e.FullPath}";
                _logger.LogInformation(message);
                WriteToLogFile(message);
                
                // Procesar el cambio
                ProcessFileChange(e.FullPath, e.ChangeType.ToString());
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Error processing file change");
            }
        }

        private void OnFileRenamed(object sender, RenamedEventArgs e)
        {
            try
            {
                var message = $"Renamed file: {e.OldFullPath} -> {e.FullPath}";
                _logger.LogInformation(message);
                WriteToLogFile(message);
                
                ProcessFileChange(e.FullPath, "Renamed");
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Error processing file rename");
            }
        }

        private void ProcessFileChange(string filePath, string changeType)
        {
            try
            {
                var message = $"Processing: {changeType} - {filePath}";
                _logger.LogInformation(message);
                WriteToLogFile(message);
                
                // Lógica específica según el tipo de cambio
                if (changeType == "Created" && File.Exists(filePath))
                {
                    var fileInfo = new FileInfo(filePath);
                    var detailMessage = $"New File: {fileInfo.Name}, Size: {fileInfo.Length} bytes";
                    _logger.LogInformation(detailMessage);
                    WriteToLogFile(detailMessage);
                    
                    // Aquí puedes agregar tu lógica personalizada:
                    // - Copiar archivos
                    // - Procesar contenido
                    // - Enviar notificaciones
                    // - Actualizar base de datos
                }
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Error while processing file change: {FilePath}", filePath);
            }
        }

        private void WriteToLogFile(string message)
        {
            try
            {
                var logMessage = $"{DateTime.Now:yyyy-MM-dd HH:mm:ss} - {message}";
                
                // Crear directorio si no existe
                var logDir = Path.GetDirectoryName(_logPath);
                if (!string.IsNullOrEmpty(logDir) && !Directory.Exists(logDir))
                {
                    Directory.CreateDirectory(logDir);
                }
                
                // Escribir de forma asíncrona
                File.AppendAllText(_logPath, logMessage + Environment.NewLine);
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Error writing to log file");
            }
        }

        public override void Dispose()
        {
            _fileWatcher?.Dispose();
            base.Dispose();
        }
    }
}