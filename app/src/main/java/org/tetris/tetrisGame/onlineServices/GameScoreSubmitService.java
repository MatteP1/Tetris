package org.tetris.tetrisGame.onlineServices;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import com.google.gson.Gson;

import org.tetris.Framework.GameObserver;
import org.tetris.Framework.GameScore;
import org.tetris.tetrisGame.GridElement;

public class GameScoreSubmitService implements GameObserver{

	private HttpClient client;
	private HttpRequest.Builder builder;

	public GameScoreSubmitService() {
		client = HttpClient.newHttpClient();
		builder = HttpRequest.newBuilder().uri(URI.create("http://192.168.49.2:32481/api/Game")).header("Content-Type", "application/json");
	}

	@Override
	public void playFieldChangedAt(List<GridElement> grids) {}

	@Override
	public void gameLost(GameScore gamescore) {
		Gson gson = new Gson();
		String body = gson.toJson(new ScoreModel(gamescore.player().userName(), gamescore.score()));
		HttpRequest request = builder.copy().POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8)).build();
		try {
			client.sendAsync(request, HttpResponse.BodyHandlers.discarding());
		} catch (Exception e) {
			System.out.println("Couldn't submit score :(");
		}
	}
	
}
