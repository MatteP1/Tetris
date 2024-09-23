package org.tetris.tetrisGame;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.google.gson.Gson;

public class GameScoreRetrievalService {

	private HttpClient client;
	private HttpRequest.Builder builder;

	public GameScoreRetrievalService() {
		client = HttpClient.newHttpClient();
		builder = HttpRequest.newBuilder().uri(URI.create("http://192.168.49.2:32481/api/Game")).header("Content-Type", "application/json");
	}

	public CompletableFuture<List<GameScore>> getScores() {
		HttpRequest request = builder.copy().GET().build();
		try {
			var res = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
			return res.thenApply(ress -> {
				Gson gson = new Gson();
				GameScore[] scoreList = gson.fromJson(ress.body(), GameScore[].class);
				List<GameScore> scoreListSorted = new ArrayList<>(Arrays.asList(scoreList));
				Collections.sort(scoreListSorted, Comparator.comparingInt(GameScore::score).reversed());
				return scoreListSorted;
			});
		} catch (Exception e) {
			System.out.println("Couldn't retrieve scores :(");
			return CompletableFuture.failedFuture(e);
		}
	}
	
}
