# PRM392_Mikky
Trong trường hợp không kết nối database bên api được thì vào file appsettings.json, ctrl+c ctrl+v dòng này, sau đó vào sql server, nhó chỉnh dtb sang local:
  "ConnectionStrings": {
    "Mikky": "server=(local);database=Mikky;uid={tên user của local dtb};pwd={password của local dtb};TrustServerCertificate=True"
  },
  "Logging": {
    "LogLevel": {
      "Default": "Information",
      "Microsoft": "Warning",
      "Microsoft.Hosting.Lifetime": "Information"
    }
  },
  "AllowedHosts": "*"
}
