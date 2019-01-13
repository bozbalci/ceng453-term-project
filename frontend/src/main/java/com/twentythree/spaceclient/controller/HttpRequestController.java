package com.twentythree.spaceclient.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.twentythree.spaceclient.constants.Network;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.concurrent.Future;

public class HttpRequestController {
    private HttpBasicAuthContainer auth = null;

    HttpRequestController() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Unirest.setDefaultHeader("accept", "application/json");
        Unirest.setDefaultHeader("Content-Type", "application/json");
    }

    public boolean login(HttpBasicAuthContainer auth) {
        this.auth = auth;

        try {
            HttpResponse<String> response = Unirest.get(Network.API_URL.concat("/player/whoami"))
                    .basicAuth(this.auth.getUsername(), this.auth.getPassword())
                    .asString();

            return response.getStatus() == HttpStatus.SC_OK;

        } catch (UnirestException e) {
            return false;
        }
    }

    public boolean register(HttpBasicAuthContainer auth) {
        this.auth = auth;

        try {
            HttpResponse<String> response = Unirest.post(Network.API_URL.concat(
                    "/player?username={username}&password={password}"))
                    .routeParam("username", auth.getUsername())
                    .routeParam("password", auth.getPassword())
                    .asString();

            System.out.println(response.getBody());
            System.out.println(Long.valueOf(response.getBody()));
            return Long.valueOf(response.getBody()) != -1;
        } catch (UnirestException e) {
            // Do nothing.
            return false;
        }
    }

    public void submitScore(long score) {
        ScoreObject scoreObject = new ScoreObject(score);

        Future<HttpResponse<JsonNode>> response = Unirest.post(Network.API_URL.concat("/match/submit-sp-score"))
            .basicAuth(auth.getUsername(), auth.getPassword())
            .body(scoreObject)
            .asJsonAsync(new Callback<JsonNode>() {
                @Override
                public void completed(HttpResponse<JsonNode> response) {
                    // Do nothing.
                }

                @Override
                public void failed(UnirestException e) {
                    // Do nothing.
                }

                @Override
                public void cancelled() {
                    // Do nothing.
                }
        });
    }

    public LeaderboardObject getLeaderboard() {
        return getLeaderboard(false);
    }

    public LeaderboardObject getLeaderboard(boolean weekly) {
        String url;

        if (weekly) {
            url = Network.API_URL.concat("/leaderboard/weekly");
        } else {
            url = Network.API_URL.concat("/leaderboard/all");
        }

        try {
            HttpResponse<LeaderboardObject> response = Unirest.get(url)
                    .basicAuth(auth.getUsername(), auth.getPassword())
                    .asObject(LeaderboardObject.class);

            return response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return null;
    }
}
