# Battery Saver Mode System

Java-only dynamic web project that monitors device battery status, applies a saver-mode policy, and displays the active power profile in a clean web dashboard.

## Features

- Embedded Java web server
- Automatic battery level monitoring with OSHI
- Smart saver and ultra saver profiles
- Dynamic UI generated from Java classes
- Auto-refreshing status dashboard

## Run

```powershell
.\apache-maven-3.9.15\bin\mvn.cmd compile exec:java
```

Then open:

```text
http://localhost:8080/
```
