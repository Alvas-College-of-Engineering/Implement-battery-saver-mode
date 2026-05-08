package com.example.batterysaver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;

public final class BatterySaverApp {
    private static final int PORT = 8080;

    private final BatterySaverService service;
    private final DashboardRenderer renderer;

    public BatterySaverApp(BatterySaverService service, DashboardRenderer renderer) {
        this.service = service;
        this.renderer = renderer;
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/", this::handleDashboard);
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Battery Saver web preview is running at http://localhost:" + PORT + "/");
    }

    private void handleDashboard(HttpExchange exchange) throws IOException {
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        String html = renderer.render(service.currentSnapshot());
        byte[] response = html.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
        exchange.sendResponseHeaders(200, response.length);
        try (OutputStream body = exchange.getResponseBody()) {
            body.write(response);
        }
    }

    public static void main(String[] args) throws IOException {
        BatterySaverService service = new BatterySaverService(new BatteryMonitor(), new SaverModePolicy());
        new BatterySaverApp(service, new DashboardRenderer()).start();
    }
}
