package com.blackjackgame.httpclient;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CustomHttpClient {
    private static CustomHttpClient single_instance = null;
    private static final String API_URL = "https://deckofcardsapi.com/api/deck";

    public HttpClient client;

    private CustomHttpClient() {
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    public static synchronized CustomHttpClient getInstance() {
        if (single_instance == null)
            single_instance = new CustomHttpClient();

        return single_instance;
    }

    public static String customGet(String endpoint) {
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(API_URL + endpoint))
                    .GET()
                    .build();
            CompletableFuture<HttpResponse<String>> res;
            CustomHttpClient customClient = getInstance();
            res = customClient.client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            String resBody;
            resBody = res.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
            return resBody;
        } catch (URISyntaxException | ExecutionException | InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
