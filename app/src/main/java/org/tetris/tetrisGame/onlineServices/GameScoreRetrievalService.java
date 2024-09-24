package org.tetris.tetrisGame.onlineServices;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.tetris.Framework.GameScore;
import org.tetris.Framework.Player;

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
				ScoreModel[] scores = gson.fromJson(ress.body(), ScoreModel[].class);
				List<ScoreModel> scoreList = new ArrayList<>(Arrays.asList(scores));
				List<GameScore> gameScores = 
					scoreList.parallelStream()
							 .map(sc -> new GameScore(new Player(sc.userName()), sc.score()))
							 .sorted(Comparator.comparingInt(GameScore::score).reversed())
							 .toList();
				return gameScores;
			});
		} catch (Exception e) {
			System.out.println("Couldn't retrieve scores :(");
			return CompletableFuture.failedFuture(e);
		}
	}
	
}
