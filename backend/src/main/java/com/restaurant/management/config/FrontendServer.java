package com.restaurant.management.config;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.Executors;

@Component
public class FrontendServer {
    private static final Logger log = LoggerFactory.getLogger(FrontendServer.class);
    private HttpServer server;

    @PostConstruct
    public void start() {
        int[] ports = {3000, 8081, 5000};
        for (int port : ports) {
            if (startOnPort(port)) return;
        }
        log.error("所有端口均被占用");
    }

    private boolean startOnPort(int port) {
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new FrontendHandler());
            server.setExecutor(Executors.newSingleThreadExecutor());
            server.start();
            log.info("🌐 小徐同学已启动: http://localhost:" + port);
            return true;
        } catch (Exception e) { return false; }
    }

    @PreDestroy
    public void stop() { if (server != null) server.stop(0); }

    static class FrontendHandler implements HttpHandler {
        private static final String API_BACKEND = "http://localhost:8080";
        private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();

            // Proxy API requests to backend
            if (path.startsWith("/api/")) {
                proxyApi(exchange);
                return;
            }

            // Serve static files with SPA fallback
            if (path.equals("/") || path.isEmpty()) path = "/index.html";

            byte[] data = readResource("/static" + path);
            if (data == null) data = readResource("/static/index.html");
            if (data == null) data = "页面未找到".getBytes("utf-8");

            exchange.getResponseHeaders().set("Content-Type", getContentType(path));
            exchange.sendResponseHeaders(200, data.length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(data); }
        }

        private void proxyApi(HttpExchange exchange) throws IOException {
            String targetUrl = API_BACKEND + exchange.getRequestURI().getRawPath()
                + (exchange.getRequestURI().getRawQuery() != null ? "?" + exchange.getRequestURI().getRawQuery() : "");

            try {
                // Read request body
                byte[] body = null;
                String contentLength = exchange.getRequestHeaders().getFirst("Content-Length");
                if (contentLength != null && !contentLength.equals("0")) {
                    body = exchange.getRequestBody().readAllBytes();
                }

                // Build request
                HttpRequest.Builder reqBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(targetUrl))
                    .timeout(Duration.ofSeconds(30));

                String method = exchange.getRequestMethod();
                if ("GET".equalsIgnoreCase(method)) {
                    reqBuilder.GET();
                } else if ("POST".equalsIgnoreCase(method)) {
                    reqBuilder.POST(body != null ? HttpRequest.BodyPublishers.ofByteArray(body) : HttpRequest.BodyPublishers.noBody());
                } else if ("PUT".equalsIgnoreCase(method)) {
                    reqBuilder.PUT(body != null ? HttpRequest.BodyPublishers.ofByteArray(body) : HttpRequest.BodyPublishers.noBody());
                } else if ("DELETE".equalsIgnoreCase(method)) {
                    reqBuilder.DELETE();
                } else {
                    reqBuilder.method(method, body != null ? HttpRequest.BodyPublishers.ofByteArray(body) : HttpRequest.BodyPublishers.noBody());
                }

                // Copy content-type header
                String ct = exchange.getRequestHeaders().getFirst("Content-Type");
                if (ct != null) reqBuilder.header("Content-Type", ct);

                HttpResponse<byte[]> resp = client.send(reqBuilder.build(), HttpResponse.BodyHandlers.ofByteArray());

                // Send response back
                exchange.getResponseHeaders().set("Content-Type", resp.headers().firstValue("Content-Type").orElse("application/json"));
                exchange.sendResponseHeaders(resp.statusCode(), resp.body().length);
                try (OutputStream os = exchange.getResponseBody()) { os.write(resp.body()); }

            } catch (Exception e) {
                String error = "{\"code\":500,\"message\":\"代理请求失败: " + e.getMessage() + "\",\"data\":null}";
                byte[] errBytes = error.getBytes("utf-8");
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(500, errBytes.length);
                try (OutputStream os = exchange.getResponseBody()) { os.write(errBytes); }
            }
        }

        private byte[] readResource(String resPath) {
            try {
                URL url = FrontendServer.class.getResource(resPath);
                if (url == null) {
                    url = Thread.currentThread().getContextClassLoader().getResource(
                        resPath.startsWith("/") ? resPath.substring(1) : resPath);
                }
                if (url == null) return null;
                try (InputStream is = url.openStream(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    byte[] buf = new byte[8192]; int n;
                    while ((n = is.read(buf)) != -1) baos.write(buf, 0, n);
                    return baos.toByteArray();
                }
            } catch (Exception e) { return null; }
        }

        private String getContentType(String p) {
            if (p.endsWith(".js")) return "application/javascript; charset=utf-8";
            if (p.endsWith(".css")) return "text/css; charset=utf-8";
            if (p.endsWith(".html")) return "text/html; charset=utf-8";
            if (p.endsWith(".png")) return "image/png";
            if (p.endsWith(".svg")) return "image/svg+xml";
            if (p.endsWith(".ico")) return "image/x-icon";
            if (p.endsWith(".woff2")) return "font/woff2";
            return "application/octet-stream";
        }
    }
}
